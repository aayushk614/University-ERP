package DTO;

//Class for Enrollments
//variables are self explanatory
//functions are getters and setters for the variables

public class EnrollmentsDTO {
    private int EnrollmentID;
    private String studentID;
    private String CourseID;
    private String Grade;
    private int NumericGrade;

    public int getEnrollmentID() {
        return EnrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) {
        EnrollmentID = enrollmentID;
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

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public int getNumericGrade() {
        return NumericGrade;
    }

    public void setNumericGrade(int numericGrade) {
        NumericGrade = numericGrade;
    }
}
