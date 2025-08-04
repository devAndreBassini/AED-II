#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define INITIAL_DB_CAPACITY 1000
#define MAX_LINE_LENGTH 10000
#define QUEUE_CAPACITY 5

typedef struct {
    char *show_id;
    char *type;
    char *title;
    char *director;
    char **cast;
    int cast_count;
    char *country;
    char *date_added;
    int release_year;
    char *rating;
    char *duration;
    char **listed_in;
    int listed_in_count;
} Show;

static char *trim(char *str) {
    if (!str) return str;
    while (isspace((unsigned char)*str)) str++;
    if (*str == '\0') return str;
    char *end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end)) end--;
    *(end + 1) = '\0';
    return str;
}

static char *strdup_safe(const char *s) {
    if (!s) return NULL;
    char *dup = malloc(strlen(s) + 1);
    if (dup) strcpy(dup, s);
    return dup;
}

static int cmp_str(const void *a, const void *b) {
    const char *const *sa = (const char *const *)a;
    const char *const *sb = (const char *const *)b;
    return strcmp(*sa, *sb);
}

static char **split_str(const char *s, char delimiter, int *count) {
    *count = 0;
    if (!s) return NULL;
    int tokens = 1;
    for (const char *p = s; *p; p++)
        if (*p == delimiter) tokens++;
    char **arr = malloc(tokens * sizeof(char *));
    const char *start = s;
    int idx = 0;
    for (const char *p = s;; p++) {
        if (*p == delimiter || *p == '\0') {
            size_t len = p - start;
            char *tok = malloc(len + 1);
            memcpy(tok, start, len);
            tok[len] = '\0';
            arr[idx++] = trim(tok);
            if (*p == '\0') break;
            start = p + 1;
        }
    }
    *count = idx;
    return arr;
}

static char **split_csv_line(const char *line, int *n_fields) {
    *n_fields = 0;
    if (!line) return NULL;
    char **fields = malloc(100 * sizeof(char *));
    int count = 0;
    char buffer[MAX_LINE_LENGTH];
    int buf_idx = 0;
    int in_quotes = 0;
    for (int i = 0;; i++) {
        char c = line[i];
        if (c == '"') {
            in_quotes = !in_quotes;
        } else if ((c == ',' && !in_quotes) || c == '\0' || c == '\n') {
            buffer[buf_idx] = '\0';
            char *field = strdup_safe(buffer);
            if (field[0] == '"' && field[strlen(field) - 1] == '"' &&
                strlen(field) >= 2) {
                field[strlen(field) - 1] = '\0';
                memmove(field, field + 1, strlen(field));
            }
            fields[count++] = trim(field);
            buf_idx = 0;
            if (c == '\0' || c == '\n') break;
        } else {
            buffer[buf_idx++] = c;
        }
    }
    fields = realloc(fields, count * sizeof(char *));
    *n_fields = count;
    return fields;
}

static char **parse_list_field(const char *field, int *out_count) {
    if (!field || strcmp(field, "NaN") == 0) {
        char **arr = malloc(sizeof(char *));
        arr[0] = strdup_safe("NaN");
        *out_count = 1;
        return arr;
    }
    return split_str(field, ',', out_count);
}

