import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        String word = myObj.nextLine();
        while (word != "FIM") {
            word = word.toLowerCase();

            StringBuilder sb = new StringBuilder(word);
            String reversedString = sb.reverse().toString();

            if (word.equals(reversedString)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            word = myObj.nextLine();
        }
        myObj.close();
    }
}
