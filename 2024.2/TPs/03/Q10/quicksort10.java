import java.io.*;
import java.nio.file.*;
import java.util.*;

public class quicksort10 {
    private static final String MATRICULA = "763995";

    private static long comparacoes = 0;
    private static long movimentacoes = 0;

    static class Show {
        String showId;
        String type;
        String title;
        String director;
        List<String> cast;
        String country;
        String dateAdded;
        int day, month, year;
        int releaseYear;
        String rating;
        String duration;
        List<String> listedIn;

        static Show parseFromCsv(String line) {
            List<String> fields = splitCsvLine(line);
            if (fields.size() < 11)
                return null;

            Show s = new Show();
            s.showId = fields.get(0);
            s.type = fields.get(1);
            s.title = fields.get(2);
            s.director = fields.get(3);
            s.cast = parseListField(fields.get(4));
            s.country = fields.get(5);
            s.dateAdded = fields.get(6).isEmpty() ? "March 1, 1900" : fields.get(6);
            parseDate(s);
            s.releaseYear = parseIntSafe(fields.get(7), 0);
            s.rating = fields.get(8);
            s.duration = fields.get(9);
            s.listedIn = parseListField(fields.get(10));
            return s;
        }

        Show cloneShow() {
            Show c = new Show();
            c.showId = this.showId;
            c.type = this.type;
            c.title = this.title;
            c.director = this.director;
            c.cast = new ArrayList<>(this.cast);
            c.country = this.country;
            c.dateAdded = this.dateAdded;
            c.day = this.day;
            c.month = this.month;
            c.year = this.year;
            c.releaseYear = this.releaseYear;
            c.rating = this.rating;
            c.duration = this.duration;
            c.listedIn = new ArrayList<>(this.listedIn);
            return c;
        }

        @Override
        public String toString() {
            return String.format(
                    "=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %d ## %s ## %s ## [%s]",
                    showId, title, type, director,
                    String.join(", ", cast),
                    country, dateAdded, releaseYear, rating, duration,
                    String.join(", ", listedIn));
        }

        private static List<String> splitCsvLine(String line) {
            List<String> out = new ArrayList<>();
            StringBuilder buf = new StringBuilder();
            boolean inQuotes = false;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '"') {
                    inQuotes = !inQuotes;
                } else if (c == ',' && !inQuotes) {
                    out.add(trim(buf.toString()));
                    buf.setLength(0);
                } else {
                    buf.append(c);
                }
            }
            out.add(trim(buf.toString()));
            return out;
        }

        private static String trim(String s) {
            return s == null ? "" : s.trim();
        }

        private static List<String> parseListField(String field) {
            field = trim(field);
            if (field.isEmpty() || field.equals("NaN")) {
                return Collections.singletonList("NaN");
            }
            String[] parts = field.split(",");
            List<String> list = new ArrayList<>();
            for (String p : parts)
                list.add(p.trim());
            return list;
        }

        private static int parseIntSafe(String s, int def) {
            try {
                return Integer.parseInt(s.trim());
            } catch (Exception e) {
                return def;
            }
        }

        private static void parseDate(Show s) {
            String[] tokens = s.dateAdded.split("[ ,]+");
            if (tokens.length >= 3) {
                s.month = monthNumber(tokens[0]);
                s.day = parseIntSafe(tokens[1], 1);
                s.year = parseIntSafe(tokens[2], 1900);
            } else {
                s.month = 3;
                s.day = 1;
                s.year = 1900;
            }
        }

        private static int monthNumber(String m) {
            String[] months = {
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            };
            for (int i = 0; i < 12; i++)
                if (months[i].equalsIgnoreCase(m))
                    return i + 1;
            return 0;
        }
    }

    static class Node {
        Show data;
        Node prev, next;

        Node(Show s) {
            this.data = s.cloneShow();
        }
    }

    private static Node appendNode(Node head, Show s) {
        Node novo = new Node(s);
        if (head == null)
            return novo;
        Node tail = head;
        while (tail.next != null)
            tail = tail.next;
        tail.next = novo;
        novo.prev = tail;
        return head;
    }

    private static void printList(Node head) {
        for (Node cur = head; cur != null; cur = cur.next) {
            System.out.println(cur.data);
        }
    }

    private static void quicksortList(Node head) {
        Node hi = head;
        while (hi != null && hi.next != null)
            hi = hi.next;
        _quicksort(head, hi);
    }

    private static void _quicksort(Node lo, Node hi) {
        if (lo != null && hi != null && lo != hi && lo != hi.next) {
            Node p = partition(lo, hi);
            _quicksort(lo, p.prev);
            _quicksort(p.next, hi);
        }
    }

    private static Node partition(Node lo, Node hi) {
        Show pivot = hi.data;
        Node i = lo.prev;
        for (Node j = lo; j != hi; j = j.next) {
            comparacoes++;
            if (compareShows(j.data, pivot) <= 0) {
                i = (i == null ? lo : i.next);
                swapNodeData(i, j);
            }
        }
        i = (i == null ? lo : i.next);
        swapNodeData(i, hi);
        return i;
    }

    private static int compareShows(Show a, Show b) {
        if (a.year != b.year)
            return a.year - b.year;
        if (a.month != b.month)
            return a.month - b.month;
        if (a.day != b.day)
            return a.day - b.day;
        return a.title.compareTo(b.title);
    }

    private static void swapNodeData(Node a, Node b) {
        Show tmp = a.data;
        a.data = b.data;
        b.data = tmp;
        movimentacoes++;
    }

    private static void freeList(Node head) {

    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        List<String> ids = new ArrayList<>();
        String line;
        while ((line = stdin.readLine()) != null) {
            line = line.trim();
            if ("FIM".equals(line))
                break;
            if (!line.isEmpty())
                ids.add(line);
        }

        Node list = null;
        try (BufferedReader br = Files.newBufferedReader(Paths.get("/tmp/disneyplus.csv"))) {

            br.readLine();
            while ((line = br.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty())
                    continue;

                String id = extractFirstField(trimmed);
                if (ids.contains(id)) {
                    Show s = Show.parseFromCsv(trimmed);
                    if (s != null) {
                        list = appendNode(list, s);
                    }
                }
            }
        }

        long t0 = System.nanoTime();
        quicksortList(list);
        double tempo = (System.nanoTime() - t0) / 1_000_000_000.0;

        printList(list);

        try (PrintWriter pw = new PrintWriter("763995_quicksort2.txt")) {
            pw.printf("%s\t%d\t%d\t%.6f\n",
                    MATRICULA, comparacoes, movimentacoes, tempo);
        }

        freeList(list);
    }

    private static String extractFirstField(String line) {
        StringBuilder buf = new StringBuilder();
        boolean inQuotes = false;
        for (char c : line.toCharArray()) {
            if (c == '"')
                inQuotes = !inQuotes;
            else if (c == ',' && !inQuotes)
                break;
            else
                buf.append(c);
        }
        return buf.toString().trim();
    }
}
