public class Q3Ciframento {
    private static final int key = 3;

    public static void main(String[] args) {

        while (true) {
            String word = MyIO.readLine();
            System.out.println(">" + word);
            if (word.equals("FIM"))
                break;

            String encryptedWord = "";

            for (int i = 0; i < word.length(); i++) {
                System.out.println(encryptedWord + "<");
                encryptedWord = encryptedWord + (char)((int)word.charAt(i) + key);
            }

            System.out.println(encryptedWord);
        }
    }
}
