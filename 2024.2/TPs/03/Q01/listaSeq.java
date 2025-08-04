import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.HashMap;

public class listaSeq {
    private String show_id;
    private String type;
    private String title;
    private String director;
    private String[] cast;
    private String country;
    private Date date_added;
    private int release_year;
    private String rating;
    private String duration;
    private String[] listed_in;

    public listaSeq() {
        this.show_id = "NaN";
        this.type = "NaN";
        this.title = "NaN";
        this.director = "NaN";
        this.cast = new String[] { "NaN" };
        this.country = "NaN";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
            this.date_added = sdf.parse("March 1, 1900");
        } catch (ParseException e) {
            this.date_added = new Date();
        }
        this.release_year = 0;
        this.rating = "NaN";
        this.duration = "NaN";
        this.listed_in = new String[] { "NaN" };
    }

    public listaSeq(String show_id, String type, String title, String director, String[] cast,
            String country, Date date_added, int release_year, String rating, String duration, String[] listed_in) {
        this.show_id = (show_id == null || show_id.trim().isEmpty()) ? "NaN" : show_id;
        this.type = (type == null || type.trim().isEmpty()) ? "NaN" : type;
        this.title = (title == null || title.trim().isEmpty()) ? "NaN" : title;
        this.director = (director == null || director.trim().isEmpty()) ? "NaN" : director;
        if (cast == null || cast.length == 0) {
            this.cast = new String[] { "NaN" };
        } else {
            this.cast = cast.clone();
            Arrays.sort(this.cast);
        }
        this.country = (country == null || country.trim().isEmpty()) ? "NaN" : country;
        if (date_added == null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
                this.date_added = sdf.parse("March 1, 1900");
            } catch (ParseException e) {
                this.date_added = new Date();
            }
        } else {
            this.date_added = (Date) date_added.clone();
        }
        this.release_year = release_year;
        this.rating = (rating == null || rating.trim().isEmpty()) ? "NaN" : rating;
        this.duration = (duration == null || duration.trim().isEmpty()) ? "NaN" : duration;
        if (listed_in == null || listed_in.length == 0) {
            this.listed_in = new String[] { "NaN" };
        } else {
            this.listed_in = listed_in.clone();
            Arrays.sort(this.listed_in);
        }
    }

    public String getShowId() {
        return show_id;
    }

    public void setShowId(String show_id) {
        this.show_id = (show_id == null || show_id.trim().isEmpty()) ? "NaN" : show_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = (type == null || type.trim().isEmpty()) ? "NaN" : type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = (title == null || title.trim().isEmpty()) ? "NaN" : title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = (director == null || director.trim().isEmpty()) ? "NaN" : director;
    }

    public String[] getCast() {
        return cast.clone();
    }

    public void setCast(String[] cast) {
        if (cast == null || cast.length == 0)
            this.cast = new String[] { "NaN" };
        else {
            this.cast = cast.clone();
            Arrays.sort(this.cast);
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = (country == null || country.trim().isEmpty()) ? "NaN" : country;
    }

    public Date getDateAdded() {
        return (date_added != null) ? (Date) date_added.clone() : null;
    }

    public void setDateAdded(Date date_added) {
        if (date_added == null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
                this.date_added = sdf.parse("March 1, 1900");
            } catch (ParseException e) {
                this.date_added = new Date();
            }
        } else {
            this.date_added = (Date) date_added.clone();
        }
    }

    public int getReleaseYear() {
        return release_year;
    }

    public void setReleaseYear(int release_year) {
        this.release_year = release_year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = (rating == null || rating.trim().isEmpty()) ? "NaN" : rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = (duration == null || duration.trim().isEmpty()) ? "NaN" : duration;
    }

    public String[] getListedIn() {
        return listed_in.clone();
    }

    public void setListedIn(String[] listed_in) {
        if (listed_in == null || listed_in.length == 0)
            this.listed_in = new String[] { "NaN" };
        else {
            this.listed_in = listed_in.clone();
            Arrays.sort(this.listed_in);
        }
    }

    public listaSeq clone() {
        String[] cloneCast = (cast != null) ? cast.clone() : new String[] { "NaN" };
        Arrays.sort(cloneCast);
        String[] cloneListedIn = (listed_in != null) ? listed_in.clone() : new String[] { "NaN" };
        Arrays.sort(cloneListedIn);
        Date cloneDate = (date_added != null) ? (Date) date_added.clone() : null;
        return new listaSeq(show_id, type, title, director, cloneCast, country,
                cloneDate, release_year, rating, duration, cloneListedIn);
    }

    public static String fixEncoding(String s) {
        if (s == null)
            return null;
        try {

            return new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            return s;
        }
    }

    public void imprimir() {
        StringBuilder sb = new StringBuilder();
        sb.append("=> ").append(fixEncoding(show_id)).append(" ## ");

        String tituloFormatado = fixEncoding(title).replace("\"\"", "").trim();
        sb.append(tituloFormatado).append(" ## ");

        sb.append(fixEncoding(type)).append(" ## ");
        sb.append(fixEncoding(director)).append(" ## ");

        sb.append("[");
        for (int i = 0; i < cast.length; i++) {
            sb.append(fixEncoding(cast[i]));
            if (i < cast.length - 1)
                sb.append(", ");
        }
        sb.append("] ## ");

        sb.append(fixEncoding(country)).append(" ## ");

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
        sb.append((date_added != null) ? sdf.format(date_added) : "NaN").append(" ## ");

        sb.append(release_year).append(" ## ");
        sb.append(fixEncoding(rating)).append(" ## ");
        sb.append(fixEncoding(duration)).append(" ## ");

        sb.append("[");
        for (int i = 0; i < listed_in.length; i++) {
            sb.append(fixEncoding(listed_in[i]));
            if (i < listed_in.length - 1)
                sb.append(", ");
        }
        sb.append("] ##");

        MyIO.println(sb.toString());
    }

    public static listaSeq lerCSV(String line) {

        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] fields = line.split(regex, -1);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
            if (fields[i].startsWith("\"") && fields[i].endsWith("\"") && fields[i].length() >= 2) {
                fields[i] = fields[i].substring(1, fields[i].length() - 1);
            }
            if (fields[i].isEmpty()) {
                fields[i] = "NaN";
            }
        }

        String id = fields[0];
        String type = fields[1];
        String title = fields[2];
        String director = fields[3];

        String[] cast;
        if (fields[4].equals("NaN")) {
            cast = new String[] { "NaN" };
        } else {
            cast = fields[4].split(",");
            for (int i = 0; i < cast.length; i++) {
                cast[i] = cast[i].trim();
            }
            Arrays.sort(cast);
        }

        String country = fields[5];

        Date date_added = null;
        if (fields[6].equals("NaN")) {
            try {
                SimpleDateFormat sdfDef = new SimpleDateFormat("MMMM d, yyyy");
                date_added = sdfDef.parse("March 1, 1900");
            } catch (ParseException e) {
                date_added = null;
            }
        } else {
            try {
                SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
                date_added = sdfInput.parse(fields[6]);
            } catch (ParseException e) {
                try {
                    SimpleDateFormat sdfDef = new SimpleDateFormat("MMMM d, yyyy");
                    date_added = sdfDef.parse(fields[6]);
                } catch (ParseException ex) {
                    try {
                        SimpleDateFormat sdfDef = new SimpleDateFormat("MMMM d, yyyy");
                        date_added = sdfDef.parse("March 1, 1900");
                    } catch (ParseException exc) {
                        date_added = null;
                    }
                }
            }
        }

        int release_year = 0;
        try {
            release_year = Integer.parseInt(fields[7]);
        } catch (NumberFormatException e) {
            release_year = 0;
        }

        String rating = fields[8];
        String duration = fields[9];

        String[] listed_in;
        if (fields[10].equals("NaN")) {
            listed_in = new String[] { "NaN" };
        } else {
            listed_in = fields[10].split(",");
            for (int i = 0; i < listed_in.length; i++) {
                listed_in[i] = listed_in[i].trim();
            }
            Arrays.sort(listed_in);
        }

        return new listaSeq(id, type, title, director, cast, country, date_added,
                release_year, rating, duration, listed_in);
    }

    public static ArrayList<listaSeq> loadShows(String filePath) {
        ArrayList<listaSeq> shows = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath), "UTF-8");

            if (sc.hasNextLine())
                sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.trim().isEmpty()) {
                    listaSeq s = lerCSV(line);
                    shows.add(s);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + filePath);
        }
        return shows;
    }

    public static void main(String[] args) {

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            System.err.println("Erro ao forçar UTF-8 na saída.");
        }

        ArrayList<listaSeq> shows = loadShows("/tmp/disneyplus.csv");

        HashMap<String, listaSeq> database = new HashMap<>();
        for (listaSeq s : shows) {
            database.put(s.getShowId(), s);
        }

        Scanner scanner = new Scanner(System.in, "UTF-8");
        ArrayList<listaSeq> list = new ArrayList<>();

        while (true) {
            String entrada = scanner.nextLine().trim();
            if (entrada.equals("FIM")) {
                break;
            }

            if (database.containsKey(entrada)) {
                list.add(database.get(entrada).clone());
            }
        }

        int n = Integer.parseInt(scanner.nextLine().trim());
        int counter = 1;

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            String[] tokens = line.split(" ");
            String cmd = tokens[0];

            switch (cmd) {
                case "II": {

                    String id = tokens[1];
                    if (database.containsKey(id)) {
                        list.add(0, database.get(id).clone());
                    }
                    break;
                }
                case "IF": {

                    String id = tokens[1];
                    if (database.containsKey(id)) {
                        list.add(database.get(id).clone());
                    }
                    break;
                }
                case "I*": {

                    int pos = Integer.parseInt(tokens[1]);
                    String id = tokens[2];
                    if (database.containsKey(id)) {
                        if (pos < 0)
                            pos = 0;
                        if (pos > list.size())
                            pos = list.size();
                        list.add(pos, database.get(id).clone());
                    }
                    break;
                }
                case "RI": {

                    if (!list.isEmpty()) {
                        listaSeq removed = list.remove(0);

                        MyIO.println("(R) " + removed.getTitle());

                        counter++;
                    }
                    break;
                }
                case "RF": {

                    if (!list.isEmpty()) {
                        listaSeq removed = list.remove(list.size() - 1);

                        MyIO.println("(R) " + removed.getTitle());

                        counter++;
                    }
                    break;
                }
                case "R*": {

                    int pos = Integer.parseInt(tokens[1]);
                    if (!list.isEmpty()) {
                        if (pos < 0)
                            pos = 0;
                        if (pos >= list.size())
                            pos = list.size() - 1;
                        listaSeq removed = list.remove(pos);

                        MyIO.println("(R) " + removed.getTitle());

                        counter++;
                    }
                    break;
                }
                default:

                    break;
            }
        }

        for (listaSeq s : list) {

            s.imprimir();

            counter++;
        }

        scanner.close();
    }
}
