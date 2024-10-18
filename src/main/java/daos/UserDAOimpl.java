package daos;

import Business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @Author: Jo Art Mahilaga
 */

public class UserDAOimpl extends Dao implements UserDAO {

    public UserDAOimpl(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean addUser(User user) {

        if (user == null || user.getUserName() == null || user.getPassword() == null) {
            return false;
        }

        Connection conn = super.getConnection();

        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (firstName, lastName, userName, password) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getPassword());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred while adding a user \n Error:" + e.getMessage());
            e.printStackTrace();

            return false;


        } catch (NullPointerException e) {
            System.out.println(LocalDateTime.now() + ": A NullPointerException occurred \n Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getPasswordByUserName(String userName) {
        String password = null;

        if (userName == null) {
            return null;
        }

        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE userName = ?")) {

            ps.setString(1, userName);

            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    password = rs.getString("password");
                }

            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred in the getUserByID() method.\nError: " + e.getMessage());
            e.printStackTrace();


        } catch (NullPointerException e) {
            System.out.println(LocalDateTime.now() + ": A NullPointerException occurred in the getUserByID() method.\nError: " + e.getMessage());
            e.printStackTrace();
        }

        return password;
    }


    @Override
    public User getUserByName(String userName) {
        User user = null;


        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE userName = ?")) {

            ps.setString(1, userName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getInt("userID")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred in the getUserByID() method.\nError: " + e.getMessage());
            e.printStackTrace();


        } catch (NullPointerException e) {
            System.out.println(LocalDateTime.now() + ": A NullPointerException occurred in the getUserByID() method.\nError: " + e.getMessage());
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean deleteUser(User user) {

        if (user == null) {
            return false;
        }

        try (Connection con = super.getConnection();

             PreparedStatement ps = con.prepareStatement("DELETE FROM Users WHERE userName= ?")) {

            ps.setString(1, user.getUserName());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred while deleting a user.\nError: " + e.getMessage());
            e.printStackTrace();
            return false;

        } catch (NullPointerException e) {
            System.out.println(LocalDateTime.now() + ": A NullPointerException occurred while deleting a user.\nError: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}