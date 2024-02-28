import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int studentsLength = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerLn = new Scanner(System.in);
        Student student = new Student();

        Student[] students = new Student[studentsLength];

        while (true) {
            System.out.println("""
                    1. Add student
                    2. Get all students
                    3. Update student
                    4. Delete student
                    """);
            int command = scanner.nextInt();

            switch (command) {
                case 1:
                    System.out.println("Write student's ID: ");
                    int id = scanner.nextInt();

                    System.out.println("Write student's firstname: ");
                    String firstName = scannerLn.nextLine();

                    System.out.println("Write student's lastname: ");
                    String lastName = scannerLn.nextLine();

                    System.out.println("Write student's email: ");
                    String email = scannerLn.nextLine();

                    System.out.println("Write student's age: ");
                    int age = scanner.nextInt();

                    students = addStudent(id, firstName, lastName, email, age, students);
                    break;
                case 2:
                    getAllStudents(students);
                    break;
                case 3:
                    System.out.println("Write new student's ID: ");
                    int newId = scanner.nextInt();

                    System.out.println("Write new student's firstname: ");
                    String newFirstName = scannerLn.nextLine();

                    System.out.println("Write new student's lastname: ");
                    String newLastName = scannerLn.nextLine();

                    System.out.println("Write new student's email: ");
                    String newEmail = scannerLn.nextLine();

                    System.out.println("Write new student's age: ");
                    int newAge = scanner.nextInt();
                    updateStudent(newId, newFirstName, newLastName, newEmail, newAge, students);
                    break;
                case 4:
                    System.out.println("Write student's ID: ");
                    int deletionId = scanner.nextInt();
                    students = deleteStudent(deletionId, students);
            }
        }
    }


    public static Student[] addStudent(int id, String firstName, String lastName, String email, int age, Student[] students) {
        int overlap = 0;
        for (Student student : students) {
            if (student != null) {
                if (student.getId() == id) {
                    overlap++;
                    break;
                }
            }
        }
        if (overlap == 0) {
            if (students[students.length - 1] != null) {
                students = Arrays.copyOf(students, students.length * 2);
            }
            for (int i = 0; i < students.length; i++) {
                if (students[i] == null) {
                    if (email.contains("@")) {
                        students[i] = new Student(id, firstName, lastName, email, age);
                        System.out.println(firstName + " successfully added !!!");
                        break;
                    } else {
                        System.out.println("Mail must have an '@' sign");
                    }
                }
            }
        } else {
            System.out.println("A student with the same id already exists");
        }
        return students;
    }


    public static void getAllStudents(Student[] students) {
        boolean hasStudents = false;
        for (Student student : students) {
            if (student != null) {
                System.out.println(student);
                hasStudents = true;
            }
        }
        if (!hasStudents) {
            System.out.println("No students");
        }
    }

    public static void updateStudent(int id, String firstName, String lastName, String email, int age, Student[] students) {
        int index = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getId() == id) {
                index = i;
            }
        }
        if (index > -1) {
            students[index].setFirstName(firstName);
            students[index].setLastName(lastName);
            if (email.contains("@")) {
                students[index].setEmail(email);
            } else {
                System.out.println("Mail must have an '@' sign");
            }
            students[index].setAge(age);
            System.out.println(firstName + " successfully updated !!!");
        } else {
            System.out.println("No student found with their ID");
            System.out.println("Please write existing id:");
        }
    }

    public static Student[] deleteStudent(int id, Student[] students) {
        boolean studentFound = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == id) {
                students[i] = null;
                studentFound = true;
                System.out.println("Student with ID " + id + " successfully deleted !!!");
                break;
            }
        }
        if (!studentFound) {
            System.out.println("No student found with their ID");
        } else {
            Student[] updatedStudents = new Student[students.length - 1];
            int index = 0;
            for (Student student : students) {
                if (student != null) {
                    updatedStudents[index++] = student;
                }
            }
            return updatedStudents;
        }
        return students;
    }
}