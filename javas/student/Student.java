package student;

import java.util.Scanner;

public class Student {
    private String name;
    private String department;
    private int studentId;

    public Student () {}
    public Student(String name, String department, int studentId) {
        this.name = name;
        this.department = department;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String toString() {
        return "Student name:" + name + ", department:" + department + ", studentId:" + studentId;
    }

    public static void main(String[] args) {
        Scanner studentScanner = new Scanner(System.in);
        do {
            Student student1 = new Student();
            System.out.println("Enter student name:");
            String name = studentScanner.nextLine();
            student1.setName(name);
            System.out.println("Enter student department:");
            String department = studentScanner.nextLine();
            student1.setDepartment(department);
            System.out.println("Enter student id:");
            int studentId = Integer.parseInt(studentScanner.nextLine());
            student1.setStudentId(studentId);
            System.out.println("Enter student name:");
            name = studentScanner.nextLine();
            System.out.println("Enter student department:");
            department = studentScanner.nextLine();
            System.out.println("Enter student id:");
            studentId = Integer.parseInt(studentScanner.nextLine());
            Student student2 = new Student(name, department, studentId);
            Student[] students = new Student[2];
            students[0] = student1;
            students[1] = student2;
            for (Student student : students) {
                System.out.println("student name: " + student.getName());
                System.out.println("student department: " + student.getDepartment());
                System.out.println("student id: " + student.getStudentId());
                System.out.println(student);
            }
            System.out.println("Do you want to continue? (y or any key)");
        } while (studentScanner.nextLine().equals("y"));
    }
}