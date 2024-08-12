import java.util.Scanner;

public class Ciframento {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        int key = 3;
        while (true) {
            String word = myObj.nextLine();

            if (word.equals("FIM"))
                break;

            StringBuilder encryptedWord = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char aux = word.charAt(i);
                if (Character.isLetter(aux))
                    aux += key;

                encryptedWord.append(aux);
            }

            System.out.println(encryptedWord);
        }
        myObj.close();
    }
}
