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
            String sql = "INSERT INTO public.users(name , surname , gender ,password ,balance) VALUES(?, ? ,? ,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1 , user.getName());
            st.setString(2 , user.getSurname());
            st.setBoolean(3, user.getGender());
            st.setString(4, user.getPassword());
            st.setInt(5, user.getBalance());




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

    public  User getUser(int id , String password){
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "SELECT * FROM public.users WHERE id =? AND password = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("password"),
                        rs.getInt("balance")
                );
            }
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
        return null;
    }
    public boolean updateUser(User user) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE public.users SET name=?, surname=?, gender=?, password=?, balance=? WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setBoolean(3, user.getGender());
            st.setString(4, user.getPassword());
            st.setInt(5, user.getBalance());
            st.setInt(6, user.getId());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public User getUser2(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("password"),
                        rs.getInt("balance")
                );
                return user;
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}