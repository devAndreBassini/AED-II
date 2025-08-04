#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define MAX_RECORDS 10000
#define MAX_LINE    10000

// ---------- Estrutura Show ----------

typedef struct Show {
    char *showId;
    char *type;
    char *title;
    char *director;
    char **cast;
    int castSize;
    char *country;
    char *dateAdded;
    int day, month, year;
    int releaseYear;
    char *rating;
    char *duration;
    char **listedIn;
    int listedSize;
} Show;

// Nó de lista duplamente encadeada
typedef struct Node {
    Show data;
    struct Node *prev, *next;
} Node;

// ---------- Funções auxiliares ----------

static char *trim(char *s) {
    while (isspace((unsigned char)*s)) s++;
    if (*s == '\0') return s;
    char *end = s + strlen(s) - 1;
    while (end > s && isspace((unsigned char)*end)) end--;
    *(end+1) = '\0';
    return s;
}

static int monthNumber(const char *m) {
    static const char *months[] = {
        "January","February","March","April","May","June",
        "July","August","September","October","November","December"
    };
    for (int i = 0; i < 12; i++)
        if (!strcmp(m, months[i])) return i+1;
    return 0;
}

static char *dupString(const char *s) {
    char *d = malloc(strlen(s)+1);
    return d ? strcpy(d,s) : NULL;
}

static void parseList(char *field, char ***outArr, int *outSize) {
    *outSize = 0;
    if (!field || !strcmp(field,"NaN")) { *outArr = NULL; return; }
    char *copy = dupString(field);
    int cap = 4;
    *outArr = malloc(cap * sizeof(char*));
    char *t = strtok(copy, ",");
    while (t) {
        if (*outSize >= cap) {
            cap *= 2;
            *outArr = realloc(*outArr, cap * sizeof(char*));
        }
        (*outArr)[(*outSize)++] = dupString(trim(t));
        t = strtok(NULL, ",");
    }
    free(copy);
}

static void lerShow(Show *s, const char *line) {
    char buf[MAX_LINE];
    int inQ = 0, bi = 0, f = 0;
    char *fields[11];
    for (int i = 0; line[i] && line[i] != '\n'; i++) {
        char c = line[i];
        if (c == '"') inQ = !inQ;
        else if (c == ',' && !inQ) {
            buf[bi] = '\0';
            fields[f++] = dupString(trim(buf));
            bi = 0;
        } else {
            buf[bi++] = c;
        }
    }
    buf[bi] = '\0';
    fields[f++] = dupString(trim(buf));

    s->showId     = dupString(fields[0]);
    s->type       = dupString(fields[1]);
    s->title      = dupString(fields[2]);
    s->director   = dupString(fields[3]);
    parseList(fields[4], &s->cast, &s->castSize);
    s->country    = dupString(fields[5]);
    s->dateAdded  = fields[6][0] ? dupString(fields[6]) : dupString("March 1, 1900");
    {
        char m[20];
        int d,y;
        if (sscanf(s->dateAdded, "%19s %d, %d", m, &d, &y) == 3) {
            s->month = monthNumber(m);
            s->day   = d;
            s->year  = y;
        } else {
            s->month=3; s->day=1; s->year=1900;
        }
    }
    s->releaseYear = atoi(fields[7]);
    s->rating      = dupString(fields[8]);
    s->duration    = dupString(fields[9]);
    parseList(fields[10], &s->listedIn, &s->listedSize);

    for (int i = 0; i < f; i++) free(fields[i]);
}

static void imprimirShow(const Show *s) {
    printf("=> %s ## %s ## %s ## %s ## [",
        s->showId, s->title, s->type, s->director);
    for (int i = 0; i < s->castSize; i++) {
        printf("%s", s->cast[i]);
        if (i < s->castSize-1) printf(", ");
    }
    printf("] ## %s ## %s ## %d ## %s ## %s ## [",
        s->country, s->dateAdded, s->releaseYear, s->rating, s->duration);
    for (int i = 0; i < s->listedSize; i++) {
        printf("%s", s->listedIn[i]);
        if (i < s->listedSize-1) printf(", ");
    }
    printf("]\n");
}

static int cmpDate(const Show *a, const Show *b) {
    if (a->year != b->year)   return a->year - b->year;
    if (a->month != b->month) return a->month - b->month;
    if (a->day != b->day)     return a->day - b->day;
    return strcmp(a->title, b->title);
}

static int comparacoes = 0, movimentacoes = 0;

