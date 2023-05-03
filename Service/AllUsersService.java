package Service;

import models.Project;
import repos.MentorRepo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AllUsersService {

    public static Connection connection;

    public AllUsersService(Connection conn) {
        connection = conn;
    }


    private Project convertRowToProject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setProjectID(resultSet.getInt("projectID"));
        project.setProjectTitle(resultSet.getString("projectTitle"));
        project.setProjectAbstract(resultSet.getString("projectAbstract"));
        project.setAuthorID(resultSet.getInt("authorID"));
        project.setMentorID(resultSet.getInt("mentorID"));
        return project;
    }


    public List<Project> searchKeyword(String keyword) throws SQLException {
        List<Project> projectList = new ArrayList<>();

        Statement stmt = connection.createStatement();
        {
            String sql = "SELECT * FROM projects "
                    + "WHERE projectTitle LIKE '%" + keyword + "%' "
                    + "OR projectAbstract LIKE '%" + keyword + "%'";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next())
            {
                Project project = new Project();
                project = convertRowToProject(resultSet);
                projectList.add(project);
            }
            resultSet.close();
        }

        return projectList;
    }


    public boolean insertMentor(String mentorName, String mentorEmail) {
        MentorRepo mentorRepo = new MentorRepo(connection);
        return mentorRepo.insertMentor(mentorName, mentorEmail);
    }
}





