import java.util.Scanner;


public class Q3Ciframento {
    private static final int key = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String word = scanner.nextLine();

            if (word.equals("FIM"))
                break;

            StringBuilder encryptedWord = new StringBuilder();


            for (int i = 0; i < word.length(); i++) {
                if(0 <= word.charAt(i) && word.charAt(i) <= 255)
                    encryptedWord.append((char) (word.charAt(i) + key));
                else
                    encryptedWord.append((char) (word.charAt(i)));
                }

            System.out.println(encryptedWord);
        }

        scanner.close();
    }
}