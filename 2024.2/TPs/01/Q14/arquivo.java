import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Q8Arquivo {
    public static void main(String[] args) {
        String nomeArquivo = "numeros.txt";

        try (Scanner scanner = new Scanner(System.in);
                RandomAccessFile arquivo = new RandomAccessFile(nomeArquivo, "rw")) {

            int n = scanner.nextInt();

            for (int i = 0; i < n; i++) {
                arquivo.writeDouble(scanner.nextDouble());
            }
        } catch (IOException e) {
            return;
        } catch (NumberFormatException e) {
            return;
        }

        // Leitura reversa dos números
        try (RandomAccessFile arquivo = new RandomAccessFile(nomeArquivo, "r")) {
            long tamanhoArquivo = arquivo.length();
            long posicaoAtual = tamanhoArquivo - Double.BYTES; // Último número armazenado

            while (posicaoAtual >= 0) {
                arquivo.seek(posicaoAtual);
                double num = arquivo.readDouble();

                // Verifica se o número é inteiro
                if (num == Math.floor(num))
                    System.out.println((long) num);
                else
                    System.out.println(num);

                posicaoAtual -= Double.BYTES; // Move para o número anterior
            }
        } catch (IOException e) {
            return;
        }
    }
}
