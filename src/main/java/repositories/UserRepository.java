package repositories;

import data.Interface.IDB;
import entities.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db){
        this.db = db;
    }

    @Override
    public boolean CreateUser(User user) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "INSERT INTO public.users(name , surname , gender) VALUES(?, ? ,? )";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1 , user.getName());
            st.setString(2 , user.getSurname());
            st.setBoolean(3, user.getGender());

            st.execute();
            return true;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
        return false;
    }
}