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

    private static User user1,guyToBeDeleted, newUser, newUser2;

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

        newUser2 = User.builder()
                .firstName("New")
                .lastName("User")
                .userName("newuser2")
                .password("hashed_password_new2")
                .userID(5)
                .build();
    }

    @AfterAll
    public static void cleanUp() {
        // Clean up resources and delete any test data created during tests
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");
        userDao.deleteUser(newUser);
        userDao.deleteUser(newUser2);
    }

    @Test
    public void addUser_Success() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Test adding a new user
        boolean result = userDao.addUser(newUser);

        assertEquals(true, result);
    }

    @Test
    public void addUser_Failure_NullUser() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Test adding a null user
        boolean result = userDao.addUser(null);

        assertEquals(false, result);
    }



    @Test
    public void getPasswordByUserName_UserExists() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Ensure the user is added before checking password
        String password = userDao.getPasswordByUserName(user1.getUserName());

        assertEquals(user1.getPassword(), password);
    }

    @Test
    public void getPasswordByUserName_NotFound() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Test getting a nonexistent user's password
        String password = userDao.getPasswordByUserName("nonexistentuser");

        assertEquals(null, password);
    }

    @Test
    public void getUserByName_UserExists() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Ensure the user is added before checking existence
        User user = userDao.getUserByName(user1.getUserName());

        assertEquals(user1, user);
    }

    @Test
    public void getUserByName_NotFound() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Test getting a nonexistent user
        User user = userDao.getUserByName("nonexistentuser");

        assertEquals(null, user);
    }

    @Test
    public void deleteUser_Success() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Ensure the guyToBeDeleted is added before deletion
        userDao.addUser(guyToBeDeleted);

        // Test deleting an existing user
        boolean result = userDao.deleteUser(guyToBeDeleted);

        assertEquals(true, result);
    }

    @Test
    public void deleteUser_Failure_NullUser() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Test deleting a null user
        boolean result = userDao.deleteUser(null);

        assertEquals(false, result);
    }

    @Test
    public void deleteUser_Failure_InvalidID() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Test deleting a user with an invalid ID
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

    @Test
    public void existsByUserName_UserExists() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        // Ensure the new user's data is added before checking existence.
        userDao.addUser(newUser2);

        // Test checking existence of a user by username
        boolean existsResult = userDao.existsbyUserName(newUser2.getUserName());
        assertEquals(true, existsResult, "Newly added username should exist.");
    }

    @Test
    public void existsByUsername_NotFound() {
        UserDAOimpl userDao = new UserDAOimpl("CA1_test");

        boolean existsResult = userDao.existsbyUserName("nonexistentusername");
        assertEquals(false, existsResult, "Nonexistent username should not exist.");

    }
}