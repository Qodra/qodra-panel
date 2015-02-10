package org.qodra.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.qodra.dao.User;
import org.qodra.exception.NotAuthorizedException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Test
    public void createUser() {
        //Init
        String username = "user_test";
        String password = "user_test";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail("user_test@qodra.org");
        UserService service = new UserService();

        //Execute
        User result = service.createUser(user);

        //Assert
        assertNotNull(result.getId());
        assertNotEquals(password, result.getPassword());
    }

    @Test
    public void doLogin() {
        //Init
        String username = "user_test";
        String password = "user_test";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserService service = new UserService();

        //Execute
        User result = service.login(user);

        //Assert
        assertNotNull(result);
    }

    @Test(expected = NotAuthorizedException.class)
    public void doLoginWithWrongPassword() {
        //Init
        String username = "user_test";
        String password = "wrong_password";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserService service = new UserService();

        //Execute
        service.login(user);
    }

    @Test
    public void doUpdate() {
        //Init
        String username = "user_test";
        String newEmail = "newmail@qodra.org";
        UserService service = new UserService();

        //Execute
        User result = service.getUserByUsername(username);
        result.setEmail(newEmail);
        service.updateUser(result);
        result = service.getUserByUsername(username);

        //Assert
        assertNotNull(result.getId());
        assertEquals(newEmail, result.getEmail());
    }

    @Test
    public void removeUser() {
        //Init
        String username = "user_test";
        String password = "user_test";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserService service = new UserService();

        //Execute
        service.deleteUser(user);
        User result = service.getUserByUsername(username);

        //Assert
        assertNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void userNullOnCreateMustReturnNullPointerExceptionException() {
        //Init
        UserService service = new UserService();

        //Execute
        service.createUser(null);
    }

    @Test(expected = NullPointerException.class)
    public void userNullOnUpdateMustReturnNullPointerExceptionException() {
        //Init
        UserService service = new UserService();

        //Execute
        service.updateUser(null);
    }

    @Test(expected = NullPointerException.class)
    public void userNullOnDeleteMustReturnNullPointerExceptionException() {
        //Init
        UserService service = new UserService();

        //Execute
        service.deleteUser(null);
    }

    @Test(expected = NullPointerException.class)
    public void userNullOnLoginMustReturnNullPointerExceptionException() {
        //Init
        UserService service = new UserService();

        //Execute
        service.login(null);
    }

    @Test(expected = NullPointerException.class)
    public void usernameNullOnGetUserByUsernameMustReturnNullPointerExceptionException() {
        //Init
        UserService service = new UserService();

        //Execute
        service.getUserByUsername(null);
    }

    @Test(expected = NullPointerException.class)
    public void usernameNullOnLoginMustReturnNullPointerExceptionException() {
        //Init
        User user = new User();
        UserService service = new UserService();

        //Execute
        service.login(user);
    }

    @Test(expected = NullPointerException.class)
    public void passwordNullOnLoginMustReturnNullPointerExceptionException() {
        //Init
        User user = new User();
        UserService service = new UserService();

        //Execute
        service.login(user);
    }


}
