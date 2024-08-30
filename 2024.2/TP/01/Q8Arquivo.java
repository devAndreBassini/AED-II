import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Q8Arquivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nomeArquivo = "numeros.txt";

        try {
            int n = scanner.nextInt();
            
            RandomAccessFile arquivo = new RandomAccessFile(nomeArquivo, "rw");

            for (int i = 0; i < n; i++) {
                double numero = scanner.nextDouble();
                arquivo.writeDouble(numero);
            }
            
            arquivo.close();

            arquivo = new RandomAccessFile(nomeArquivo, "r");

            long posicaoAtual = arquivo.length();

            for (int i = n - 1; i >= 0; i--) {
                posicaoAtual -= Double.BYTES;
                arquivo.seek(posicaoAtual);
                double numero = arquivo.readDouble();
                System.out.println(numero);
            }

            arquivo.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
