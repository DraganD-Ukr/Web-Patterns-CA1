package daoTests;

import Business.User;
import daos.UserDAOimpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: Jo Art Mahilaga
 */


public class UserDAOimplTest {

    private static User user1, guyToBeDeleted, newUser;

    @BeforeAll
    public static void init() {

        // Sample users for testing
        user1 = User.builder()
                .firstName("John")
                .lastName("Doe")
                .userName("johndoe")
                .password("password123")
                .userID(1)
                .build();

        guyToBeDeleted = User.builder()
                .firstName("Guy")
                .lastName("ToBeDeleted")
                .userName("guytobedeleted")
                .password("hashed_password_3")
                .userID(3)
                .build();

        newUser = User.builder()
                .firstName("New")
                .lastName("User")
                .userName("newuser")
                .password("hashed_password_new")
                .userID(4)
                .build();
    }

    @AfterAll
    public static void cleanUp() {
        // Clean up resources and delete any test data created during tests
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");
        userDao.deleteUser(newUser);
    }


    @Test
    public void addUser_Success() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        boolean result = userDao.addUser(newUser);

        assertEquals(true, result);
    }

    @Test
    public void addUser_Failure_NullUser() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        boolean result = userDao.addUser(null);

        assertEquals(false, result);
    }

    @Test
    public void addUser_Failure_NullUsernameOrPassword() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        User userWithoutUsername = User.builder()
                .firstName("John")
                .lastName("Doe")
                .userName(null)
                .password("hashed_password_1")
                .userID(1)
                .build();

        assertEquals(false, userDao.addUser(userWithoutUsername));

        User userWithoutPassword = User.builder()
                .firstName("Jane")
                .lastName("Smith")
                .userName("janesmith")
                .password(null)
                .userID(2)
                .build();

        assertEquals(false, userDao.addUser(userWithoutPassword));
    }

    @Test
    public void getPasswordByUserName_UserExists() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");
        System.out.println(user1.getUserName());
        String password = userDao.getPasswordByUserName(user1.getUserName());

        assertEquals(user1.getPassword(), password);
    }

    @Test
    public void getPasswordByUserName_NotFound() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        String password = userDao.getPasswordByUserName("nonexistentuser");

        assertEquals(null, password);
    }

    @Test
    public void getUserByName_UserExists() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        User user = userDao.getUserByName(user1.getUserName());

        assertEquals(user1, user);
    }

    @Test
    public void getUserByName_NotFound() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        User user = userDao.getUserByName("nonexistentuser");

        assertEquals(null, user);
    }

    @Test
    public void deleteUser_Success() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Add a user to delete
        userDao.addUser(guyToBeDeleted);


        boolean result = userDao.deleteUser(guyToBeDeleted);

        assertEquals(true, result);
    }

    @Test
    public void deleteUser_Failure_NullUser() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        boolean result = userDao.deleteUser(null);

        assertEquals(false, result);
    }

    @Test
    public void deleteUser_Failure_InvalidID() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        User invalidUser = User.builder()
                .firstName("Invalid")
                .lastName("User")
                .userName("invaliduser")
                .password("hashed_password_invalid")
                .userID(-1)
                .build();

        boolean result = userDao.deleteUser(invalidUser);

        assertEquals(false, result);
    }


}