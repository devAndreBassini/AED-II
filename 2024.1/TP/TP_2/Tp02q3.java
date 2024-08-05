import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tp02q3 {
    private String id;
    private String name;
    private static List<String> alternateNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private static List<String> alternateActors;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    static Tp02q3[] hpc = new Tp02q3[500];
    static Tp02q3[] array = new Tp02q3[100];
    public static int tam = array.length - 1;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            read();

            int indice = -1;

            // Lê parte 1 da entrada (ID)
            while (((input = br.readLine()).equals("FIM")) == false) {
                // Busca por ID no array com todos personagens
                if (!input.equals("FIM")) {
                    indice = searchByID(input);
                }
                // insere no final do outro array por ID
                if (indice >= 0) {
                    insertFinal(indice);
                }

            }
            input = "";
            // Lê parte 2 da entrada (nome)
            // input=br.readLine();
            while (((input = br.readLine()).equals("FIM")) == false) {

                // Pesquisa por nome
                indice = searchByName(input);
                print(indice);
                // input=br.readLine();
            }

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.exit(0);
        }

    }

    public static int searchByID(String input) {
        try {
            int i = 0;
            // Compara a entrada com os elementos do array até encontrar (ou não)
            while (i < hpc.length) {
                // Caso a entrada a entrada seja igual a algum ID será retornada a posição
                if (hpc[i].getId().equals(input)) {
                    return i;
                }
                i++;
            }
            return -1;
        } catch (NullPointerException e) {
            return -1;
        }

    }

    public Tp02q3(String id, String name, List<String> alternateNames, String house, String ancestry, String species,
            String patronus, boolean hogwartsStaff, boolean hogwartsStudent, String actorName,
            boolean alive, List<String> alternateActors, LocalDate dateOfBirth, int yearOfBirth, String eyeColour,
            String gender,
            String hairColour, boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternateNames = alternateNames;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.alternateActors = alternateActors;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    public Tp02q3() {
        this.id = "";
        this.name = "";
        this.alternateNames = null;
        this.house = "";
        this.ancestry = "";
        this.species = "";
        this.patronus = "";
        this.hogwartsStaff = false;
        this.hogwartsStudent = false;
        this.actorName = "";
        this.alive = false;
        this.alternateActors = null;
        this.dateOfBirth = LocalDate.of(0000, 01, 01);
        this.yearOfBirth = 0000;
        this.eyeColour = "";
        this.gender = "";
        this.hairColour = "";
        this.wizard = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public List getAlternateNames() {
        return alternateNames;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getHouse() {
        return house;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public String getPatronous() {
        return patronus;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public boolean getHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlternateActors(List<String> alternateActors) {
        this.alternateActors = alternateActors;
    }

    public List getAlternateActors() {
        return alternateActors;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    public boolean getWizard() {
        return wizard;
    }

    public Tp02q3 clone() {
        return new Tp02q3(id, name, new ArrayList<>(alternateNames), house, ancestry, species, patronus,
                hogwartsStaff, hogwartsStudent, actorName, alive, alternateActors, dateOfBirth, yearOfBirth, eyeColour,
                gender,
                hairColour, wizard);
    }

    public static void print(int indice) {
        if (indice >= 0) {

            System.out.println("SIM");
        } else {

            System.out.println("NAO");
        }

    }

    public static void read() {
        try {

            String file = "/tmp/characters.csv";
            String line;
            int k = 0;

            // Abre o arquivo, lê e armazena no buffer
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(";", -1);

                List<String> tmp_alternates = new ArrayList<>();
                List<String> tmp_actors = new ArrayList<>();

                String[] tmp = attributes[2].split(",");
                for (int j = 0; j < tmp.length - 1; j++) {

                    tmp_alternates.add(tmp[j]);
                    j++;
                }

                String[] tmp2 = attributes[11].split(",");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String[] tmp3 = attributes[12].split("-");
                for (int i = 0; i < tmp3.length; i++) {
                    if (tmp3[i].length() < 2) {
                        tmp3[i] = "0" + tmp3[i];
                    }
                }

                attributes[12] = tmp3[0] + "-" + tmp3[1] + "-" + tmp3[2];
                LocalDate data = LocalDate.parse(attributes[12], formatter);
                int year = Integer.parseInt(attributes[13]);

                hpc[k] = new Tp02q3(
                        attributes[0], // id
                        attributes[1], // name
                        tmp_alternates, // alternate names
                        attributes[3], // house
                        attributes[4], // ancestry
                        attributes[5], // species
                        attributes[6], // patronous
                        stringToBoolean(attributes[7]), // hogwartsStaff
                        stringToBoolean(attributes[8]), // hogwartsStudent
                        attributes[9], // actorName
                        stringToBoolean(attributes[10]), // alive
                        tmp_actors, // alternate actors
                        data, // dateOfBirth
                        year, // yearOfBirth
                        attributes[14], // eyeColour
                        attributes[15], // gender
                        attributes[16], // haircolour
                        stringToBoolean(attributes[17]) // wizard
                );

                k++;

            }

            br.close();
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("");
        }

    }

    public static boolean stringToBoolean(String attribute) {
        if (attribute.equals("VERDADEIRO")) {
            return true;
        }
        return false;
    }

    public static int searchByName(String input) {
        try {
            int i = 0;
            // Compara a entrada com os elementos do array até encontrar (ou não)
            while (i < hpc.length) {
                // Caso a entrada a entrada seja igual a algum nome será retornada a posição
                if (array[i].getName().equals(input)) {
                    return i;
                }
                i++;
            }
            return -1;
        } catch (NullPointerException e) {
            // e.printStackTrace();
            return -1;
        }

    }

    public static void insertFinal(int indice) {
        for (int i = 0; i < tam; i++) {
            if (array[i] == null) {
                array[i] = hpc[indice];
                i = tam;
            }
        }

        // System.out.println(array[tam-i]);
    }

}