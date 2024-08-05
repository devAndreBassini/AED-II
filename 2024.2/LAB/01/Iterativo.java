import java.util.Scanner;

public class Iterativo {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        int upperLetters = 0;
        while (true) {
            String text = myObj.nextLine();

            if (text.equals("FIM")) 
                break;
            

            upperLetters = 0;
            for (int i = 0; i < text.length(); i++) {
                if (Character.isUpperCase(text.charAt(i)))
                    upperLetters++;
            }

            System.out.println(upperLetters);
        }
        myObj.close();
    }
}

// javac Iterativo.java && java Iterativo < 1_pub.in > minhaSaida.out && diff 1_pub.out minhaSaida.out
