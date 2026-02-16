public class CourseManager {
    private Student[] students = new Student[100];
    private Course[] courses = new Course[100];
    private int studentCount = 0;
    private int courseCount = 0;

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount] = student;
            studentCount++;
        } else {
            System.out.println("Cannot add more students.");
        }
    }

    public void addCourse(Course course) {
        if (courseCount < courses.length) {
            courses[courseCount] = course;
            courseCount++;
        } else {
            System.out.println("Cannot add more courses.");
        }
    }

    public Course[] getCourses() {
        return courses;
    }

    public Student[] getStudents() {
        return students;
    }
}
