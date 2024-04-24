public class Tp01q3{
    static int key = 3;
    static String end = "FIM";

    public static String criptografia(String word){
        String newWord = " ";
        char letter;
        for(int i = 0; i < word.length(); i++){
            letter = word.charAt(i);
            for(int j = 0; j < 3; j++){
                ++letter;
            }
            newWord += letter;
        }
        return newWord;
    }

    public static void main(String[] args) {
        String encoded;
        String uncoded = MyIO.readLine();
            while(uncoded.compareTo(end) != 0){
                encoded = criptografia(uncoded);
                MyIO.println(encoded);
                uncoded = MyIO.readLine();
            }
        }
}