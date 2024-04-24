import java.util.Random;

public class Tp01q4 {
    static String end = "FIM";

    public static String substituirLetras(String texto, char letraOriginal, char letraSubstituta) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            if (caractere == letraOriginal) {
                resultado.append(letraSubstituta);
            } else {
                resultado.append(caractere);
            }
        }
        return resultado.toString();
    }

    public static void main(String[] args) {
        Random gerador = new Random();
        gerador.setSeed(4);

        String entrada;

        do{
            entrada = MyIO.readLine();
            char letraOriginal = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            char letraSubstituta = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

            String resultado = substituirLetras(entrada, letraOriginal, letraSubstituta);
            MyIO.println(resultado);
        } while(entrada.compareTo(end) != 0);
    }
}
