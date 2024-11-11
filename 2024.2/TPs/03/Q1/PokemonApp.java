import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class PokemonApp {

    static class Pokemon implements Cloneable {
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

            String typesFormatted = this.types.isEmpty() ? "[]"
                    : String.format("['%s']", String.join("', '", this.types));
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
                Arrays.fill(campos, 12, campos.length, ""); // Preenche com strings vazias para evitar
                                                            // NullPointerException
            }

            this.id = Integer.parseInt(campos[0].isEmpty() ? "0" : campos[0]);
            this.generation = Integer.parseInt(campos[1].isEmpty() ? "0" : campos[1]);
            this.name = campos[2].replace("\"", "").trim();
            this.description = campos[3].replace("\"", "").trim();
            this.types = new ArrayList<>();

            if (campos.length > 4 && !campos[4].isEmpty()) {
                this.types.add(campos[4].replace("\"", "").trim());
            }

            if (campos.length > 5 && !campos[5].isEmpty()) {
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

        public String getName() {
            return name;
        }
    }

    static class ListaPokemon {
        private List<Pokemon> pokemons;

        public ListaPokemon() {
            this.pokemons = new ArrayList<>();
        }

        public void inserirInicio(Pokemon pokemon) {
            this.pokemons.add(0, pokemon);
        }

        public void inserir(Pokemon pokemon, int posicao) {
            if (posicao >= 0 && posicao <= this.pokemons.size()) {
                this.pokemons.add(posicao, pokemon);
            } else {
                throw new IndexOutOfBoundsException("Posição inválida");
            }
        }

        public void inserirFim(Pokemon pokemon) {
            this.pokemons.add(pokemon);
        }

        public Pokemon removerInicio() {
            if (!this.pokemons.isEmpty()) {
                return this.pokemons.remove(0);
            }
            return null;
        }

        public Pokemon remover(int posicao) {
            if (posicao >= 0 && posicao < this.pokemons.size()) {
                return this.pokemons.remove(posicao);
            }
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        public Pokemon removerFim() {
            if (!this.pokemons.isEmpty()) {
                return this.pokemons.remove(this.pokemons.size() - 1);
            }
            return null;
        }

        public void mostrarPokemons() {
            for (int i = 0; i < this.pokemons.size(); i++) {
                System.out.print("[" + i + "] ");
                this.pokemons.get(i).imprimir();
            }
        }
    }

    public static void main(String[] args) {
        ListaPokemon listaPokemon = new ListaPokemon();
        Scanner scanner = new Scanner(System.in);

        // Parte 1: Leitura dos Pokémons do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader("../pokemon.csv"))) {
            br.readLine(); // Ignora o cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.equals("FIM"))
                    break;
                Pokemon pokemon = new Pokemon();
                pokemon.ler(linha);
                listaPokemon.inserirFim(pokemon); // Adiciona no final da lista
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parte 2: Comandos de inserção e remoção
        int n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String comando = scanner.nextLine().trim();
            String[] partes = comando.split(" ");
            switch (partes[0]) {
                case "II":
                    Pokemon pokemonInicio = carregarPokemonDoArquivo(partes[1]);
                    listaPokemon.inserirInicio(pokemonInicio);
                    break;
                case "I*":
                    int posicaoI = Integer.parseInt(partes[1]);
                    Pokemon pokemonPosicao = carregarPokemonDoArquivo(partes[2]);
                    listaPokemon.inserir(pokemonPosicao, posicaoI);
                    break;
                case "IF":
                    Pokemon pokemonFim = carregarPokemonDoArquivo(partes[1]);
                    listaPokemon.inserirFim(pokemonFim);
                    break;
                case "RI":
                    Pokemon removidoInicio = listaPokemon.removerInicio();
                    if (removidoInicio != null) {
                        System.out.println("(R) " + removidoInicio.getName());
                    }
                    break;
                case "R*":
                    int posicaoR = Integer.parseInt(partes[1]);
                    Pokemon removidoPosicao = listaPokemon.remover(posicaoR);
                    if (removidoPosicao != null) {
                        System.out.println("(R) " + removidoPosicao.getName());
                    }
                    break;
                case "RF":
                    Pokemon removidoFim = listaPokemon.removerFim();
                    if (removidoFim != null) {
                        System.out.println("(R) " + removidoFim.getName());
                    }
                    break;
            }
        }

        listaPokemon.mostrarPokemons();
        scanner.close();
    }

    private static Pokemon carregarPokemonDoArquivo(String linha) {
        Pokemon pokemon = new Pokemon();
        pokemon.ler(linha);
        return pokemon;
    }
}
