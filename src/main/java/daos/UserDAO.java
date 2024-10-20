package daos;
import Business.User;

/**
 * @Author: Jo Art Mahilaga
 */

public interface UserDAO {

    boolean addUser(User user);

    String getPasswordByUserName(String userName);

    User getUserByName(String userName);

    boolean deleteUser(User user);

}
