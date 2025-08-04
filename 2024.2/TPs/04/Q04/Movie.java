import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Movie {

    static SimpleDateFormat ddf = new SimpleDateFormat("MMMM dd, YYYY", Locale.ENGLISH);

    // Global variables
    public static final String FILE_PATH = "/tmp/disneyplus.csv";
    // public static final String FILE_PATH = "../disneyplus.csv";
    public static ArrayList<Movie> allMovies = new ArrayList<Movie>();

    // Attributes
    private String show_id;
    private String type;
    private String title;
    private String director;
    private ArrayList<String> cast;
    private String country;
    private Date date_added;
    private int release_year;
    private String rating;
    private String duration;
    private ArrayList<String> listed_in;
    private String description;

    // Empty constructor
    public Movie() {
        this.show_id = "";
        this.type = "";
        this.title = "";
        this.director = "";
        this.cast = new ArrayList<String>();
        this.country = "";
        this.date_added = null;
        this.release_year = 0;
        this.rating = "";
        this.duration = "";
        this.listed_in = new ArrayList<String>();
        this.description = "";
    }

    // Constructor
    public Movie(String show_id, String type, String title, String director, ArrayList<String> cast, String country,
            Date date_added, int release_year, String rating, String duration, ArrayList<String> listed_in,
            String description) {
        this.show_id = show_id;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.date_added = date_added;
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration;
        this.listed_in = listed_in;
        this.description = description;
    }

    // Gets
    public String getShowId() {
        return this.show_id;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDirector() {
        return this.director;
    }

    public String getCountry() {
        return this.country;
    }

    public Date getDateAdded() {
        return this.date_added;
    }

    public int getReleaseYear() {
        return this.release_year;
    }

    public String getRating() {
        return this.rating;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCast() {

        // Construct string e.g. [item1, item2, item3]
        String cast = "[";

        for (int i = 0; i < this.cast.size(); i++) {

            cast += this.cast.get(i);

            if (i < this.cast.size() - 1)
                cast += ", ";
        }

        cast += "]";

        return cast;
    }

    public String getListedIn() {

        // Construct string e.g. [item1, item2, item3]
        String listed_in = "[";

        for (int i = 0; i < this.listed_in.size(); i++) {

            listed_in += this.listed_in.get(i);

            if (i < this.listed_in.size() - 1)
                listed_in += ", ";
        }

        listed_in += "]";

        return listed_in;
    }

    // Sets
    public void setShowId(String show_id) {
        this.show_id = show_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDateAdded(Date date_added) {
        this.date_added = date_added;
    }

    public void setReleaseYear(int release_year) {
        this.release_year = release_year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public void setListedIn(ArrayList<String> listed_in) {
        this.listed_in = listed_in;
    }

    // Clone
    public Movie clone() {
        return new Movie(this.show_id, this.type, this.title, this.director, this.cast, this.country, this.date_added,
                this.release_year, this.rating, this.duration, this.listed_in, this.description);
    }

    // Print
    public void print() {
        System.out.println("=> "
                + this.getShowId() + " ## "
                + this.getTitle() + " ## "
                + this.getType() + " ## "
                + (this.getDirector() == "" ? "NaN" : this.getDirector()) + " ## "
                + (this.getCast() == "" ? "NaN" : this.getCast()) + " ## "
                + (this.getCountry() == "" ? "NaN" : this.getCountry()) + " ## "
                + (this.getDateAdded() == null ? "NaN" : ddf.format(this.getDateAdded())) + " ## "
                + (this.getReleaseYear() == 0 ? "NaN" : this.getReleaseYear()) + " ## "
                + (this.getRating() == "" ? "NaN" : this.getRating()) + " ## "
                + (this.getDuration() == "" ? "NaN" : this.getDuration()) + " ## "
                + (this.getListedIn() == "" ? "NaN" : this.getListedIn()) + " ##");
    }

    // Extract list
    public static ArrayList<String> extractNames(String input) {
        ArrayList<String> str = new ArrayList<>();

        if (!input.startsWith("\"")) {
            str.add(input);
            return str;
        }

        // Clean input
        String cleanedInput = input.substring(1, input.length() - 1);

        // Split input by comma and space
        String[] parts = cleanedInput.split(", ");

        for (String part : parts)
            str.add(part);

        return str;
    }

    // Read
    public void read(String line) {

        // Start position
        int positionStart = 0;
        int positionEnd = 0;

        // ---------------------- //

        // Set show_id
        positionEnd = line.indexOf(",", positionStart);
        this.setShowId(line.substring(positionStart, positionEnd));

        // Set type
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        this.setType(line.substring(positionStart, positionEnd));

        // Set title
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        if (line.substring(positionStart, positionEnd).startsWith("\""))
            positionEnd = line.indexOf("\",", positionStart) + 1;
        this.setTitle(line.substring(positionStart, positionEnd));

        // Set director
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        if (line.substring(positionStart, positionEnd).startsWith("\""))
            positionEnd = line.indexOf("\",", positionStart) + 1;
        this.setDirector(line.substring(positionStart, positionEnd));

        // Set cast
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        if (line.substring(positionStart, positionEnd).startsWith("\""))
            positionEnd = line.indexOf("\",", positionStart) + 1;
        this.setCast(extractNames(line.substring(positionStart, positionEnd)));

        // Set country
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        if (line.substring(positionStart, positionEnd).startsWith("\""))
            positionEnd = line.indexOf("\",", positionStart) + 1;
        this.setCountry(line.substring(positionStart, positionEnd));

        // Set date_added
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        if (line.substring(positionStart, positionEnd).startsWith("\"")) {
            positionStart += 1;
            positionEnd = line.indexOf("\",", positionStart) + 1;
        }
        try {
            this.setDateAdded(ddf.parse(line.substring(positionStart, positionEnd)));
        } catch (Exception e) {
            this.setDateAdded(null);
        }

        // Set release_year
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        if (line.substring(positionStart, positionEnd).charAt(0) == '\"')
            positionEnd = line.indexOf("\",", positionStart) + 1;
        try {
            this.setReleaseYear(Integer.parseInt(line.substring(positionStart, positionEnd)));
        } catch (Exception e) {
            this.setReleaseYear(0);
        }

        // Set rating
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        this.setRating(line.substring(positionStart, positionEnd));

        // Set duration
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        this.setDuration(line.substring(positionStart, positionEnd));

        // Set listed_in
        positionStart = positionEnd + 1;
        positionEnd = line.indexOf(",", positionStart);
        if (line.substring(positionStart, positionEnd).startsWith("\""))
            positionEnd = line.indexOf("\",", positionStart) + 1;
        this.setListedIn(extractNames(line.substring(positionStart, positionEnd)));

        // Set description
        positionStart = positionEnd + 1;
        this.setDescription(line.substring(positionStart, line.length()));
    }

    // Read all movies function
    public static void startMovies() {

        // Initialize variables
        try {

            FileInputStream fstream = new FileInputStream(FILE_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            // ---------------------- //

            // Explode CSV file
            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                // Initialize movie
                Movie movie = new Movie();

                // Read line
                movie.read(line);

                // Add movie to array
                allMovies.add(movie);
            }

            // Close CSV file
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    // Search by show_id function
    public static Movie searchByShowId(String show_id, ArrayList<Movie> movies) {

        // Search for movie
        for (int i = 0; i < movies.size(); i++) {

            if (movies.get(i).getShowId().equals(show_id))
                return movies.get(i);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        startMovies();
        Scanner sc = new Scanner(System.in);

        class No {
            Movie movie;
            No esq, dir;
            boolean cor;

            public No(Movie movie) {
                this.movie = movie;
                this.cor = true; // novo nó sempre vermelho
            }
        }

        class Alvinegra {
            private final boolean RED = true;
            private final boolean BLACK = false;
            private No raiz = null;
            private int comparacoes = 0;

            private boolean isRed(No no) {
                return (no != null) && (no.cor == RED);
            }

            private No rotacionarEsq(No h) {
                No x = h.dir;
                h.dir = x.esq;
                x.esq = h;
                x.cor = h.cor;
                h.cor = RED;
                return x;
            }

            private No rotacionarDir(No h) {
                No x = h.esq;
                h.esq = x.dir;
                x.dir = h;
                x.cor = h.cor;
                h.cor = RED;
                return x;
            }

            private void trocarCores(No h) {
                h.cor = RED;
                if (h.esq != null)
                    h.esq.cor = BLACK;
                if (h.dir != null)
                    h.dir.cor = BLACK;
            }

            public void inserir(Movie movie) {
                raiz = inserir(raiz, movie);
                if (raiz != null)
                    raiz.cor = BLACK;
            }

            private No inserir(No h, Movie movie) {
                if (h == null)
                    return new No(movie);

                comparacoes++;
                String atual = movie.getTitle().replaceAll("\\s", "").toLowerCase();
                String noAtual = h.movie.getTitle().replaceAll("\\s", "").toLowerCase();

                if (atual.compareTo(noAtual) < 0)
                    h.esq = inserir(h.esq, movie);
                else if (atual.compareTo(noAtual) > 0)
                    h.dir = inserir(h.dir, movie);
                else
                    return h;

                if (isRed(h.dir) && !isRed(h.esq))
                    h = rotacionarEsq(h);
                if (isRed(h.esq) && isRed(h.esq.esq))
                    h = rotacionarDir(h);
                if (isRed(h.esq) && isRed(h.dir))
                    trocarCores(h);

                return h;
            }

            public String pesquisar(String titulo) {
                comparacoes = 0;
                StringBuilder caminho = new StringBuilder("=>raiz");
                boolean achou = pesquisar(raiz, titulo.replaceAll("\\s", "").toLowerCase(), caminho);
                return caminho.toString() + (achou ? " SIM" : " NAO");
            }

            private boolean pesquisar(No no, String titulo, StringBuilder caminho) {
                if (no == null)
                    return false;

                comparacoes++;
                String atual = no.movie.getTitle().replaceAll("\\s", "").toLowerCase();
                int cmp = titulo.compareTo(atual);

                if (cmp == 0)
                    return true;
                else if (cmp < 0) {
                    caminho.append(" esq");
                    return pesquisar(no.esq, titulo, caminho);
                } else {
                    caminho.append(" dir");
                    return pesquisar(no.dir, titulo, caminho);
                }
            }

            public int getComparacoes() {
                return comparacoes;
            }
        }

        Alvinegra arvore = new Alvinegra();

        // Inserção
        String entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            Movie m = Movie.searchByShowId(entrada, Movie.allMovies);
            if (m != null) {
                if (m.getTitle().startsWith("\"") && m.getTitle().endsWith("\""))
                    m.setTitle(m.getTitle().substring(1, m.getTitle().length() - 1));
                arvore.inserir(m);
            }
            entrada = sc.nextLine();
        }

        long inicio = System.nanoTime();

        // Pesquisa
        entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            System.out.println(arvore.pesquisar(entrada));
            entrada = sc.nextLine();
        }

        long fim = System.nanoTime();
        double tempo = (fim - inicio) / 1e9;

        // Log
        BufferedWriter bw = new BufferedWriter(new FileWriter("763995_avinegra.txt"));
        bw.write("763995\t" + tempo + "\t" + arvore.getComparacoes());
        bw.close();

        sc.close();
    }
}