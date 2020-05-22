package com.rest.app.model;

public class User1 {
    private int id;
    private String name;
    private double salary;
    private String department;
    private  String image;

    public User1() {
    }

    public User1(int id, String name, double salary, String department, String image) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
