import java.util.Scanner;

public class ContagemPalavras {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine(); 
            String[] words = line.split(" ");

            if(line.equals("FIM")) break;

            System.out.println(words.length);
            
        }
        sc.close();
    }
}
