import java.util.Scanner;

public class SUS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int N = sc.nextInt();
            int totalCriticos = 0;
            int tempoAtual = 420; // Inicio se da as 7h
            int entradaPaciente = 0;

            for (int i = 0; i < N; i++) {
                int H = sc.nextInt();
                int M = sc.nextInt();
                int C = sc.nextInt();

                entradaPaciente = H * 60 + M;
                while (tempoAtual < entradaPaciente) {
                    tempoAtual += 30;
                }

                if (tempoAtual - entradaPaciente > C)
                    totalCriticos++;

                tempoAtual += 30;
            }

            System.out.println(totalCriticos);
        }

        sc.close();

    }
}
