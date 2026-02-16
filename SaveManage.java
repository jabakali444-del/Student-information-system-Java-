import java.io.FileWriter;
import java.io.IOException;

public class SaveManage {

    public static void saveToCSV(Student[] students, Course[] courses, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < students.length; i++) {
                if (students[i] != null) {
                    writer.write("Student," + students[i].id + "," + students[i].name + "\n");
                    for (int j = 0; j < courses.length; j++) {
                        if (courses[j] != null && students[i].isEnrolledInCourse(courses[j])) {
                            int grade = students[i].getGradeForCourse(courses[j]);
                            writer.write("Course," + courses[j].getName() + "," + grade + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
