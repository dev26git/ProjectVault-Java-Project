package OldClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    public static String login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email: ");
        String input = scanner.nextLine();

        String csvFile = "/Users/sinner/intellij_projects/Project_Repository_Portal/src/OldClient/user_details.csv";
        String line;
        String email = "";
        String username = "";
        String password = "";
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                email = data[0];
                password = data[1];
                if (email.equals(input) || username.equals(input)) {
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        if (!found) {
            System.out.println("User not found. Please sign up first.");
            return "";
        }

        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();

        if (password.equals(inputPassword)) {
            System.out.println("Login successful!");
            return email;
        }

        System.out.println("Incorrect password. Please try again.");
        return "";
    }

}