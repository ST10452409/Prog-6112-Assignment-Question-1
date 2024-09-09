/*
STUDENT NAME: Liyema Masala
STUDENT ID:ST10452409

*/
package smapp.java;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    //Private fields made for Student ID, name, age, email, and course
    private String id;
    private String name;
    private int age;
    private String email;
    private String course;
    
    //A Constructor to create a new student object
    public Student(String id, String name, int age, String email, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }
    
    //Planned retreivers for the stored data
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }
}

class StudentDatabase {
    // Private field for an ArrayList of Student objects
    private ArrayList<Student> students;

    public StudentDatabase() {// Constructor to create a new StudentDatabase object
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student searchStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void printStudentReport() {
        for (Student student : students) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + student.getCourse());
            System.out.println();
        }
    }

    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}

public class SMAppJava {
    private static StudentDatabase database = new StudentDatabase();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        welcomeMessage();
        getUserInput();
    }

    public static void welcomeMessage() {
        System.out.println("*");
        System.out.println("STUDENT MANAGEMENT APPLICATION");
        System.out.println("*");
        System.out.println("Enter (1) to launch menu or press any button to exit.");
    }

    public static void getUserInput() {
        int choice = scanner.nextInt();

        if (choice == 1) {
            menu();
        } else {
            System.out.println("Exiting application. Goodbye!");
        }
    }

    public static void menu() {
        System.out.println();
        System.out.println("Please select one of the following menu items:");
        System.out.println("*");
        System.out.println("(1)  Capture new Student.");
        System.out.println("(2)  Search for a student.  ");
        System.out.println("(3)  Delete a student. ");
        System.out.println("(4)  Print student Report. ");
        System.out.println("(5)  Exit Application. ");
        System.out.println("*");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                searchStudent();
                break;
            case 3:
                deleteStudent();
                break;
            case 4:
                printStudentReport();
                break;
            case 5:
                System.out.println("Exiting application. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                menu();
        }
    }

    public static void addStudent() {
    System.out.println();
    System.out.println("Capture a New Student");
    System.out.println("*");
    System.out.println("Enter student's ID (5 digits):");
    String studentId = scanner.next().trim();
    while (studentId.length() != 5 || !studentId.matches("\\d+")) {
        System.out.println("Invalid ID. Please enter a 5-digit number.");
        studentId = scanner.next().trim();// trims the spaces
    }

    System.out.println("Enter student's name:");
    String name = scanner.next().trim(); 

    int age;
    while (true) {
        System.out.println("Enter student's age:");
        if (scanner.hasNextInt()) {
            age = scanner.nextInt();
            if (age >= 17) {
                break;
            } else {
                System.out.println("You have entered an incorrect student age!!! Please re-enter the student");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid age.");
            scanner.next(); // Clear invalid input
        }
    }

    System.out.println("Enter student's email:");
    String email;
    while (true) {
        email = scanner.next().trim();
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            break;
        } else {
           System.out.println("Invalid email format. Please enter a valid email.");
        }
    }

    System.out.println("Enter student's course:");
    String course = scanner.next().trim();

    Student student = new Student(studentId, name, age, email, course);
    database.addStudent(student);

    System.out.println("Student added successfully!");
    System.out.println("Name: " + student.getName());
    System.out.println("Age: " + student.getAge());
    System.out.println("Email: " + student.getEmail());
    System.out.println("Course: " + student.getCourse());

    System.out.println("Enter (1) to launch menu or any other key to exit.");
    int choice = scanner.nextInt();
    if (choice == 1) {
        menu();
    } else {
        System.out.println("Exiting application. Goodbye!");
    }
}

    private static void searchStudent() {
    System.out.println("Enter student's ID to search:");
    String studentId = scanner.next().trim();

    Student student = database.searchStudent(studentId);

    if (student!= null) {
        System.out.println("Student ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Course: " + student.getCourse());
    } else {
        System.out.println("Student with Student Id: " + studentId + " was not found!");
    }

    System.out.println("Enter (1) to launch menu or any other key to exit.");
    int choice = scanner.nextInt();
    if (choice == 1) {
        menu();
    } else {
        System.out.println("Exiting application. Goodbye!");
    }
}

    private static void deleteStudent() {
    System.out.println("Enter student's ID to delete:");
    String studentId = scanner.next().trim();

    if (database.searchStudent(studentId) != null) {
        database.deleteStudent(studentId);
        System.out.println("Student with Id " + studentId + " was deleted!");
    } else {
        System.out.println("Student with Id " + studentId + " was not found!");
    }

    System.out.println("Enter (1) to launch menu or any other key to exit.");
    int choice = scanner.nextInt();
    if (choice == 1) {
        menu();
    } else {
        System.out.println("Exiting application. Goodbye!");
    }
}

   private static void printStudentReport() {
    System.out.println("Student Report:");
    System.out.println("--------------------------");
    
    for (Student student : database.getStudents()) { //uses student datadase to display student info in report form 
        System.out.println("Student ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Course: " + student.getCourse());
        System.out.println("------------------------");
    }
    
    System.out.println("Enter (1) to launch menu or any other key to exit.");
    int choice = scanner.nextInt();
    if (choice == 1) {
        menu();
    } else {
        System.out.println("Exiting application. Goodbye!");
    }
}
    
}


