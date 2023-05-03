package repos;

import models.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepo {

    public static Connection connection;

    public AuthorRepo(Connection conn){
        connection = conn;
    }

    public Author convertRowToAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setAuthorID(resultSet.getInt("authorID"));
        author.setAuthorName(resultSet.getString("authorName"));
        author.setAuthorEmail(resultSet.getString("authorEmail"));
        return author;
    }

    public List<Author> getAllAuthors() {
        List<Author> authorList = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(AuthorQueries.GET_ALL_AUTHORS);) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                Author author = convertRowToAuthor(resultSet);
                authorList.add(author);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem:" + ex.getMessage());
        }
        return authorList;
    }


    public boolean insertAuthor(String authorName, String authorEmail) {

        try (PreparedStatement pst = connection.prepareStatement(AuthorQueries.INSERT_AUTHOR);) {
            pst.setString(1, authorName);
            pst.setString(2, authorEmail);
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Problem in inserting author. " + ex.getMessage());
        }
    }


    public Author getAuthorById(int authorID) {
        Author author = null;
        try (PreparedStatement pst = connection.prepareStatement(AuthorQueries.GET_AUTHOR_BY_ID);) {

            pst.setInt(1, authorID);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                author = convertRowToAuthor(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting author by id." + ex.getMessage());
        }
        return author;
    }


    public Author getAuthorByEmail(String authorEmail) {
        try (PreparedStatement pst = connection.prepareStatement(AuthorQueries.GET_AUTHOR_BY_EMAIL);) {
            pst.setString(1, authorEmail);
            ResultSet resultSet = pst.executeQuery();
            Author author = null;
            while (resultSet.next()) {
                // convert row into author object
                author = convertRowToAuthor(resultSet);
            }
            resultSet.close();
            return author;
        } catch (SQLException ex) {
            throw new RuntimeException("Problem in getting author by email." + ex.getMessage());
        }
    }


    public Author getAuthorByName(String authorName) {
        Author author = null;
        try (PreparedStatement pst = connection.prepareStatement(AuthorQueries.GET_AUTHOR_BY_EMAIL);) {

            pst.setString(1, authorName);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                // convert row into author object
                author = convertRowToAuthor(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Problem in getting author by name." + ex.getMessage());
        }
        return author;
    }
}

