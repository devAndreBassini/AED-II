import java.util.Scanner;

public class ParImpar {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in); // Create a scanner object

        int number = myObj.nextInt(); // Read User input
        while (number != 0) {
            if (number % 2 == 0)
                System.out.println("P");
            else
                System.out.println("I");
            number = myObj.nextInt(); // Read User input
        }
        myObj.close();
    }
}
