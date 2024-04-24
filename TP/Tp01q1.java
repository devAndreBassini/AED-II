import java.util.Scanner;

public class Tp01q1{
    public static boolean ehPalindromo(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
           if (ehPalindromo(linha)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }
        sc.close();
    }
}
