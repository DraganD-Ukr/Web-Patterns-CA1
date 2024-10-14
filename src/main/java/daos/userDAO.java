package daos;

import Business.User;

import java.util.List;

public interface UserDAO {

 boolean addUser(User user);

 String getPasswordbyUserName(String userName);

 User getUserByID(String userName);

 boolean deleteUser(User user);



}
