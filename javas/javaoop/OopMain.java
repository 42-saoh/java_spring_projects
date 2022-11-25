package oop;

import java.util.Arrays;
import java.util.Scanner;

public class OopMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            Person p = new Person();
            Employee em = new Employee();
            p.setName("Chris");
            p.setAge(20);
            em.setName("John");
            em.setAge(27);
            em.setEmployeeId(1);
            Person[] persons = new Person[2];
            persons[0] = p;
            persons[1] = em;
            for (Person person : persons) {
                System.out.println(person.toString());
                person.doSomething();
                if (Arrays.stream(person.getClass().getInterfaces()).toList().contains(Worker.class)) {
                    ((Worker) person).work();
                }
            }
            System.out.println("Do you want to continue? (y or any key)");
            flag = scanner.nextLine().equals("y");
        }
    }
}
