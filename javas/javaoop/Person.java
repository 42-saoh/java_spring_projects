package oop;

public class Person {
    private String name;
    private int age;

    public Person() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Age: " + getAge();
    }

    public void doSomething() {
        System.out.println(getName() + " is doing something");
    }
}