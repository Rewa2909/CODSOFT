import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    int enrolled;
    
    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public void enroll() {
        if (!isFull()) {
            enrolled++;
        }
    }

    public void drop() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    @Override
    public String toString() {
        return code + ": " + title + " (" + enrolled + "/" + capacity + ")\n" + description + "\nSchedule: " + schedule;
    }
}

class Student {
    String id;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (!course.isFull() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enroll();
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.drop();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(id).append("\nName: ").append(name).append("\nRegistered Courses:\n");
        for (Course course : registeredCourses) {
            sb.append(course.code).append(": ").append(course.title).append("\n");
        }
        return sb.toString();
    }
}

public class CourseRegistrationSystem {
    private static HashMap<String, Course> courseDatabase = new HashMap<>();
    private static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample courses
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basics of computer science.", 30, "Mon/Wed/Fri 10:00-11:00"));
        courseDatabase.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to calculus.", 25, "Tue/Thu 09:00-10:30"));

        // Sample students
        studentDatabase.put("001", new Student("001", "Joey"));
        studentDatabase.put("002", new Student("002", "Chandler"));

        while (true) {
            System.out.println("Course Registration System:");
            System.out.println("1. List Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Student Info");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    viewStudentInfo(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void listCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentDatabase.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.next();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.registerCourse(course);
        System.out.println("Course registered successfully.");
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentDatabase.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.next();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
        System.out.println("Course dropped successfully.");
    }

    private static void viewStudentInfo(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentDatabase.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println(student);
    }
}

