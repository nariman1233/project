package controllers;

import entities.User;
import  repositories.interfaces.IUserRepository;

import javax.swing.*;
import java.util.List;

public class UserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo){
        this.repo = repo;
    }
   public String createUser(String name , String surname , String gender , String password , int balance){
        boolean male = (gender.toLowerCase().equals("male"));
        User user = new User(name , surname , male , password , balance);

        boolean created = repo.CreateUser(user);

        return (created ? "User was created!" : "User creation was failed!");
   }
   public String getUser(int id , String password){
        User user = repo.getUser(id , password);

        return (user == null ? "User was not found!" : user.toString());
   }


}
