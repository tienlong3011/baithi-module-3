package com.model;

public class Student {
    private int id;
    private String nameStudent;
    private String className;

    public Student(int id, String nameStudent,String className) {
        this.id = id;
        this.nameStudent = nameStudent;
        this.className = className;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
