package repos;

public class AuthorQueries {
    public static final String GET_ALL_AUTHORS = "select * from authors";
    public static final String INSERT_AUTHOR = "insert into authors (authorName, authorEmail) VALUES (?,?)";
    public static final String GET_AUTHOR_BY_ID = "select * from authors where authorID = ?";
    public static final String GET_AUTHOR_BY_EMAIL = "select * from authors where authorEmail LIKE ?";
    public static final String GET_AUTHOR_BY_NAME = "select * from authors where authorName LIKE ?";
}