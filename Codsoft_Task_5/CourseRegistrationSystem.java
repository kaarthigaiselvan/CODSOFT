package Codsoft_Task_5;

import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity, enrolled;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public boolean isAvailable() {
        return enrolled < capacity;
    }

    public void enroll() {
        enrolled++;
    }

    public void drop() {
        if (enrolled > 0) enrolled--;
    }

    @Override
    public String toString() {
        return code + " - " + title + "\nDesc: " + description + "\nSchedule: " + schedule +
               "\nSlots: " + (capacity - enrolled) + "/" + capacity + "\n";
    }
}

class Student {
    String id, name;
    List<Course> registeredCourses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.enroll();
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.drop();
    }

    public void listRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("You haven't registered for any courses.");
        } else {
            System.out.println("Registered Courses:");
            for (Course c : registeredCourses) {
                System.out.println("- " + c.code + ": " + c.title);
            }
        }
    }
}

public class CourseRegistrationSystem {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Course> courseCatalog = new HashMap<>();
    static Student student;

    public static void main(String[] args) {
        loadCourses();

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        student = new Student(id, name);

        int choice;
        do {
            System.out.println("\n----- Course Registration Menu -----");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayCourses();
                case 2 -> registerCourse();
                case 3 -> dropCourse();
                case 4 -> student.listRegisteredCourses();
                case 5 -> System.out.println("Exiting. Thank you!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void loadCourses() {
        courseCatalog.put("CSE101", new Course("CSE101", "Java Basics", "Intro to Java programming", 3, "Mon-Wed 10AM"));
        courseCatalog.put("CSE102", new Course("CSE102", "DBMS", "Database fundamentals", 2, "Tue-Thu 11AM"));
        courseCatalog.put("CSE103", new Course("CSE103", "Web Dev", "HTML, CSS, JS basics", 4, "Mon-Fri 2PM"));
    }

    static void displayCourses() {
        System.out.println("\n--- Available Courses ---");
        for (Course course : courseCatalog.values()) {
            System.out.println(course);
        }
    }

    static void registerCourse() {
        displayCourses();
        System.out.print("Enter Course Code to register: ");
        String code = scanner.nextLine();
        Course course = courseCatalog.get(code.toUpperCase());

        if (course != null && course.isAvailable()) {
            student.registerCourse(course);
            System.out.println("✅ Registered for " + course.title);
        } else {
            System.out.println("❌ Course not available or already full.");
        }
    }

    static void dropCourse() {
        student.listRegisteredCourses();
        System.out.print("Enter Course Code to drop: ");
        String code = scanner.nextLine();

        Course course = courseCatalog.get(code.toUpperCase());
        if (course != null && student.registeredCourses.contains(course)) {
            student.dropCourse(course);
            System.out.println("❌ Dropped " + course.title);
        } else {
            System.out.println("⚠️ Not registered for this course.");
        }
    }
}
