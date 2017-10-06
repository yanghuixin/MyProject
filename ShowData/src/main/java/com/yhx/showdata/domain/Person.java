package com.yhx.showdata.domain;

/**
 * Created by Administrator on 2017/9/24.
 */

public class Person {
    private String name;
    private String phone;
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Person(String name, String phone, int salary) {
        this.name = name;
        this.phone = phone;
        this.salary = salary;
    }
}
