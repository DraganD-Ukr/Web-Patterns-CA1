package daos;
import Business.User;

import java.util.List;

public interface userDAO {

    boolean addUser(User user);

    String getPasswordByUserName(String userName);

    User getUserByID(String userName);

    boolean deleteUser(User user);

}
