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

    // Construtor padrão
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

    // Método clone
    @Override
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // Método imprimir
    public void imprimir() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf(
            "[#%d -> %s: %s - %s - %s - %.1fkg - %.1fm - %d%% - %b - %d gen] - %s\n",
            this.id, this.name, this.description, this.types.toString(), this.abilities.toString(),
            this.weight, this.height, this.captureRate, this.isLegendary, this.generation, sdf.format(this.captureDate)
        );
    }

    // Método para ler a partir do CSV
    public void ler(String linha) {
        String[] campos = linha.split(",");
        
        this.id = Integer.parseInt(campos[0].isEmpty() ? "0" : campos[0]);
        this.name = campos[1];
        this.description = campos[2];
        this.types = new ArrayList<>(Arrays.asList(campos[3].split(";")));
        this.abilities = new ArrayList<>(Arrays.asList(campos[4].split(";")));
        this.weight = Double.parseDouble(campos[5].isEmpty() ? "0.0" : campos[5]);
        this.height = Double.parseDouble(campos[6].isEmpty() ? "0.0" : campos[6]);
        this.captureRate = Integer.parseInt(campos[7].isEmpty() ? "0" : campos[7]);
        this.isLegendary = Boolean.parseBoolean(campos[8]);
        this.generation = Integer.parseInt(campos[9].isEmpty() ? "0" : campos[9]);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.captureDate = sdf.parse(campos[10]);
        } catch (Exception e) {
            this.captureDate = new Date(); // Data padrão
        }
    }

    public static void main(String[] args) {
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pokemon.csv"))) {
            br.readLine(); // Pula a primeira linha (cabeçalho)

            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.equals("FIM")) break;

                Pokemon p = new Pokemon();
                p.ler(linha);
                pokemons.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Pokemon p : pokemons) {
            p.imprimir();
        }
    }
}
