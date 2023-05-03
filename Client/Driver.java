package Client;

import models.*;
import repos.*;
import Service.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws SQLException, InterruptedException {

        Connection conn = DBUtil.getConnection();
        AuthorRepo authorRepo = new AuthorRepo(conn);
        MentorRepo mentorRepo = new MentorRepo(conn);
        ProjectRepo projectRepo = new ProjectRepo(conn);
        CurrentUserService currentUserService = null;
        AllUsersService allUsersService = new AllUsersService(conn);
        Scanner sc = new Scanner(System.in);

        boolean alive = true;
        while(alive) {
            int choice;
            Author currentUser = null;
            System.out.println("\n\t\t\t\t\t\t   MENU");
            System.out.println("\t\t\t-----------------------------------");
            System.out.println("\t\t\t\t 1. Sign-Up");
            System.out.println("\t\t\t\t 2. Login");
            System.out.println("\t\t\t\t 3. Add a new project");
            System.out.println("\t\t\t\t 4. View your projects");
            System.out.println("\t\t\t\t 5. Search Project by keyword");
            System.out.println("\t\t\t\t 6. Search Project by ID");
            System.out.println("\t\t\t\t 7. Update Project Title");
            System.out.println("\t\t\t\t 8. Update Project Abstract");
            System.out.println("\t\t\t\t 9. Add New Mentor");
            System.out.println("\t\t\t\t10. Change Project Mentor");
            System.out.println("\t\t\t\t11. View All Mentors");
            System.out.println("\t\t\t\t12. View All Projects");
            System.out.println("\t\t\t\t13. Delete a Project");
            System.out.println("\t\t\t\t 0. End Program");
            System.out.println("\t\t\t-----------------------------------");
            System.out.print("\t\t\t\tEnter your choice (0-11): ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice) {

                case 1 -> {
                    // Signup and add author to database
                    Signup SignupObj = new Signup();
                    Author newAuthor = SignupObj.signup();
                    authorRepo.insertAuthor(newAuthor.getAuthorName(), newAuthor.getAuthorEmail());
                }

                case 2 -> {
                    // Login and make current user
                    try {
                        String userEmail = Login.login();
                        if (!userEmail.equals("")) {
                            currentUser = authorRepo.getAuthorByEmail(userEmail);
                            currentUserService = new CurrentUserService(conn, currentUser);
                        }
                    } catch (IncorrectPasswordException ex){
                        System.out.println("Incorrect Password.");
                    }
                }

                case 3 -> {
                    if(checkLogin(currentUserService)) {
                        System.out.print("Enter Project Title:");
                        String title = sc.nextLine();
                        System.out.print("Enter Project Abstract:");
                        String Abstract = sc.nextLine();
                        System.out.print("Enter Mentor ID:");
                        int mentorID = sc.nextInt();
                        sc.nextLine();
                        if (currentUserService.insertProject(title, Abstract, mentorID)) {
                            System.out.println("Project Saved.");
                        }
                    }
                }

                case 4 -> {
                    if(checkLogin(currentUserService)) {
                        for (Project p : currentUserService.getCurrentUsersProjects()) {
                            System.out.println(p);
                        }
                    }
                }

                case 5 -> {
                    System.out.println("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    List<Project> projectList= allUsersService.searchKeyword(keyword);
                    if(projectList.size() == 0){
                        System.out.println("No Projects found.");
                    }
                    else {
                        for (Project p : projectList) {
                            System.out.println(p);
                        }
                    }
                }

                case 6 -> {
                    System.out.println("Enter Project ID to search:");
                    int projectID = sc.nextInt();
                    sc.nextLine();
                    Project p = projectRepo.getProjectById(projectID);
                    if(p == null){
                        System.out.println("Given Project ID not found.");
                    }
                    else{
                        System.out.println(p);
                    }
                }

                case 7 -> {
                    if(checkLogin(currentUserService)) {
                        System.out.print("Enter Project ID:");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new title:");
                        String newTitle = sc.nextLine();
                        if (currentUserService.updateTitle(newTitle, id)) {
                            System.out.print("Title Updated.");
                        }
                    }
                }

                case 8 -> {
                    if(checkLogin(currentUserService)) {
                        System.out.print("Enter Project ID:");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new abstract:");
                        String newAbstract = sc.nextLine();
                        if (currentUserService.updateTitle(newAbstract, id)) {
                            System.out.println("Abstract Updated.");
                        }
                    }
                }

                case 9 -> {
                    System.out.print("Enter Mentor Name:");
                    String mentorName = sc.nextLine();
                    System.out.print("Enter Mentor Email:");
                    String mentorEmail = sc.nextLine();
                    if(allUsersService.insertMentor(mentorName, mentorEmail)){
                        System.out.println("Mentor Added.");
                    }
                }

                case 10 -> {
                    if(checkLogin(currentUserService)) {
                        System.out.print("Enter Project ID:");
                        int id = sc.nextInt();
                        System.out.print("Enter new Mentor ID:");
                        int newMentorID = sc.nextInt();
                        sc.nextLine();
                        if (currentUserService.updateMentorId(newMentorID, id)) {
                            System.out.println("Mentor ID Updated.");
                        }
                    }
                }

                case 11 -> {
                    for (Mentor m : mentorRepo.getAllMentors()) {
                        System.out.println(m);
                        System.out.println();
                    }
                }

                case 12 -> {
                    for(Project p: projectRepo.getAllProjects()) {
                        System.out.println(p);
                    }
                }

                case 13 -> {
                    if(checkLogin(currentUserService)) {
                        System.out.print("Enter Project ID to delete:");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Do you really want to delete the project? (y/n):");
                        String confirmation = sc.next();
                        if (confirmation.equals("y")) {
                            if (currentUserService.deleteProject(id)) {
                                System.out.println("Project Deleted.");
                            }
                        }
                    }
                }

                case 0 -> {
                    alive = false;
                    System.out.println("Thank You.");
                }

                default -> System.out.println("Incorrect Choice.");
            }

            Thread.sleep(1000);
        }
    }


    private static boolean checkLogin(CurrentUserService currentUserService){
        if(currentUserService == null) {
            System.out.println("Please login to perform this action.");
            return false;
        }
        return true;
    }
}
