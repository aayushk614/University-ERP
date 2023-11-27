import DAO.EnrollmentsDAO;
import DAO.FacultyDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import DTO.EnrollmentsDTO;
import DTO.FacultyDTO;
import DTO.StudentDTO;
import DTO.SubjectDTO;

public class Main {
    public static void main(String[] args) {


        StudentDAO obj = new StudentDAO();
        System.out.println("Displaying Student Table");
        obj.displayStudentTable();

//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setStudentID("MT21001");
//        studentDTO.setName("Test1");
//        studentDTO.setEmail("test1@gmail.com");

        //obj.addStudentDAO(studentDTO);

        //obj.displayCustomTable("student");

        //obj.deleteStudentDAO("MT21001");

        //obj.displayCustomTable("student");

//
//        FacultyDAO ob1 = new FacultyDAO();
//        ob1.displayFacultyTable();
//
//
//
//        FacultyDTO facultyDTO = new FacultyDTO();
//        facultyDTO.setFaculty_id(104);
//        facultyDTO.setFaculty_name("Test 1");
//        facultyDTO.setEmail("test1@gmail.com");
//        facultyDTO.setAge(56);
//        facultyDTO.setGender("Male");
//        facultyDTO.setEmployment_type("Regular");

//        //ob1.addFacultyDAO(facultyDTO);
//        ob1.deleteFacultyDAO(104);
//
//
//        ob1.displayFacultyTable();


        // Remove student record -> enrollment removed

        //StudentDAO ob1 = new StudentDAO();
        //ob1.displayStudentTable();

        //ob1.deleteStudentDAO("MT22005");

        //ob1.displayStudentTable();

//        EnrollmentsDAO ob2 = new EnrollmentsDAO();
//        ob2.displayEnrollmentsTable();
//
//        SubjectDTO ob3 = new SubjectDTO();
//
//        SubjectDAO ob4 = new SubjectDAO();
//        ob4.deleteSubjectDAO("CSE604");
//
//        ob2.displayEnrollmentsTable();











    }
}