package com.jh.societymember;

public class TMInsertAccount {

    private String Name;
    private int Age;

    public TMInsertAccount() {

    }

    public TMInsertAccount(String name, int age) {
        Name = name;
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "TMInsertAccount{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }
}