// Swap de dois nós (troca apenas o conteúdo Show)
static void swapNodeData(Node *a, Node *b) {
    Show tmp = a->data;
    a->data = b->data;
    b->data = tmp;
    movimentacoes++;
}

// Particiona sublista [lo..hi] usando pivô hi
static Node *partition(Node *lo, Node *hi) {
    Show pivot = hi->data;
    Node *i = lo->prev;
    for (Node *j = lo; j != hi; j = j->next) {
        comparacoes++;
        if (cmpDate(&j->data, &pivot) <= 0) {
            i = (i == NULL ? lo : i->next);
            swapNodeData(i, j);
        }
    }
    i = (i == NULL ? lo : i->next);
    swapNodeData(i, hi);
    return i;
}

// Quicksort recursivo sobre ponteiros de nó
static void _quicksort(Node *lo, Node *hi) {
    if (hi != NULL && lo != hi && lo != hi->next) {
        Node *p = partition(lo, hi);
        _quicksort(lo, p->prev);
        _quicksort(p->next, hi);
    }
}

static void quicksortList(Node *head) {
    // encontra o último nó
    Node *hi = head;
    while (hi && hi->next) hi = hi->next;
    _quicksort(head, hi);
}

// ---------- Construção e manipulação da lista ----------

static Node *appendNode(Node *head, const Show *s) {
    Node *node = malloc(sizeof(Node));
    node->data = *s;  // copia estrutura Show
    node->next = node->prev = NULL;
    if (!head) return node;
    Node *tail = head;
    while (tail->next) tail = tail->next;
    tail->next = node;
    node->prev = tail;
    return head;
}

// Libera toda a lista e seus Shows
static void freeList(Node *head) {
    Node *cur = head;
    while (cur) {
        Node *tmp = cur->next;
        // libera campos do Show
        free(cur->data.showId);
        free(cur->data.type);
        free(cur->data.title);
        free(cur->data.director);
        for (int i = 0; i < cur->data.castSize; i++) free(cur->data.cast[i]);
        free(cur->data.cast);
        free(cur->data.country);
        free(cur->data.dateAdded);
        free(cur->data.rating);
        free(cur->data.duration);
        for (int i = 0; i < cur->data.listedSize; i++) free(cur->data.listedIn[i]);
        free(cur->data.listedIn);
        free(cur);
        cur = tmp;
    }
}

// ---------- Leitura do CSV e seleção por IDs ----------

int main() {
    char buf[MAX_LINE], idbuf[MAX_LINE];
    char *ids[MAX_RECORDS];
    int idc = 0;

    // lê IDs até "FIM"
    while (fgets(buf, MAX_LINE, stdin)) {
        buf[strcspn(buf, "\n")] = 0;
        if (!strcmp(buf, "FIM")) break;
        ids[idc++] = dupString(buf);
    }

    FILE *fp = fopen("/tmp/disneyplus.csv", "r");
    if (!fp) return 1;
    // descarta cabeçalho
    fgets(buf, MAX_LINE, fp);

    Node *list = NULL;
    // lê cada linha, extrai showId e compara
    while (fgets(buf, MAX_LINE, fp)) {
        buf[strcspn(buf, "\n")] = 0;
        int inQ = 0, bi = 0;
        for (int k = 0; buf[k] && buf[k] != '\n'; k++) {
            char c = buf[k];
            if (c == '"') inQ = !inQ;
            else if (c == ',' && !inQ) break;
            else idbuf[bi++] = c;
        }
        idbuf[bi] = 0; trim(idbuf);
        for (int k = 0; k < idc; k++) {
            if (!strcmp(idbuf, ids[k])) {
                Show s;
                lerShow(&s, buf);
                list = appendNode(list, &s);
                break;
            }
        }
    }
    fclose(fp);
    for (int i = 0; i < idc; i++) free(ids[i]);

    // Quicksort e log de tempo
    clock_t t0 = clock();
    quicksortList(list);
    double tempo = (double)(clock() - t0) / CLOCKS_PER_SEC;

    // imprime ordenado
    for (Node *cur = list; cur; cur = cur->next) {
        imprimirShow(&cur->data);
    }

    // grava log
    FILE *logf = fopen("763995_quicksort2.txt", "w");
    fprintf(logf, "763995\t%d\t%d\t%.6f\n",
            comparacoes, movimentacoes, tempo);
    fclose(logf);

    // libera lista
    freeList(list);
    return 0;
}
