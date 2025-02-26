import java.util.Scanner;

public class Anagrama {
    public static int comparador(String input01, String input02) {
        int match = 0;
        for (int i = 0; i < input01.length(); i++) {
            for (int j = 0; j < input02.length(); j++) {
                if (input01.charAt(i) == input02.charAt(j)) {
                    match += 1;
                    j = input02.length();
                }
            }
        }
        return match;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input01 = sc.next();
            if (input01.equals("FIM"))
                break;

            String c = sc.next();
            String input02 = sc.next();

            int match = 0;

            input01 = input01.toLowerCase();
            input02 = input02.toLowerCase();

            if (input01.length() == input02.length())
                match = comparador(input01, input02);

            if (match == input01.length() || match == input02.length())
                System.out.println("SIM");
            else
                System.out.println("NÃO");
        }
        sc.close();
    }
}

/*
 * ENTRADA SAÍDA
 * listen silent SIM
 * 
 * 
 * 
 * 
 */