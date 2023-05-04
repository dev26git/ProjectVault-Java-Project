package models;

public class Author {
    private int authorID;
    private String authorName, authorEmail;

    public Author(int authorID, String authorName, String authorEmail) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
    }

    public Author() {}

    public int getAuthorID() {
        return authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorID=" + authorID +
                ", authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                '}';
    }
}
