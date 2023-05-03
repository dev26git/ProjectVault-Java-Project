package repos;

import models.Author;
import models.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepo {

    public static Connection connection;

    public ProjectRepo(Connection conn) {
        connection = conn;
    }

    public Project convertRowToProject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setProjectID(resultSet.getInt("projectID"));
        project.setProjectTitle(resultSet.getString("projectTitle"));
        project.setProjectAbstract(resultSet.getString("projectAbstract"));
        project.setAuthorID(resultSet.getInt("authorID"));
        project.setMentorID(resultSet.getInt("mentorID"));
        return project;
    }


    public boolean insertProject(String projectTitle, String projectAbstract, int authorID, int mentorID) {

        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.INSERT_PROJECT);) {
            pst.setString(1, projectTitle);
            pst.setString(2, projectAbstract);
            pst.setInt(3, authorID);
            pst.setInt(4, mentorID);
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Problem in inserting author. " + ex.getMessage());
        }
    }

    public List<Project> getAllProjects() {
        List<Project> projectList = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.GET_ALL_PROJECTS);) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                Project project = convertRowToProject(resultSet);
                projectList.add(project);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting all products. " + ex.getMessage());
        }
        return projectList;
    }


    public Project getProjectById(int projectID) {
        Project project = null;
        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.GET_PROJECT_BY_ID);) {

            pst.setInt(1, projectID);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                project = convertRowToProject(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting project by id." + ex.getMessage());
        }
        return project;
    }


    public List<Project> getProjectsByAuthorId(int authorID) {
        List<Project> projectList = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.GET_PROJECT_BY_AUTHOR_ID);) {

            pst.setInt(1, authorID);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                Project project = convertRowToProject(resultSet);
                projectList.add(project);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting project." + ex.getMessage());
        }
        return projectList;
    }


    public Project getProjectByMentorId(int mentorID) {
        Project project = null;
        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.GET_PROJECT_BY_MENTOR_ID);) {

            pst.setInt(1, mentorID);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                project = convertRowToProject(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting project." + ex.getMessage());
        }
        return project;
    }


    public boolean updateTitle(String newTitle, int projectID) {
        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.UPDATE_TITLE);) {
            pst.setString(1, newTitle);
            pst.setInt(2, projectID);
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Problem. " + ex.getMessage());
        }
    }


    public boolean updateAbstract(String newAbstract, int projectID) {
        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.UPDATE_ABSTRACT);) {
            pst.setString(1, newAbstract);
            pst.setInt(2, projectID);
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Problem. " + ex.getMessage());
        }
    }


    public boolean updateMentorID(int newMentorID, int projectID) {
        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.UPDATE_MENTOR_ID);) {
            pst.setInt(1, newMentorID);
            pst.setInt(2, projectID);
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Problem. " + ex.getMessage());
        }
    }


    public boolean deleteProject(int projectID) {
        try (PreparedStatement pst = connection.prepareStatement(ProjectQueries.DELETE_PROJECT);) {
            pst.setInt(1, projectID);
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Problem. " + ex.getMessage());
        }
    }
}
