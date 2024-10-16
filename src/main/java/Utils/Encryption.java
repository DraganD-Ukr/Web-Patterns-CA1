package Utils;

import daos.userDAO;
import daos.userDAOimpl;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class Encryption {
    public Encryption() {
    }


    //Method to check hash password
    public boolean checkPasswordWithUsername(String password, String username){
        //Get the hashed password from the database
        userDAO userDAO = new userDAOimpl("CA1_test");
        String hashedPassword = userDAO.getPasswordByUserName(username);

        //Check if the password is correct
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }

    //Method to hash password
    public String hashPassword(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

}
