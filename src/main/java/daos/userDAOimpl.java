package daos;

import Business.User;

public class UserDAOimpl extends Dao implements UserDAO {

    public UserDAOimpl(String databaseName) {
        super(databaseName);
    }

    public boolean addUser(User user) {
        return false;
    }

//implement hash and salting
    public String getPasswordbyUserName(String userName) {
        return null;
    }

    public User getUserByID(String userName){
        return null;
    }

    public boolean deleteUser(User user) {
        return false;
    }

}
