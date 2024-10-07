import java.util.Scanner;

public class Q1Palindromo {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        do {
            String word = myObj.nextLine();

            if (word == "FIM")
                break;

            StringBuilder sb = new StringBuilder(word);
            String reversedString = sb.reverse().toString();

            if (word.equals(reversedString))
                System.out.println("SIM");
            else
                System.out.println("NAO");

        } while (myObj.hasNextLine());
        myObj.close();
        
    }
}
