import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Pokemon implements Cloneable {
    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private Date captureDate;

    public Pokemon() {
        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.weight = 0.0;
        this.height = 0.0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = new Date();
    }

    @Override
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void imprimir() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String typesFormatted = this.types.isEmpty() ? "[]" : String.format("['%s']", String.join("', '", this.types));

        String abilitiesFormatted = this.abilities.isEmpty() ? "[]"
                : String.format("['%s']", String.join("', '", this.abilities));

        System.out.printf(
                "[#%d -> %s: %s - %s - %s - %.1fkg - %.1fm - %d%% - %b - %d gen] - %s\n",
                this.id, this.name, this.description, typesFormatted, abilitiesFormatted,
                this.weight, this.height, this.captureRate, this.isLegendary, this.generation,
                sdf.format(this.captureDate));
    }

    public void ler(String linha) {
        String[] campos = linha.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        if (campos.length < 12) {
            campos = Arrays.copyOf(campos, 12);
        }

        this.id = Integer.parseInt(campos[0].isEmpty() ? "0" : campos[0]);
        this.generation = Integer.parseInt(campos[1].isEmpty() ? "0" : campos[1]);
        this.name = campos[2].replace("\"", "").trim();
        this.description = campos[3].replace("\"", "").trim();
        this.types = new ArrayList<>();

        if (!campos[4].isEmpty()) {
            this.types.add(campos[4].replace("\"", "").trim());
        }

        if (!campos[5].isEmpty()) {
            this.types.add(campos[5].replace("\"", "").trim());
        }

        this.abilities = new ArrayList<>(
                Arrays.asList(campos[6].replace("\"", "").replaceAll("[\\[\\]']", "").split(",\\s*")));

        String weightString = campos[7].isEmpty() ? "0.0" : campos[7].replace(",", ".");
        String heightString = campos[8].isEmpty() ? "0.0" : campos[8].replace(",", ".");

        this.weight = Double.parseDouble(weightString);
        this.height = Double.parseDouble(heightString);
        this.captureRate = Integer.parseInt(campos[9].isEmpty() ? "0" : campos[9]);
        this.isLegendary = Integer.parseInt(campos[10].isEmpty() ? "0" : campos[10]) == 1;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.captureDate = sdf.parse(campos[11].trim());
        } catch (Exception e) {
            this.captureDate = new Date();
        }
    }

    public static void main(String[] args) {
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("../pokemon.csv"))) {
            br.readLine();

            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.equals("FIM"))
                    break;

                Pokemon p = new Pokemon();
                p.ler(linha);
                pokemons.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String entrada = scanner.nextLine();
                if (entrada.equals("FIM")) {
                    break;
                }

                try {
                    int id = Integer.parseInt(entrada);

                    Pokemon encontrado = null;
                    for (Pokemon p : pokemons) {
                        if (p.id == id) {
                            encontrado = p;
                            break;
                        }
                    }

                    if (encontrado != null) {
                        encontrado.imprimir();
                    }
                } catch (NumberFormatException e) {
                    return;
                }
            }
            scanner.close();
        }
    }
}
