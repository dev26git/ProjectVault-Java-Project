package Client;

import models.Author;
import java.io.FileWriter;
import java.io.IOException;

public abstract class SignupBase {
    public abstract Author signup();

    protected static void saveAuthorToCsv(String email, String password) {
        String csv = email + "," + password + "\n";
        try (FileWriter writer = new FileWriter("/Users/sinner/intellij_projects/Project_Repository_Portal/src/TestClient/user_details.csv", true)) {
            writer.write(csv);
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}

