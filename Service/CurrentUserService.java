package Service;

import models.Author;
import repos.ProjectRepo;
import models.Project;

import java.sql.Connection;
import java.util.List;

public class CurrentUserService {

    public Connection connection;
    private int currAuthorID;
    private String currAuthorName, currAuthorEmail;


    public CurrentUserService(Connection conn, Author currentUser) {
        this.connection = conn;
        this.currAuthorID = currentUser.getAuthorID();
        this.currAuthorName = currentUser.getAuthorName();
        this.currAuthorEmail = currentUser.getAuthorEmail();
    }


    // Check if current user has access to a specific project
    private boolean hasAccessToProject(int projectID){
        ProjectRepo projectRepo = new ProjectRepo(connection);
        Project project = projectRepo.getProjectById(projectID);
        if(project.getAuthorID() != currAuthorID){
            System.out.println("Failure. You can only insert, update or delete your own projects.");
            return false;
        }
        return true;
    }


    public List<Project> getCurrentUsersProjects() {
        ProjectRepo projectRepo = new ProjectRepo(connection);
        return projectRepo.getProjectsByAuthorId(currAuthorID);
    }


    public boolean insertProject(String projectTitle, String projectAbstract, int mentorID) {
        ProjectRepo projectRepo = new ProjectRepo(connection);
        return projectRepo.insertProject(projectTitle, projectAbstract, currAuthorID, mentorID);
    }


    public boolean deleteProject(int projectID) {
        if(hasAccessToProject(projectID)) {
            ProjectRepo projectRepo = new ProjectRepo(connection);
            return projectRepo.deleteProject(projectID);
        }
        return false;
    }


    public boolean updateTitle(String newTitle, int projectID) {
        if(hasAccessToProject(projectID)) {
            ProjectRepo projectRepo = new ProjectRepo(connection);
            return projectRepo.updateTitle(newTitle, projectID);
        }
        return false;
    }


    public boolean updateAbstract(String newAbstract, int projectID) {
        if(hasAccessToProject(projectID)) {
            ProjectRepo projectRepo = new ProjectRepo(connection);
            return projectRepo.updateAbstract(newAbstract, projectID);
        }
        return false;
    }


    public boolean updateMentorId(int newMentorID, int projectID) {
        if(hasAccessToProject(projectID)) {
            ProjectRepo projectRepo = new ProjectRepo(connection);
            return projectRepo.updateMentorID(newMentorID, projectID);
        }
        return false;
    }
}
