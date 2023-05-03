package repos;

import models.Author;
import models.Mentor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MentorRepo {

    public static Connection connection;

    public MentorRepo(Connection conn) {
        connection = conn;
    }


    public Mentor convertRowToMentor(ResultSet resultSet) throws SQLException {
        Mentor mentor = new Mentor();
        mentor.setMentorID(resultSet.getInt("mentorID"));
        mentor.setMentorName(resultSet.getString("mentorName"));
        mentor.setMentorEmail(resultSet.getString("mentorEmail"));
        return mentor;
    }


    public List<Mentor> getAllMentors() {
        List<Mentor> mentorList = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(MentorQueries.GET_ALL_MENTORS);) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                Mentor mentor = convertRowToMentor(resultSet);
                mentorList.add(mentor);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting all mentors. " + ex.getMessage());
        }
        return mentorList;
    }


    public boolean insertMentor(String mentorName, String mentorEmail) {
        try (PreparedStatement pst = connection.prepareStatement(MentorQueries.INSERT_MENTOR);) {
            pst.setString(1, mentorName);
            pst.setString(2, mentorEmail);
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Problem in inserting mentor." + ex.getMessage());
        }
    }


    public Mentor getMentorById(int mentorID) {
        Mentor mentor = null;
        try (PreparedStatement pst = connection.prepareStatement(MentorQueries.GET_MENTOR_BY_ID);) {

            pst.setInt(1, mentorID);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                mentor = convertRowToMentor(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting mentor by id." + ex.getMessage());
        }
        return mentor;
    }


    public Mentor getMentorByEmail(String mentorEmail) {
        Mentor mentor = null;
        try (PreparedStatement pst = connection.prepareStatement(MentorQueries.GET_MENTOR_BY_EMAIL);) {

            pst.setString(1, mentorEmail);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                mentor = convertRowToMentor(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting mentor by email." + ex.getMessage());
        }
        return mentor;
    }
}
