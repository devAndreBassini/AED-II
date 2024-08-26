import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int contL = 0, contR = 0, total = 0;
        String line;

        while(true){
            line = scan.nextLine();           
            if (line == "FIM" || !(scan.hasNextLine()))
                break;

            for (int i = 0; i < line.length(); i++) {
                if (line.toCharArray()[i] == '(')
                    contL++;

                if (line.toCharArray()[i] == ')')
                    contR++;

                if (contL == 0 && line.toCharArray()[i] == ')') 
                    contL = -1;

            }

            total = contL - contR;

            if (total == 0 && contL == contR)
                System.out.println("correto");
            else
                System.out.println("incorreto");

            if(scan.hasNextLine()){
                line = scan.nextLine();
                contL = 0;
                contR = 0;
                total = 0;
            }
        }
        scan.close();
    }
}
