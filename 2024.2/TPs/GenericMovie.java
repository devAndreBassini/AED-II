import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

class Movie {
    SimpleDateFormat ddf = new SimpleDateFormat("MMMM dd, YYYY", Locale.ENGLISH);

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
    public String getShowId() { return this.show_id; }
    public String getType() { return this.type; }
    public String getTitle() { return this.title; }
    public String getDirector() { return this.director; }
    public String getCountry() { return this.country; }
    public Date getDateAdded() { return this.date_added; }
    public int getReleaseYear() { return this.release_year; }
    public String getRating() { return this.rating; }
    public String getDuration() { return this.duration; }
    public String getDescription() { return this.description; }
    public String getCast() {

        // Construct string e.g. [item1, item2, item3]
        String cast = "[";

        for (int i = 0; i < this.cast.size(); i++) {
            cast += this.cast.get(i);
            if (i < this.cast.size() - 1) cast += ", ";
        }

        cast += "]";

        return cast;
    }
    public String getListedIn() {

        // Construct string e.g. [item1, item2, item3]
        String listed_in = "[";

        for (int i = 0; i < this.listed_in.size(); i++) {
            listed_in += this.listed_in.get(i);
            if (i < this.listed_in.size() - 1) listed_in += ", ";
        }

        listed_in += "]";

        return listed_in;
    }

    // Sets
    public void setShowId(String show_id) { this.show_id = show_id; }
    public void setType(String type) { this.type = type; }
    public void setTitle(String title) { this.title = title; }
    public void setDirector(String director) { this.director = director; }
    public void setCountry(String country) { this.country = country; }
    public void setDateAdded(Date date_added) { this.date_added = date_added; }
    public void setReleaseYear(int release_year) { this.release_year = release_year; }
    public void setRating(String rating) { this.rating = rating; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setDescription(String description) { this.description = description; }
    public void setCast(ArrayList<String> cast) { this.cast = cast; }
    public void setListedIn(ArrayList<String> listed_in) { this.listed_in = listed_in; }
    
    // Clone
    public Movie clone() {
        return new Movie(this.show_id, this.type, this.title, this.director, this.cast, this.country, this.date_added, this.release_year, this.rating, this.duration, this.listed_in, this.description);
    }

    // Extract Elements
    public static ArrayList<String> extractElements(String input) {
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

    // Print
    public void print() {
        System.out.println("=> " + this.getShowId() + " ## " + this.getTitle() + " ## " + this.getType() + " ## "
                + (this.getDirector() == "" ? "NaN" : this.getDirector()) + " ## "
                + (this.getCast() == "" ? "NaN" : this.getCast()) + " ## "
                + (this.getCountry() == "" ? "NaN" : this.getCountry()) + " ## "
                + (this.getDateAdded() == null ? "NaN" : ddf.format(this.getDateAdded())) + " ## "
                + (this.getReleaseYear() == 0 ? "NaN" : this.getReleaseYear()) + " ## "
                + (this.getRating() == "" ? "NaN" : this.getRating()) + " ## "
                + (this.getDuration() == "" ? "NaN" : this.getDuration()) + " ## "
                + (this.getListedIn() == "" ? "NaN" : this.getListedIn()) + " ##");
    }

    // Set values from string
    public void read(String line) {

        // Start variables
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
        this.setCast(extractElements(line.substring(positionStart, positionEnd)));

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
        this.setListedIn(extractElements(line.substring(positionStart, positionEnd)));

        // Set description
        positionStart = positionEnd + 1;
        this.setDescription(line.substring(positionStart, line.length()));
    }

    // Search by show_id function
    public static Movie searchByShowId(String show_id, ArrayList<Movie> movies) {

        // Search for movie
        for (int i = 0; i < movies.size(); i++) {

            if (movies.get(i).getShowId().equals(show_id)) return movies.get(i);
        }
        return null;
    }

    // Read all movies function
    public static void startMovies(FileInputStream file) {

        // Initialize variables
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(file));

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
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}

public class GenericMovie extends Movie{
    public static FileInputStream checkFile(String[] PATHS){
        FileInputStream fstream= null;
        for (String PATH : PATHS) {
            try {
                fstream = new FileInputStream(PATH);
                break;
            } catch (FileNotFoundException e) { /* Try next path */  }
        }
        return fstream;
    }

    public static void main(String[] args) throws Exception {
        // #0 - Check computer path and Verde path
        String[] PATHS = {"/tmp/disneyplus.csv", "disneyplus.csv"};

        FileInputStream file = checkFile(PATHS);

        if(file == null) System.err.println("File not Found.");

        // #1 - Reads all movies in CSV file
        startMovies(file);

        // #2 - Print all movies
        for (Movie mv : allMovies) {
            mv.print();
        }


    }
}
