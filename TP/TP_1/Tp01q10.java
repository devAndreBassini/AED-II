import java.util.Scanner;

public class Tp01q10 {
    public static boolean ehPalindromoRecursivo(String str, int inicio, int fim) {
        if (inicio >= fim) {
            return true;
        }
        if (str.charAt(inicio) != str.charAt(fim)) {
            return false;
        }
        return ehPalindromoRecursivo(str, inicio + 1, fim - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            if (ehPalindromoRecursivo(linha, 0, linha.length() - 1)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }
        sc.close();
    }
}
