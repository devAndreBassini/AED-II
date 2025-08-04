import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class pilha09 {
    static class Show {
        String showId;
        String type;
        String title;
        String director;
        List<String> cast;
        String country;
        String dateAdded;
        int releaseYear;
        String rating;
        String duration;
        List<String> listedIn;

        private Show() { }

        public static Show parseFromCsv(String line) {
            String[] rawFields = splitCsvLine(line);
            if (rawFields.length < 11) return null;

            Show s = new Show();
            s.showId      = rawFields[0].isEmpty() ? "NaN" : rawFields[0];
            s.type        = rawFields[1].isEmpty() ? "NaN" : rawFields[1];
            s.title       = rawFields[2].isEmpty() ? "NaN" : rawFields[2];
            s.director    = rawFields[3].isEmpty() ? "NaN" : rawFields[3];
            s.cast        = parseListField(rawFields[4]);
            Collections.sort(s.cast);
            s.country     = rawFields[5].isEmpty() ? "NaN" : rawFields[5];
            s.dateAdded   = rawFields[6].isEmpty() ? "NaN" : rawFields[6];
            s.releaseYear = parseIntSafe(rawFields[7], 0);
            s.rating      = rawFields[8].isEmpty() ? "NaN" : rawFields[8];
            s.duration    = rawFields[9].isEmpty() ? "NaN" : rawFields[9];
            s.listedIn    = parseListField(rawFields[10]);
            Collections.sort(s.listedIn);

            return s;
        }

        public Show cloneShow() {
            Show c = new Show();
            c.showId      = this.showId;
            c.type        = this.type;
            c.title       = this.title;
            c.director    = this.director;
            c.cast        = new ArrayList<>(this.cast);
            c.country     = this.country;
            c.dateAdded   = this.dateAdded;
            c.releaseYear = this.releaseYear;
            c.rating      = this.rating;
            c.duration    = this.duration;
            c.listedIn    = new ArrayList<>(this.listedIn);
            return c;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("=> ")
              .append(showId).append(" ## ")
              .append(title).append(" ## ")
              .append(type).append(" ## ")
              .append(director).append(" ## [")
              .append(String.join(", ", cast)).append("] ## ")
              .append(country).append(" ## ")
              .append(dateAdded).append(" ## ")
              .append(releaseYear).append(" ## ")
              .append(rating).append(" ## ")
              .append(duration).append(" ## [")
              .append(String.join(", ", listedIn)).append("] ##");
            return sb.toString();
        }


        private static String[] splitCsvLine(String line) {
            List<String> fields = new ArrayList<>();
            StringBuilder buf = new StringBuilder();
            boolean inQuotes = false;

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '"') {
                    inQuotes = !inQuotes;
                } else if (c == ',' && !inQuotes) {
                    fields.add(trim(stripSurroundingQuotes(buf.toString())));
                    buf.setLength(0);
                } else {
                    buf.append(c);
                }
            }
            fields.add(trim(stripSurroundingQuotes(buf.toString())));
            return fields.toArray(new String[0]);
        }

        private static String stripSurroundingQuotes(String s) {
            if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
                return s.substring(1, s.length() - 1);
            }
            return s;
        }

        private static String trim(String s) {
            return s == null ? null : s.trim();
        }

        private static List<String> parseListField(String field) {
            if (field == null || field.equals("NaN") || field.isEmpty()) {
                return new ArrayList<>(Collections.singletonList("NaN"));
            }
            String[] parts = field.split(",");
            List<String> out = new ArrayList<>();
            for (String p : parts) {
                out.add(p.trim());
            }
            return out;
        }

        private static int parseIntSafe(String s, int def) {
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                return def;
            }
        }
    }

    private static List<Show> loadDatabase(String path) throws IOException {
        List<Show> db = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Show s = Show.parseFromCsv(line);
                if (s != null) db.add(s);
            }
        }
        return db;
    }

    private static Show findInDatabase(List<Show> db, String id) {
        for (Show s : db) {
            if (s.showId.equals(id)) return s;
        }
        return null;
    }

    public static void main(String[] args) {
        final String CSV_PATH = "/tmp/disneyplus.csv";
        List<Show> database;
        try {
            database = loadDatabase(CSV_PATH);
        } catch (IOException e) {
            System.err.println("Erro ao abrir CSV: " + e.getMessage());
            return;
        }

        Deque<Show> stack = new ArrayDeque<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if ("FIM".equals(line)) break;
            Show orig = findInDatabase(database, line);
            if (orig != null) stack.push(orig.cloneShow());
        }

        if (!sc.hasNextLine()) {sc.close(); return;}
        int nCommands = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < nCommands; i++) {
            if (!sc.hasNextLine()) break;
            String cmd = sc.nextLine().trim();
            if (cmd.isEmpty()) {
                i--; 
                continue;
            }
            if (cmd.startsWith("I ")) {
                String id = cmd.substring(2).trim();
                Show orig = findInDatabase(database, id);
                if (orig != null) {
                    stack.push(orig.cloneShow());
                }
            } else if ("R".equals(cmd)) {
                Show removed = stack.poll();
                if (removed != null) {
                    System.out.println("(R) " + removed.title);
                }
            }
        }

        List<Show> remaining = new ArrayList<>(stack);
        Collections.reverse(remaining);
        for (int idx = remaining.size() - 1; idx >= 0; idx--) {
            System.out.printf("[%d] %s%n", idx, remaining.get(idx).toString());
        }
        
        sc.close();
    }
}
