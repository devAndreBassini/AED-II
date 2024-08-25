import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(System.in);

        int n1, n2;
        int maior, menor, size;
        
        String output;

        while (scan.hasNextInt()) {
            sb.setLength(0);

            n1 = scan.nextInt();
            n2 = scan.nextInt();

            if (n2 > n1) {
                maior = n2;
                menor = n1;
            } else {
                maior = n1;
                menor = n2;
            }

            size = maior - menor + 1;
            int vet[] = new int[size];

            for (int i = 0; i < size; i++) {
                vet[i] = menor + i;
                sb.append(vet[i]);
            }

            char[] vetString = sb.toString().toCharArray();

            for (int i = vetString.length - 1; i >= 0; i--) {
                sb.append(vetString[i]);
            }

            output = sb.toString();

            System.out.println(output);
        }
        scan.close();
    }
}
