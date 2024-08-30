public class Q12CiframentoRecursivo {
    public static void main(String[] args) {
        processarEntrada();
    }

    public static void processarEntrada() {
        String word = MyIO.readLine();

        if (word.equals("FIM")) {
            return;
        }

        System.out.println(encriptarPalavra(word, 0, new StringBuilder()));

        processarEntrada();
    }

    public static String encriptarPalavra(String word, int index, StringBuilder sb) {
        int key = 3;

        if (index == word.length()) {
            return sb.toString();
        }

        char encryptedChar = (char) (word.charAt(index) + key);
        sb.append(encryptedChar);

        return encriptarPalavra(word, index + 1, sb);
    }
}