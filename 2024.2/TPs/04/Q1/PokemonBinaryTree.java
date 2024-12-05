import java.io.*;
import java.sql.Struct;
import java.util.*;

public class PokemonBinaryTree {
       
    public static void main(String[] args) {
        String csvFile = "pokemon.csv"; // Caminho do arquivo CSV

        try {
            // Leitura do arquivo CSV
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            br.readLine(); // Ignorar cabeçalho

            // Processa cada linha do arquivo
            while ((line = br.readLine()) != null) {
                String[] abilitieS = line.split("\"");
                String[] fields_1 = abilitieS[0].split(",");
                String[] fields_2 = abilitieS[1].split("'");
                String[] fields_3 = abilitieS[2].split(",");
                
                int abilities_cont = fields_2.length / 2;
                String[] types = new String[abilities_cont];


                int id = Integer.parseInt(fields_1[0]);
                String generation = fields_1[1];
                String name = fields_1[2];
                String description = fields_1[3];
                for(int i = 1, j = 0; i < fields_2.length; i += 2, j++){
                    types[j] = fields_2[i];
                }
                String abilities = abilitieS[1];
                double weightKg = Double.parseDouble(fields_3[1]);
                double heightM = Double.parseDouble(fields_3[2]);
                int captureRate = Integer.parseInt(fields_3[3]);
                boolean isLegendary = Boolean.parseBoolean(fields_3[4]);
                String captureDate = fields_3[5];
                
                // Armazenar os dados no mapa
                // pokemon.put(new String[]{name, generation, description, abilities, String.valueOf(weightKg), String.valueOf(heightM), String.valueOf(captureRate), String.valueOf(isLegendary), captureDate});
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
