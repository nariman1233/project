package org.example;

public class User{
    private int id;
    private String name;
    private String surname;
    private boolean gender;

    public User(int id, String name, String surname, boolean gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isGender() {
        return gender;
    }

    @Override
    public String toString() {
        return id + ". " + name +" " + surname + "-" + (gender ? "male" : "female");
    }
}
