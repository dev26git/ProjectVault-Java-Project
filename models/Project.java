package models;

public class Project {
    private int projectID;
    private String projectTitle, projectAbstract;
    private int authorID, mentorID;

    public Project() {
    }

    public Project(int projectID, String projectTitle, String projectAbstract, int authorID, int mentorID) {
        this.projectID = projectID;
        this.projectTitle = projectTitle;
        this.projectAbstract = projectAbstract;
        this.authorID = authorID;
        this.mentorID = mentorID;
    }

    public int getProjectID() {
        return projectID;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectAbstract() {
        return projectAbstract;
    }

    public void setProjectAbstract(String projectAbstract) {
        this.projectAbstract = projectAbstract;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    @Override
    public String toString() {
        return  "--------------------------------------" +
                "\nTITLE: " + projectTitle +
                "\nABSTRACT: " + projectAbstract + //.substring(0, Math.min(200, projectAbstract.length())) +
                "\nAuthor ID: " + authorID +
                "\nMentor ID: " + mentorID +
                "\nProject ID:" + projectID +
                "\n--------------------------------------";
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
