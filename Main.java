import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CourseManager cm = new CourseManager();
        Scanner scanner = new Scanner(System.in);

        // Predefined available courses
        String[] courseNames = {
                "Citizenship and Society", "Discrete Mathematics", "Calculus", "Programming 1", "Lab Programming I",
                "Computer Networks", "Accounting and Finance", "Linear Algebra", "Programming II", "Lab Programming II",
                "Computer Architecture", "Web Design 1", "Oral and Written Communication", "Applied Probability & Statistics",
                "Data Structure and Algorithms", "Programming III", "Database Design", "UX/UI Design", "Artificial Intelligence",
                "Operating Systems", "Database Programming", "Web Development"
        };

        // Add courses to CourseManager
        for (String courseName : courseNames) {
            String id = courseName.replaceAll("\\s", "_");
            int credits = courseName.contains("Programming") || courseName.contains("Lab") ? 1 : 3;
            cm.addCourse(new Course(id, courseName, credits));
        }

        // Load data from CSV file
        LoadManager.loadFromCSV(cm.getStudents(), cm.getCourses(), "students.csv");

        while (true) {
            System.out.println("\n==== Course Management Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. Enroll Student in Course");
            System.out.println("3. Assign Grade");
            System.out.println("4. View Student Information");
            System.out.println("5. View Financial Account");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine().trim();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine().trim();
                    cm.addStudent(new Student(studentId, studentName));
                    System.out.println("Student added.");
                    SaveManage.saveToCSV(cm.getStudents(), cm.getCourses(), "students.csv");
                    break;

                case "2":
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine().trim();
                    Student student = findStudent(cm, studentId);
                    if (student != null) {
                        System.out.println("Available Courses:");
                        for (int i = 0; i < cm.getCourses().length; i++) {
                            if (cm.getCourses()[i] != null) {
                                System.out.println((i + 1) + ". " + cm.getCourses()[i].getName());
                            }
                        }
                        System.out.print("Choose course number to enroll: ");
                        int courseIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (courseIndex >= 0 && courseIndex < cm.getCourses().length) {
                            Course selectedCourse = cm.getCourses()[courseIndex];
                            student.enrollCourse(selectedCourse);
                            student.financialAccount.chargeCourse(selectedCourse);
                            System.out.println("Student enrolled in " + selectedCourse.getName());
                            SaveManage.saveToCSV(cm.getStudents(), cm.getCourses(), "students.csv");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine().trim();
                    student = findStudent(cm, studentId);
                    if (student != null) {
                        System.out.println("Courses student is enrolled in:");
                        Course[] enrolledCourses = student.getCourses();
                        for (int i = 0; i < enrolledCourses.length; i++) {
                            if (enrolledCourses[i] != null) {
                                System.out.println((i + 1) + ". " + enrolledCourses[i].getName());
                            }
                        }
                        System.out.print("Choose course number to assign grade: ");
                        int gradeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (enrolledCourses[gradeIndex] != null) {
                            System.out.print("Enter grade: ");
                            int grade = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter admin password: ");
                            String password = scanner.nextLine();
                            Admin admin = new Admin("A001", "System Admin", "admin123");
                            admin.assignGrade(student, enrolledCourses[gradeIndex], grade, password);
                            System.out.println("Grade assigned.");
                            SaveManage.saveToCSV(cm.getStudents(), cm.getCourses(), "students.csv");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "4":
                    System.out.print("Enter student ID to view details: ");
                    studentId = scanner.nextLine().trim();
                    student = findStudent(cm, studentId);
                    if (student != null) {
                        System.out.println("Student ID: " + student.id);
                        System.out.println("Student Name: " + student.name);
                        System.out.println("Courses Enrolled:");
                        for (Course c : student.getCourses()) {
                            if (c != null) {
                                int grade = student.getGradeForCourse(c);
                                System.out.println("  - " + c.getName() + " | Grade: " + (grade != -1 ? grade : "N/A"));
                            }
                        }
                        System.out.println("Total Balance Due: $" + student.financialAccount.getBalance());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "5":
                    System.out.print("Enter student ID to check financial account: ");
                    studentId = scanner.nextLine().trim();
                    student = findStudent(cm, studentId);
                    if (student != null) {
                        System.out.println("Student Name: " + student.name);
                        System.out.println("Outstanding Balance: $" + student.financialAccount.getBalance());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case "6":
                    System.out.println("Exiting.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static Student findStudent(CourseManager cm, String id) {
        for (Student s : cm.getStudents()) {
            if (s != null && s.id.equals(id)) {
                return s;
            }
        }
        return null;
    }
}
