import java.util.Random;
import java.util.Scanner;

public class Q4Alteracao {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        Random gerador = new Random();
        gerador.setSeed(4); 

        do {
            String word = myObj.nextLine();
            char letter1 = (char) ('a' + Math.abs(gerador.nextInt() % 26));
            char letter2 = (char) ('a' + Math.abs(gerador.nextInt() % 26));

            StringBuilder newWord = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter1)
                    newWord.append(letter2);
                else
                    newWord.append(word.charAt(i));

            }

            System.out.println(newWord);
        } while (myObj.hasNextLine());
        myObj.close();
    }
}
