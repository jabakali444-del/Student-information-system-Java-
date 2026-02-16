import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadManager {

    public static void loadFromCSV(Student[] students, Course[] courses, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Student currentStudent = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Student")) {
                    String studentId = parts[1];
                    String studentName = parts[2];
                    currentStudent = new Student(studentId, studentName);
                    addStudentToArray(students, currentStudent);
                } else if (parts[0].equals("Course") && currentStudent != null) {
                    String courseName = parts[1];
                    int grade = Integer.parseInt(parts[2]);
                    Course course = findCourseByName(courses, courseName);
                    if (course != null) {
                        currentStudent.enrollCourse(course);
                        currentStudent.assignGrade(course, grade);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addStudentToArray(Student[] students, Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    private static Course findCourseByName(Course[] courses, String courseName) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null && courses[i].getName().equals(courseName)) {
                return courses[i];
            }
        }
        return null;
    }
}
