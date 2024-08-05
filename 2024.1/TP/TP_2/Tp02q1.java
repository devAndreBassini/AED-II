import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Date {
    int day;
    int month;

    Date(int day, int month) {
        this.day = day;
        this.month = month;
    }
}

class Persona {
    String id;
    String name;
    String alternate_names;
    String house;
    String ancestry;
    String species;
    String patrounus;
    boolean hogwartsStaff;
    String hogwartsStudent;
    String actorName;
    boolean alive;
    Date dateOfBirth;
    int yearOfBirth;
    String eyeColour;
    String hairColour;
    boolean wizard;
}

public class Tp02q1 {
    private static final String FILE_NAME = "/tmp/characters.csv";
    private static final int MAX_SIZE = 300;

    private static BufferedReader file;

    public static void main(String[] args) {
        try {
            file = new BufferedReader(new FileReader(FILE_NAME));

            int linesAmount = lineCounter();
            Persona[] personas = new Persona[linesAmount];

            for (int line = 0; line < linesAmount; line++) {
                personas[line] = readInfos(line);
            }

            
            for (int i = 0; i < linesAmount; i++) {
                printOutput(personas[i]);
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int lineCounter() throws IOException {
        int amount = 0;

        file = new BufferedReader(new FileReader(FILE_NAME));

        while (file.readLine() != null) {
            amount++;
        }

        file.close();

        return amount;
    }

    private static Persona readInfos(int line) throws IOException {
        if (line == 0)
            file.readLine(); // Ignorar a primeira linha

        String[] booleanReader = new String[MAX_SIZE];
        Persona persona = new Persona();

        persona.hogwartsStaff = false;
        persona.alive = false;
        persona.wizard = false;

        String[] data = file.readLine().split(";");

        persona.id = data[0];
        persona.name = data[1];
        persona.alternate_names = data[2];
        persona.house = data[3];
        persona.ancestry = data[4];
        persona.species = data[5];
        persona.patrounus = data[6];
        booleanReader[0] = data[7];
        if (booleanReader[0].equals("VERDADEIRO")) {
            persona.hogwartsStaff = true;
        }
        persona.hogwartsStudent = data[8];
        persona.actorName = data[9];
        booleanReader[1] = data[10];
        if (booleanReader[1].equals("VERDADEIRO")) {
            persona.alive = true;
        }
        persona.dateOfBirth = new Date(Integer.parseInt(data[11].substring(0, 2)),
                                        Integer.parseInt(data[11].substring(3, 5)));
        persona.yearOfBirth = Integer.parseInt(data[12]);
        persona.eyeColour = data[13];
        persona.hairColour = data[14];
        booleanReader[2] = data[15];
        if (booleanReader[2].equals("VERDADEIRO")) {
            persona.wizard = true;
        }

        return persona;
    }

    private static void printOutput(Persona persona) {
        System.out.print(persona.id);
        System.out.print(" ## ");
        System.out.print(persona.name);
        System.out.print(" ## ");
        System.out.print(persona.alternate_names); // LISTA
        System.out.print(" ## ");
        System.out.print(persona.house);
        System.out.print(" ## ");
        System.out.print(persona.ancestry);
        System.out.print(" ## ");
        System.out.print(persona.species);
        System.out.print(" ## ");
        System.out.print(persona.patrounus);
        System.out.print(" ## ");
        System.out.print(persona.hogwartsStaff ? "true" : "false"); // STAFF
        System.out.print(" ## ");
        System.out.print(persona.hogwartsStudent);
        System.out.print(" ## ");
        System.out.print(persona.actorName);
        System.out.print(" ## ");
        System.out.print(persona.alive ? "true" : "false"); // ALIVE
        System.out.print(persona.dateOfBirth.day + "-" + persona.dateOfBirth.month + "-" + persona.yearOfBirth);
        System.out.print(" ## ");
        System.out.print(persona.yearOfBirth);
        System.out.print(" ## ");
        System.out.print(persona.eyeColour);
        System.out.print(" ## ");
        System.out.print(persona.hairColour);
        System.out.print(" ## ");
        System.out.println(persona.wizard ? "true" : "false"); // WIZARD
    }
}
