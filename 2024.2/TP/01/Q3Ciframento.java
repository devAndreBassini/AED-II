public class Q3Ciframento {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        int key = 3;
        while (true) {
            sb.setLength(0);
            String word = MyIO.readLine();
            if (word.equals("FIM"))
                break;

            char[] vet = new char[word.length()];

            for (int i = 0; i < word.length(); i++) {
                vet[i] = word.toCharArray()[i];
                vet[i] += (char) key;
                sb.append(vet[i]);
            }

            String encryptedWord = sb.toString();

            System.out.println(encryptedWord);
        }
    }
}
