import controllers.UserController;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;
import  data.PostgreDB;
import data.Interface.IDB;

import java.sql.*;

public class Main{
    public static void main(String args[]) {
        IDB db = new PostgreDB();
        IUserRepository repo = new UserRepository(db);
        UserController controller = new UserController(repo);
        MyApplication app = new MyApplication(controller , repo);
        app.start();

    }
}