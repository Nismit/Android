package ca.ciccc.listviewex;

public class Person {
    private String name;
    private String color;
    private int age;

    public Person(String name, String color, int age) {
        setName(name);
        setColor(color);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
