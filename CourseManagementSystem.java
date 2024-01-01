import java.util.ArrayList;
import java.util.List;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> studentsEnrolled;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.studentsEnrolled = new ArrayList<>();
    }

    public int getAvailableSlots() {
        return capacity - studentsEnrolled.size();
    }

    public boolean enrollStudent(Student student) {
        if (getAvailableSlots() > 0) {
            studentsEnrolled.add(student);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudent(Student student) {
        return studentsEnrolled.remove(student);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private int studentId;
    private String name;
    private List<Course> coursesRegistered;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.coursesRegistered = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public boolean registerCourse(Course course) {
        if (course.enrollStudent(this)) {
            coursesRegistered.add(course);
            return true;
        } else {
            return false;
        }
    }

    public boolean dropCourse(Course course) {
        if (coursesRegistered.contains(course)) {
            coursesRegistered.remove(course);
            return course.removeStudent(this);
        } else {
            return false;
        }
    }

    public List<Course> getCoursesRegistered() {
        return coursesRegistered;
    }
}

public class CourseManagementSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseManagementSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + course.getAvailableSlots());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CourseManagementSystem cms = new CourseManagementSystem();

        Course csCourse = new Course("CS101", "Introduction to Computer Science", "Fundamentals of programming", 30, "Mon/Wed 9-11AM");
        Course engCourse = new Course("ENG201", "English Literature", "Exploration of famous literary works", 25, "Tue/Thu 1-3PM");

        Student alice = new Student(1, "Alice");
        Student bob = new Student(2, "Bob");

        cms.addCourse(csCourse);
        cms.addCourse(engCourse);

        cms.addStudent(alice);
        cms.addStudent(bob);

        alice.registerCourse(csCourse);
        bob.registerCourse(engCourse);

        // Displaying courses before dropping
        cms.displayCourses();

        // Dropping a course for Alice
        alice.dropCourse(csCourse);

        // Displaying courses after dropping
        cms.displayCourses();
    }
}
