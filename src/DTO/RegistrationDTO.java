package DTO;

public class RegistrationDTO {

    private int RegistrationID;
    private String studentID;
    private String CourseID;

    public int getRegistrationID() {
        return RegistrationID;
    }

    public void setRegistrationID(int registrationID) {
        RegistrationID = registrationID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }
}
