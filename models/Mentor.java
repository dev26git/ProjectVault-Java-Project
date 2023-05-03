package models;

public class Mentor {
    private int mentorID;
    String mentorName, mentorEmail;

    public Mentor(int mentorID, String mentorName, String mentorEmail) {
        this.mentorID = mentorID;
        this.mentorName = mentorName;
        this.mentorEmail = mentorEmail;
    }

    public Mentor() {
    }


    public int getMentorID() {
        return mentorID;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "mentorID=" + mentorID +
                ", mentorName='" + mentorName + '\'' +
                ", mentorEmail='" + mentorEmail + '\'' +
                '}';
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }
}
