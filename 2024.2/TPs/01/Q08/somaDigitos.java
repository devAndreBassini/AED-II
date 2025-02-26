import java.util.Scanner;

public class SomaDigitos {
    public static int somador(int value, int sum){
        if(value > 0)
            return somador(value / 10, sum + (value%10));
        else
            return sum;    
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        while(sc.hasNextInt()){
            int value = sc.nextInt();

            if(value == 0) break;
            
            System.out.println(somador(value, 0));
        }
        sc.close();
    }
}
