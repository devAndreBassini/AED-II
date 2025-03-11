import java.util.Scanner;

public class Substring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("FIM"))
                break;

            int maior = 0, subStringID = 0, maisLongaID = 0;
            String[] substring = new String[100];
            boolean isIn = false;

            for (int i = 0; i < input.length(); i++) {
                if (substring[subStringID] == null) substring[subStringID] = "";

                substring[subStringID] += input.charAt(i);

                if (i + 1 < input.length())
                    isIn = substring[subStringID].contains(String.valueOf(input.charAt(i + 1)));
                else 
                    subStringID++;


                if(isIn){
                    i = subStringID;
                    subStringID++;
                    isIn = false;
                }
                
            }
            
            for (int i = 0; i < subStringID; i++) {
                if (substring[i] != null && maior < substring[i].length()) {
                    maior = substring[i].length();
                    maisLongaID = i;
                }
            }

            System.out.println(substring[maisLongaID].length());
        }
        sc.close();
    }
}
