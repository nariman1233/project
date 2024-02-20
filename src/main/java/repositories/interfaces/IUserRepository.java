package repositories.interfaces;

import entities.User;

public interface IUserRepository {
    User CreateUser(User user);
    User getUser(int id , String password);
    boolean updateUser(User user);
    User getUser2(int id);
}
