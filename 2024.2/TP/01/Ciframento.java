import java.util.Scanner;

public class Ciframento {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        
        int key = 3;
        int aux;

        do{
            String word = "";
            
            String entrada = myObj.nextLine();
            char[] entArray = entrada.toCharArray();

            for(int i = 0; i < entrada.length(); i++){
                aux = (int)(entArray[i]) + key;
                word += (char)aux;
            }
        
            System.out.println(word);
        } while(myObj.hasNextLine());
        myObj.close();
    }
}
