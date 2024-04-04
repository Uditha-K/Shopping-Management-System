package CLI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validations {

    private static final Scanner input = new Scanner(System.in);

    //Int validation
    public static int intValidation(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter an integer.");
                input.nextLine();
            }
        }
    }

    //Double validation
    public static double doubleValidation(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter a number.");
                input.nextLine();
            }
        }
    }

    //String validation
    public static String stringValidation(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String userInput = input.next();

                // Validate using regular expression
                if (userInput.matches("[a-zA-Z0-9]+")) {
                    return userInput;
                } else {
                    System.out.println("Invalid input. Please enter only letters (A-Z, a-z) and digits (0-9).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input type.");
                input.nextLine();
            }
        }
    }
}