static Show *parse_show_from_csv(const char *line) {
    int n_fields;
    char **fields = split_csv_line(line, &n_fields);
    if (n_fields < 11) {
        for (int i = 0; i < n_fields; i++) free(fields[i]);
        free(fields);
        return NULL;
    }
    Show *s = malloc(sizeof(Show));
    s->show_id = strdup_safe(fields[0][0] ? fields[0] : "NaN");
    s->type = strdup_safe(fields[1][0] ? fields[1] : "NaN");
    s->title = strdup_safe(fields[2][0] ? fields[2] : "NaN");
    s->director = strdup_safe(fields[3][0] ? fields[3] : "NaN");
    int cast_cnt;
    char **cast_arr = parse_list_field(fields[4], &cast_cnt);
    for (int i = 0; i < cast_cnt; i++)
        cast_arr[i] = strdup_safe(trim(cast_arr[i]));
    qsort(cast_arr, cast_cnt, sizeof(char *), cmp_str);
    s->cast = cast_arr;
    s->cast_count = cast_cnt;
    s->country = strdup_safe(fields[5][0] ? fields[5] : "NaN");
    s->date_added = strdup_safe(fields[6][0] ? fields[6] : "NaN");
    s->release_year = atoi(fields[7]);
    s->rating = strdup_safe(fields[8][0] ? fields[8] : "NaN");
    s->duration = strdup_safe(fields[9][0] ? fields[9] : "NaN");
    int list_cnt;
    char **list_arr = parse_list_field(fields[10], &list_cnt);
    for (int i = 0; i < list_cnt; i++)
        list_arr[i] = strdup_safe(trim(list_arr[i]));
    qsort(list_arr, list_cnt, sizeof(char *), cmp_str);
    s->listed_in = list_arr;
    s->listed_in_count = list_cnt;
    for (int i = 0; i < n_fields; i++) free(fields[i]);
    free(fields);
    return s;
}

static Show *clone_show(const Show *orig) {
    Show *s = malloc(sizeof(Show));
    s->show_id = strdup_safe(orig->show_id);
    s->type = strdup_safe(orig->type);
    s->title = strdup_safe(orig->title);
    s->director = strdup_safe(orig->director);
    s->cast_count = orig->cast_count;
    s->cast = malloc(s->cast_count * sizeof(char *));
    for (int i = 0; i < s->cast_count; i++)
        s->cast[i] = strdup_safe(orig->cast[i]);
    s->country = strdup_safe(orig->country);
    s->date_added = strdup_safe(orig->date_added);
    s->release_year = orig->release_year;
    s->rating = strdup_safe(orig->rating);
    s->duration = strdup_safe(orig->duration);
    s->listed_in_count = orig->listed_in_count;
    s->listed_in = malloc(s->listed_in_count * sizeof(char *));
    for (int i = 0; i < s->listed_in_count; i++)
        s->listed_in[i] = strdup_safe(orig->listed_in[i]);
    return s;
}

static void print_show(const Show *s) {
    printf("=> %s ## %s ## %s ## %s ## [", s->show_id, s->title, s->type,
           s->director);
    for (int i = 0; i < s->cast_count; i++) {
        printf("%s", s->cast[i]);
        if (i < s->cast_count - 1) printf(", ");
    }
    printf("] ## %s ## %s ## %d ## %s ## %s ## [", s->country, s->date_added,
           s->release_year, s->rating, s->duration);
    for (int i = 0; i < s->listed_in_count; i++) {
        printf("%s", s->listed_in[i]);
        if (i < s->listed_in_count - 1) printf(", ");
    }
    printf("] ##\n");
}

static void free_show(Show *s) {
    if (!s) return;
    free(s->show_id);
    free(s->type);
    free(s->title);
    free(s->director);
    for (int i = 0; i < s->cast_count; i++) free(s->cast[i]);
    free(s->cast);
    free(s->country);
    free(s->date_added);
    free(s->rating);
    free(s->duration);
    for (int i = 0; i < s->listed_in_count; i++) free(s->listed_in[i]);
    free(s->listed_in);
}

static Show **load_database(const char *file_path, int *out_count) {
    FILE *fp = fopen(file_path, "r");
    if (!fp) {
        fprintf(stderr, "Não foi possível abrir CSV '%s'\n", file_path);
        *out_count = 0;
        return NULL;
    }
    char line[MAX_LINE_LENGTH];
    fgets(line, MAX_LINE_LENGTH, fp);
    int capacity = INITIAL_DB_CAPACITY, count = 0;
    Show **db = malloc(capacity * sizeof(Show *));
    while (fgets(line, MAX_LINE_LENGTH, fp)) {
        line[strcspn(line, "\r\n")] = '\0';
        if (strlen(trim(line)) == 0) continue;
        Show *s = parse_show_from_csv(line);
        if (s) {
            if (count >= capacity) {
                capacity *= 2;
                db = realloc(db, capacity * sizeof(Show *));
            }
            db[count++] = s;
        }
    }
    fclose(fp);
    *out_count = count;
    return db;
}

