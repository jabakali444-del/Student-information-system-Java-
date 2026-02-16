public class Student extends Person {
    private Course[] courses = new Course[10];
    private int[] grades = new int[10];
    private int courseCount = 0;

    public FinancialAccount financialAccount;

    public Student(String id, String name) {
        super(id, name);
        this.financialAccount = new FinancialAccount();
    }

    public void enrollCourse(Course course) {
        if (courseCount < courses.length) {
            courses[courseCount] = course;
            grades[courseCount] = -1; // No grade assigned initially
            courseCount++;
        } else {
            System.out.println("Cannot enroll more courses.");
        }
    }

    public void assignGrade(Course course, int grade) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].equals(course)) {
                grades[i] = grade;
                System.out.println("Assigned grade " + grade + " to " + courses[i].getName());
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public Course[] getCourses() {
        return courses;
    }

    public int[] getGrades() {
        return grades;
    }

    public boolean isEnrolledInCourse(Course course) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].equals(course)) {
                return true;
            }
        }
        return false;
    }

    public int getGradeForCourse(Course course) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].equals(course)) {
                return grades[i];
            }
        }
        return -1; // No grade assigned
    }
}
