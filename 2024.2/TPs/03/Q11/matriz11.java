import java.io.*;
import java.util.*;

public class matriz11 {
    static class Matriz {
        private final int linhas;
        private final int colunas;
        private final int[][] dados;

        public Matriz(int linhas, int colunas) {
            this.linhas = linhas;
            this.colunas = colunas;
            this.dados = new int[linhas][colunas];
        }

        public void setValor(int i, int j, int valor) {
            this.dados[i][j] = valor;
        }

        public int getValor(int i, int j) {
            return this.dados[i][j];
        }

        public Matriz soma(Matriz outra) {
            if (outra.linhas != this.linhas || outra.colunas != this.colunas) {
                throw new IllegalArgumentException("Dimensões incompatíveis para soma");
            }
            Matriz resultado = new Matriz(linhas, colunas);
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    resultado.dados[i][j] = this.dados[i][j] + outra.dados[i][j];
                }
            }
            return resultado;
        }

        public Matriz multiplicacao(Matriz outra) {
            if (this.colunas != outra.linhas) {
                throw new IllegalArgumentException("Dimensões incompatíveis para multiplicação");
            }
            Matriz resultado = new Matriz(this.linhas, outra.colunas);
            for (int i = 0; i < this.linhas; i++) {
                for (int j = 0; j < outra.colunas; j++) {
                    int soma = 0;
                    for (int k = 0; k < this.colunas; k++) {
                        soma += this.dados[i][k] * outra.dados[k][j];
                    }
                    resultado.dados[i][j] = soma;
                }
            }
            return resultado;
        }

        public void mostrarDiagonalPrincipal() {
            for (int i = 0; i < Math.min(linhas, colunas); i++) {
                System.out.print(dados[i][i] + " ");
            }
            System.out.println();
        }

        public void mostrarDiagonalSecundaria() {
            int n = Math.min(linhas, colunas);
            for (int i = 0; i < n; i++) {
                System.out.print(dados[i][colunas - 1 - i] + " ");
            }
            System.out.println();
        }

        public void imprimir() {
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    System.out.print(dados[i][j] + (j < colunas - 1 ? " " : ""));
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int casos = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < casos; tc++) {
            // Leitura da primeira matriz
            int l1 = Integer.parseInt(br.readLine().trim());
            int c1 = Integer.parseInt(br.readLine().trim());
            Matriz m1 = new Matriz(l1, c1);
            for (int i = 0; i < l1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c1; j++) {
                    m1.setValor(i, j, Integer.parseInt(st.nextToken()));
                }
            }

            // Leitura da segunda matriz
            int l2 = Integer.parseInt(br.readLine().trim());
            int c2 = Integer.parseInt(br.readLine().trim());
            Matriz m2 = new Matriz(l2, c2);
            for (int i = 0; i < l2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c2; j++) {
                    m2.setValor(i, j, Integer.parseInt(st.nextToken()));
                }
            }

            // Saídas solicitadas
            // Diagonais da primeira matriz
            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();

            // Soma
            Matriz soma = m1.soma(m2);
            soma.imprimir();

            // Multiplicação
            Matriz prod = m1.multiplicacao(m2);
            prod.imprimir();
        }
    }
}
