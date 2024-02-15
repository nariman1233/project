package controllers;

import entities.User;
import  repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo){
        this.repo = repo;
    }
   public String createUser(String name , String surname , String gender){
        boolean male = (gender.toLowerCase().equals("male"));
        User user = new User(name , surname , male);

        boolean created = repo.CreateUser(user);

        return (created ? "User was created!" : "User creation was failed!");
   }


}
