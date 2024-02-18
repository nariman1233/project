package repositories.interfaces;

import entities.User;

import java.util.List;
public interface IUserRepository {
    boolean CreateUser(User user);
    User getUser(int id , String password);
}
