import java.util.Scanner;

public class InversaoString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String input = sc.nextLine();
            
            if(input.equals("FIM")) break;

            StringBuilder reversed = new StringBuilder();
            
            for(int i = input.length() - 1; i >= 0; i--){
                reversed.append(input.charAt(i));
            }

            System.out.println(reversed.toString());
        }
        sc.close();
    }
}