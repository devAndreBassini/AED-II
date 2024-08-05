import java.util.Scanner;

public class lab01q2 {
  public static int contarMaiusculas(String str, int index) {
    if (index == str.length()) {
      return 0;
    }
    int contador = Character.isUpperCase(str.charAt(index)) ? 1 : 0;
    return contador + contarMaiusculas(str, index + 1);
  }

  public static void main(String[] args) {
    boolean end = false;
    Scanner s = new Scanner(System.in);
    while (s.hasNextLine() && !end) {
      String line = s.nextLine();
      if (line.equals("FIM")) {
        end = true;
      } else {
        int maiusculas = contarMaiusculas(line, 0);
        System.out.println(maiusculas);
      }
    }
    s.close();
  }
}
