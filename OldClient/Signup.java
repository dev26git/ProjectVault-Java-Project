package OldClient;

import models.Author;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


// Signup returns an author object to be saved into the database
public class Signup {
    public static Author signup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String csv = email + "," + password + "\n";
        try (FileWriter writer = new FileWriter("/Users/sinner/intellij_projects/Project_Repository_Portal/src/OldClient/user_details.csv", true)) {
            writer.write(csv);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }

        System.out.println("\nSignup Successful!");

        return new Author(-1, name, email);
    }
}
