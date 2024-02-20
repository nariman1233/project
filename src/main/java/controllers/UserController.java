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
   public User createUser( String name , String surname , String gender , String password , int balance){
        boolean male = (gender.toLowerCase().equals("male"));
        User user = new User(name , surname , male , password , balance);

       User createdUser = repo.CreateUser(user);
       return createdUser;

   }
   public String getUser(int userId , String password){
        User user = repo.getUser(userId , password);

        return (user == null ? "User was not found!" : user.toString());
   }

    public String getUser2(int userId){
        User user2 = repo.getUser2(userId);

        return (user2 == null ? "User was not found!" : user2.toString());
    }





}
