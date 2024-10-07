import java.util.Scanner;

public class Q14IsRecursivo {
    public static boolean X1(String str) {
        return verificaVogais(str, 0);
    }

    private static boolean verificaVogais(String str, int index) {
        if (index >= str.length()) {
            return true;
        }

        char c = str.charAt(index);
        if (65 <= c && c <= 122) {
            c = Character.toLowerCase(c);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return verificaVogais(str, index + 1);
            }
            return false;
        }

        return verificaVogais(str, index + 1);
    }

    public static boolean X2(String str) {
        return verificaNaoAlfabeticos(str, 0);
    }

    private static boolean verificaNaoAlfabeticos(String str, int index) {
        if (index >= str.length()) {
            return true;
        }

        char c = str.charAt(index);
        if (65 <= c && c <= 122) {
            c = Character.toLowerCase(c);
            if (!(c >= 'a' && c <= 'z')) {
                return false;
            }
        }

        return verificaNaoAlfabeticos(str, index + 1);
    }

    public static boolean X3(String str) {
        return verificaDigitos(str, 0);
    }

    private static boolean verificaDigitos(String str, int index) {
        if (index >= str.length()) {
            return true;
        }

        char c = str.charAt(index);
        if (48 <= c && c <= 57) {
            return verificaDigitos(str, index + 1);
        }

        return false;
    }

    public static boolean X4(String str) {
        return verificaDecimal(str, 0, false, 0);
    }

    private static boolean verificaDecimal(String str, int index, boolean temDecimal, int cont) {
        if (index >= str.length()) {
            return temDecimal && cont == str.length();
        }

        char c = str.charAt(index);
        if (c == ',' || c == '.') {
            temDecimal = true;
        } else if (48 <= c && c <= 57) {
            cont++;
        }

        return verificaDecimal(str, index + 1, temDecimal, cont);
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
