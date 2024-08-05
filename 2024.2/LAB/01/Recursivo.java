import java.util.Scanner;

public class Recursivo {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        while (true) {
            String text = myObj.nextLine();

            if (text.equals("FIM"))
                break;

            int upperLetters = countUpperCase(text, text.length() - 1);
            System.out.println(upperLetters);
        }
        myObj.close();
    }

    public static int countUpperCase(String text, int index) {
        if (index < 0) {
            return 0;
        }

        int count = Character.isUpperCase(text.charAt(index)) ? 1 : 0;

        return count + countUpperCase(text, index - 1);
    }
}

// javac Recursivo.java && java Recursivo < 1_pub.in > minhaSaida.out && diff 1_pub.out minhaSaida.out
