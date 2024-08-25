public class Q5AlgebraBoleana {

    static boolean validation(char letter) {
        String invalid = "() ,";
        for (int i = 0; i < invalid.length(); i++) {
            if (letter == invalid.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        
        String line;
        int[] num;

        String tempLine = "";
        String[] palavras = { "" };

        int j = 0;

        while (true) {
            line = MyIO.readLine();

            if (line.charAt(0) == '0') {
                break;
            }

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ' ') {
                    i += 1;
                }
                tempLine += line.charAt(i);
            }

            palavras[j] = tempLine;
            tempLine = "";
            j += 1;

            System.out.println(line);
            System.out.println(">" + palavras[j - 1]);
        }
    }
}
