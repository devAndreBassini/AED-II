import java.util.Scanner;

public class aquecimento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = null;
        int maiusculas = 0;
        
        while(sc.hasNextLine()){
            input = sc.nextLine();
            if(input.equals("FIM")) break;

            for(int i = 0; i < input.length(); i++){
                if(Character.isUpperCase(input.charAt(i))) maiusculas++;
            }   
            
            System.out.println(maiusculas);
            maiusculas = 0;
        }
        sc.close();
    }
}
