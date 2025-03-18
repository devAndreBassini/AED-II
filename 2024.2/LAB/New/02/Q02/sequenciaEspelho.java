import java.util.Scanner;

public class sequenciaEspelho {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextInt()){
            int start = sc.nextInt();
            int end = sc.nextInt();

            StringBuilder resp = new StringBuilder();

            for(int i = start; i <= end; i++){
                resp.append(i);
            }

            int length = resp.toString().length();
            
            for(int i = length-1; i >= 0; i--){
                resp.append(resp.charAt(i));
            }
            System.out.println(resp);

        }
        sc.close();
    }
}
