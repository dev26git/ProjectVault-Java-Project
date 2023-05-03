package repos;

public class ProjectQueries {
    public static final String INSERT_PROJECT = "INSERT INTO projects (projectTitle,projectAbstract,authorID,mentorID) VALUES(?,?,?,?)";
    public static final String GET_ALL_PROJECTS = "select * from projects";
    public static final String GET_PROJECT_BY_ID = "select * from projects WHERE projectID = ?";
    public static final String GET_PROJECT_BY_AUTHOR_ID = "select * from projects WHERE authorID = ?";
    public static final String GET_PROJECT_BY_MENTOR_ID = "select * from projects WHERE mentorID = ?";
    public static final String UPDATE_TITLE = " UPDATE projects set projectTitle = ? WHERE projectID = ?";
    public static final String UPDATE_ABSTRACT = " UPDATE projects set projectAbstract = ? WHERE projectID = ?";
    public static final String UPDATE_MENTOR_ID = " UPDATE projects set mentorID = ? WHERE projectID = ?";
    public static final String DELETE_PROJECT = "DELETE FROM projects WHERE projectID = ?";
}
