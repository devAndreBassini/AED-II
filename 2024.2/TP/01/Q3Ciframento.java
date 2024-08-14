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
                encryptedWord.append((char) (word.charAt(i) + key));
            }

            System.out.println(encryptedWord);
        }

        scanner.close();
    }
}
