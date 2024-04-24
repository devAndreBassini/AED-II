public class Tp01q12 {
    static int key = 3;
    static String end = "FIM";

    public static String criptografia(String word) {
        return criptografiaRecursiva(word, 0, "");
    }

    public static String criptografiaRecursiva(String word, int index, String newWord) {
        if (index == word.length()) {
            return newWord;
        } else {
            char letter = word.charAt(index);
            for (int j = 0; j < key; j++) {
                letter++;
            }
            newWord += letter;
            return criptografiaRecursiva(word, index + 1, newWord);
        }
    }

    public static void main(String[] args) {
        String encoded;
        String uncoded = MyIO.readLine();
        while (!uncoded.equals(end)) {
            encoded = criptografia(uncoded);
            MyIO.println(encoded);
            uncoded = MyIO.readLine();
        }
    }
}
