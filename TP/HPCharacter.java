import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HPCharacter {
    private String id;
    private String name;
    private List<String> alternateNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private String hogwartsStudent;
    private String actorName;
    private boolean alive;
    private String dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    HPCharacter[] HPC = new HPCharacter[500];

    public static void main(String[] args){
        read(HPC);
    }

    public HPCharacter(String id, String name, List<String> alternateNames, String house, String ancestry, String species,
                      String patronus, boolean hogwartsStaff, String hogwartsStudent, String actorName,
                      boolean alive, String dateOfBirth, int yearOfBirth, String eyeColour, String gender,
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
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public void setHogwartsStudent(String hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    public HPCharacter clone() {
        return new HPCharacter(id, name, new ArrayList<>(alternateNames), house, ancestry, species, patronus,
                hogwartsStaff, hogwartsStudent, actorName, alive, dateOfBirth, yearOfBirth, eyeColour, gender,
                hairColour, wizard);
    }

    public void imprimir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + name);
        System.out.println("Nomes alternativos: " + alternateNames);
        System.out.println("Casa: " + house);
        System.out.println("Ascendência: " + ancestry);
        System.out.println("Espécie: " + species);
        System.out.println("Patronus: " + patronus);
        System.out.println("Professor de Hogwarts: " + hogwartsStaff);
        System.out.println("Estudante de Hogwarts: " + hogwartsStudent);
        System.out.println("Nome do ator: " + actorName);
        System.out.println("Vivo: " + alive);
        System.out.println("Data de nascimento: " + dateOfBirth);
        System.out.println("Ano de nascimento: " + yearOfBirth);
        System.out.println("Cor dos olhos: " + eyeColour);
        System.out.println("Gênero: " + gender);
        System.out.println("Cor do cabelo: " + hairColour);
        System.out.println("Bruxo: " + wizard);
    }

    public void read(HPCharacter[] HPC) {
        try{
            String file = "characters.csv";
            String line;
            int k=0;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            line = br.readLine();
            while (!line.equals(null)) {
                line = br.readLine();
                String[] attributes = line.split(",",-1);
                HPC[k].id = attributes[0];
                HPC[k].name = attributes[1];
                // Se alternateNames for uma lista, você precisa criar uma nova lista e adicionar os elementos a ela
                int i=2,j=0;
                String[] tmp_alternates = {};
                while (!attributes[i].contains("]")) {
                    tmp_alternates[j]=attributes[i];
                    alternateNames.add(tmp_alternates[j]);
                    i++;j++;
                }
               
                HPC[k].house = attributes[i+1];
                HPC[k].ancestry = attributes[i+2];
                HPC[k].species = attributes[i+3];
                HPC[k].patronus = attributes[i+4];
                // Se hogwartsStaff for booleano, você precisa converter a string para boolean
                boolean hogwartsStaff = stringToBoolean(attributes[i+5]);
                HPC[k].hogwartsStaff = hogwartsStaff;
                HPC[k].hogwartsStudent = attributes[i+6];
                HPC[k].actorName = attributes[i+7];
                // Se alive for booleano, você precisa converter a string para boolean
                boolean alive = stringToBoolean(attributes[i+8]);
                HPC[k].alive = alive;
                HPC[k].dateOfBirth = attributes[i+9];
                // Se yearOfBirth for inteiro, você precisa converter a string para int
                HPC[k].yearOfBirth = Integer.parseInt(attributes[i+9]);
                HPC[k].eyeColour = attributes[i+10];
                HPC[k].gender = attributes[i+11];
                HPC[k].hairColour = attributes[i+12];
                boolean wizard = stringToBoolean(attributes[i+13]);
                // Se wizard for booleano, você precisa converter a string para boolean
                HPC[k].wizard = wizard;
                k++;
            }

            br.close();
        }
        catch(IOException e){
            System.out.println("Erro: "+e.getMessage());
        }
    }

    public boolean stringToBoolean(String attribute){
        if(attribute.equals("True")){
            return true;
        }
        return false;
    }
}
