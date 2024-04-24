import java.io.*;

public class Tp01q8 {

    public static void main(String[] args) {
        try {
            FileWriter arquivo = new FileWriter("valores.txt");
            BufferedWriter escritor = new BufferedWriter(arquivo);

            BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(leitor.readLine());

            for (int i = 0; i < n; i++) {
                double valor = Double.parseDouble(leitor.readLine());
                escritor.write(Double.toString(valor) + "\n");
            }

            escritor.close();
            arquivo.close();

            RandomAccessFile raf = new RandomAccessFile("valores.txt", "r");
            long tamanhoArquivo = raf.length();
            long ponteiro = tamanhoArquivo - 1;

            while (ponteiro >= 0) {
                raf.seek(ponteiro);
                char c = (char) raf.readByte();
                if (c == '\n') {
                    String linha = raf.readLine();
                    if (linha != null && !linha.isEmpty()) {
                        double valor = Double.parseDouble(linha);
                        if (valor == (int) valor) {
                            System.out.println((int) valor);
                        } else {
                            System.out.println(valor);
                        }
                    }
                }
                ponteiro--;
            }

            raf.close();

        } catch (IOException e) {
        } catch (NumberFormatException e) {
        }
    }
}
