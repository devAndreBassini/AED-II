import java.util.Scanner;

public class validacao{
    public static Boolean upperChar(String password){ // uma letra maiúscula 65 <= c <= 90
        for(int i = 0; i < password.length(); i++){
            if(65 <= password.charAt(i) && password.charAt(i) <= 90) return true;
        }
        return false;
    }
    
    public static Boolean lowerChar(String password){ // uma letra minúscula 97 <= c <= 122
        for(int i = 0; i < password.length(); i++){
            if(97 <= password.charAt(i) && password.charAt(i) <= 122) return true;
        }
        return false;
    }

    public static Boolean numberChar(String password){ // um número 48 <= c <= 57      
        for(int i = 0; i < password.length(); i++){
            if(48 <= password.charAt(i) && password.charAt(i) <= 57) return true;
        }
        return false;
    }

    public static Boolean specialChar(String password){ // cractere especial
        // 33 <= c <= 47
        // 58 <= c <= 64
        // 91 <= c <= 96
        // 123 <= c <= 126
        for(int i = 0; i < password.length(); i++){
            if(33 <= password.charAt(i) && password.charAt(i) <= 47) return true;
            else if(58 <= password.charAt(i) && password.charAt(i) <= 64) return true;
            else if(91 <= password.charAt(i) && password.charAt(i) <= 96) return true;
            else if(123 <= password.charAt(i) && password.charAt(i) <= 126) return true;
        }
        return false;
    }

    public static Boolean securePassword(String password){
        if(password.length() < 8) return false;
        
        if(upperChar(password) && lowerChar(password) && numberChar(password) && specialChar(password))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String password = sc.nextLine();
            
            if(password.equals("FIM")) break;
            
    
            if(securePassword(password))
                System.out.println("SIM");
            else
                System.out.println("NÃO");
        }
        sc.close();
    }
}
