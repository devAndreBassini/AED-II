import java.util.Scanner;

public class lab01q1 {
  public static int contarMaiusculas(String str) {
    int contador = 0;
    for(int i = 0; i < str.length(); i++){
      if(64 < str.charAt(i) && str.charAt(i) < 91){
        contador++;
      }
    }
    return contador;
  }

  public static void main(String[] args) {
    boolean end = false;
    Scanner s = new Scanner(System.in);
    while (s.hasNextLine() && end == false) {
      String line = s.nextLine();
      if(line.equals("FIM")){
        end = true;
      } else {
      int maiusculas = contarMaiusculas(line);
      System.out.println(maiusculas);         
      }
    }
    s.close();
  }
}
