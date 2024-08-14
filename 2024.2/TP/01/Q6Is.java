import java.util.Scanner;

public class Q6Is {

    public static boolean X1(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (65 <= c && c <= 122) {
                c = Character.toLowerCase(c);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    cont++;
                }
            }
        }
        return cont == str.length() - 1;
    }

    public static boolean X2(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (65 <= c && c <= 122) {
                c = Character.toLowerCase(c);
                if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                    cont++;
                }
            }
        }
        return cont == str.length() - 1;
    }

    public static boolean X3(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (48 <= c && c <= 57) {
                cont++;
            }
        }
        return cont == str.length() - 1;
    }

    public static boolean X4(String str) {
        int cont = 0;
        boolean decimal = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') {
                decimal = true;
            }
        }
        if (!decimal) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (48 <= c && c <= 57) {
                cont++;
            }
        }
        return cont == str.length() - 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("FIM")) break;
            
            System.out.print(X1(str) ? "SIM " : "NAO ");
            System.out.print(X2(str) ? "SIM " : "NAO ");
            System.out.print(X3(str) ? "SIM " : "NAO ");
            System.out.print(X4(str) ? "SIM " : "NAO ");
        }
        scanner.close();
    }
}
