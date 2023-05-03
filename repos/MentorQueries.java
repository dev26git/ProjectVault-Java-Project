package repos;

public class MentorQueries {
    public static final String GET_ALL_MENTORS = "SELECT * FROM mentors";
    public static final String INSERT_MENTOR = "INSERT INTO mentors (mentorName, mentorEmail) VALUES(?,?)";
    public static final String GET_MENTOR_BY_ID = "SELECT * FROM mentors WHERE mentorID = ?";
    public static final String GET_MENTOR_BY_EMAIL = "SELECT * FROM mentors WHERE mentorEmail = ?";
}
