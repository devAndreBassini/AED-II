public class Tp01q6 {
    static String end = "FIM";

    public static boolean Vogais(String line){
        char[] vogais = {'A', 'E', 'I', 'O', 'U'};
        int qtd = 0;
        for(int i = 0; i < line.length(); i++){
            for(int j = 0; j < vogais.length; j++){
                if( Character.toUpperCase(line.charAt(i)) == vogais[j]){
                    qtd++;
                }
            }
        }
    
        if(qtd == line.length()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean Consoantes(String line){
        char[] vogais = {'A', 'E', 'I', 'O', 'U'};
        int qtd = 0;
        for(int i = 0; i < line.length(); i++){
            for(int j = 0; j < vogais.length; j++){
                if( Character.toUpperCase(line.charAt(i)) == vogais[j]){
                    qtd++;
                }
            }
        }
    
        if(qtd == 0){
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean Inteiro(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean Real(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void main(String[] args) {
        String line;
        String X1, X2, X3, X4;
        
        do{
            X1 = "NAO "; X2 = "NAO "; X3 = "NAO "; X4 = "NAO ";
            line = MyIO.readLine();
            if(Vogais(line)) { X1 = "SIM "; }
            else if(Consoantes(line)) { X2 = "SIM "; }
            else if(Inteiro(line)) { X3 = "SIM "; }
            else if(Real(line)) { X4 = "SIM "; }

            MyIO.println(X1 + X2 + X3 + X4);            
        } while(line.compareTo(end) != 0);
    }

}
