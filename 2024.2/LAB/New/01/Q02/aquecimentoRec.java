import java.util.Scanner;

public class aquecimentoRec {
    public static int getUpperCases(String input, int i) {
        if (i >= input.length()) return 0;
                    
        return (input.charAt(i) >= 65 && input.charAt(i) <= 90 ? 1 : 0) + getUpperCases(input, i + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = " ";

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if(input.equals("FIM")) break;
            int maiusculas = getUpperCases(input, 0);

            System.out.println(maiusculas);
        }
        sc.close();
    }
}
