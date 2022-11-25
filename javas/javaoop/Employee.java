package oop;

public class Employee extends Person implements Worker {
    private int employeeId;

    public Employee() {}

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Age: " + getAge() + ", EmployeeId: " + employeeId;
    }

    @Override
    public void doSomething() {
        System.out.println(getName() + " is doing something");
    }

    @Override
    public void work() {
        System.out.println(getName() + " is working");
    }
}