public class Admin extends Person {
    private String password;

    public Admin(String id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    public void assignGrade(Student student, Course course, int grade, String inputPassword) {
        if (this.password.equals(inputPassword)) {
            student.assignGrade(course, grade);
        } else {
            System.out.println("Incorrect password. Grade not assigned.");
        }
    }

    public void viewGrades(Student[] students) {
        for (Student student : students) {
            if (student != null) {
                System.out.println(student);
                for (Course course : student.getCourses()) {
                    if (course != null) {
                        int grade = student.getGradeForCourse(course);
                        System.out.println("  Enrolled in: " + course.getName() + " | Grade: " + (grade != -1 ? grade : "N/A"));
                    }
                }
            }
        }
    }
}
