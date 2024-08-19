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
        return cont == str.length() ;
    }

    public static boolean X2(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (65 <= c && c <= 122) {
                c = Character.toLowerCase(c);
                if (!( c >= 97 || c <= 122)) {
                    cont++;
                }
            }
        }
        return cont == str.length();
    }

    public static boolean X3(String str) {
        int cont = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (48 <= c && c <= 57) {
                cont++;
            }
        }
        return cont == str.length();
    }

    public static boolean X4(String str) {
        int cont = 0;
        boolean decimal = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',' || str.charAt(i) == '.') {
                decimal = true;
            }
        }
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (48 <= c && c <= 57) {
                cont++;
            }
        }

        if (decimal && cont == str.length()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String str = MyIO.readLine();
            if (str.equals("FIM")) break;
            
            System.out.print(X1(str) ? "SIM " : "NAO ");
            System.out.print(X2(str) ? "SIM " : "NAO ");
            System.out.print(X3(str) ? "SIM " : "NAO ");
            System.out.print(X4(str) ? "SIM" : "NAO");
            System.out.println("");
        }
        scanner.close();
    }
}
