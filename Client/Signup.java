package Client;

import models.Author;
import java.util.Scanner;

public class Signup extends SignupBase {
    @Override
    public Author signup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        saveAuthorToCsv(email, password);

        System.out.println("\nSignup Successful!");

        return new Author(-1, name, email);
    }
}