public class Tp01q15 {
    static String end = "FIM";

    public static boolean Vogais(String line, int index, int qtdVogais) {
        char[] vogais = {'A', 'E', 'I', 'O', 'U'};
        if (index == line.length()) {
            return qtdVogais == line.length();
        } else {
            char currentChar = Character.toUpperCase(line.charAt(index));
            for (char vogal : vogais) {
                if (currentChar == vogal) {
                    qtdVogais++;
                    break;
                }
            }
            return Vogais(line, index + 1, qtdVogais);
        }
    }

    public static boolean Consoantes(String line, int index, int qtdVogais) {
        char[] vogais = {'A', 'E', 'I', 'O', 'U'};
        if (index == line.length()) {
            return qtdVogais == 0;
        } else {
            char currentChar = Character.toUpperCase(line.charAt(index));
            for (char vogal : vogais) {
                if (currentChar == vogal) {
                    qtdVogais++;
                    break;
                }
            }
            return Consoantes(line, index + 1, qtdVogais);
        }
    }

    public static boolean Inteiro(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean Real(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String line;
        String X1, X2, X3, X4;

        do {
            X1 = "NAO ";
            X2 = "NAO ";
            X3 = "NAO ";
            X4 = "NAO ";
            line = MyIO.readLine();
            if (Vogais(line, 0, 0)) {
                X1 = "SIM ";
            } else if (Consoantes(line, 0, 0)) {
                X2 = "SIM ";
            } else if (Inteiro(line)) {
                X3 = "SIM ";
            } else if (Real(line)) {
                X4 = "SIM ";
            }

            MyIO.println(X1 + X2 + X3 + X4);
        } while (line.compareTo(end) != 0);
    }
}
