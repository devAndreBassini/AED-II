import java.util.Scanner;

public class Q10PalindromoRecursivo {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        do {
            String word = myObj.nextLine();

            if (word.equals("FIM")) {
                break;
            }

            if (isPalindromeRecursive(word, 0, word.length() - 1)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

        } while (myObj.hasNextLine());
        
        myObj.close();
    }

    public static boolean isPalindromeRecursive(String word, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (word.charAt(left) != word.charAt(right)) {
            return false;
        }

        return isPalindromeRecursive(word, left + 1, right - 1);
    }
}