static Show *find_in_database(Show **db, int db_count, const char *id) {
    for (int i = 0; i < db_count; i++) {
        if (strcmp(db[i]->show_id, id) == 0) return db[i];
    }
    return NULL;
}

typedef struct {
    Show *arr[QUEUE_CAPACITY];
    int head;
    int tail;
    int count;
} ShowQueue;

static void init_show_queue(ShowQueue *q) {
    q->head = 0;
    q->tail = 0;
    q->count = 0;
}

static Show *enqueue(ShowQueue *q, Show *s) {
    Show *removed = NULL;
    if (q->count == QUEUE_CAPACITY) {
        removed = q->arr[q->head];
        q->head = (q->head + 1) % QUEUE_CAPACITY;
        q->count--;
    }
    q->arr[q->tail] = s;
    q->tail = (q->tail + 1) % QUEUE_CAPACITY;
    q->count++;
    return removed;
}

static Show *dequeue(ShowQueue *q) {
    if (q->count == 0) return NULL;
    Show *res = q->arr[q->head];
    q->head = (q->head + 1) % QUEUE_CAPACITY;
    q->count--;
    return res;
}

static int average_release_year(ShowQueue *q) {
    if (q->count == 0) return 0;
    int sum = 0;
    int idx = q->head;
    for (int i = 0; i < q->count; i++) {
        sum += q->arr[idx]->release_year;
        idx = (idx + 1) % QUEUE_CAPACITY;
    }
    return sum / q->count;
}

static void print_queue(ShowQueue *q) {
    int idx = q->head;
    for (int i = 0; i < q->count; i++) {
        printf("[%d] ", i);
        print_show(q->arr[idx]);
        idx = (idx + 1) % QUEUE_CAPACITY;
    }
}

int main() {
    int db_count;
    Show **database = load_database("/tmp/disneyplus.csv", &db_count);

    ShowQueue queue;
    init_show_queue(&queue);

    char buffer[MAX_LINE_LENGTH];
    while (fgets(buffer, MAX_LINE_LENGTH, stdin)) {
        buffer[strcspn(buffer, "\r\n")] = '\0';
        char *entry = trim(buffer);
        if (strcmp(entry, "FIM") == 0) break;
        Show *orig = find_in_database(database, db_count, entry);
        if (orig) {
            Show *clone = clone_show(orig);
            Show *removed = enqueue(&queue, clone);
            if (removed) {
                free_show(removed);
                free(removed);
            }
            int avg = average_release_year(&queue);
            printf("[Media] %d\n", avg);
        }
    }

    if (!fgets(buffer, MAX_LINE_LENGTH, stdin)) return 0;
    int n_commands = atoi(trim(buffer));

    for (int i = 0; i < n_commands; i++) {
        if (!fgets(buffer, MAX_LINE_LENGTH, stdin)) break;
        buffer[strcspn(buffer, "\r\n")] = '\0';
        char *line = trim(buffer);
        if (strlen(line) == 0) {
            i--;
            continue;
        }
        if (line[0] == 'I' && isspace((unsigned char)line[1])) {
            char *id = trim(line + 2);
            Show *orig = find_in_database(database, db_count, id);
            if (orig) {
                Show *clone = clone_show(orig);
                Show *removed = enqueue(&queue, clone);
                if (removed) {
                    free_show(removed);
                    free(removed);
                }
                int avg = average_release_year(&queue);
                printf("[Media] %d\n", avg);
            }
        } else if (strcmp(line, "R") == 0) {
            Show *rem = dequeue(&queue);
            if (rem) {
                printf("(R) %s\n", rem->title);
                free_show(rem);
                free(rem);
            }
        }
    }

    print_queue(&queue);

    while (queue.count > 0) {
        Show *rem = dequeue(&queue);
        free_show(rem);
        free(rem);
    }
    for (int i = 0; i < db_count; i++) {
        free_show(database[i]);
        free(database[i]);
    }
    free(database);

    return 0;
}
