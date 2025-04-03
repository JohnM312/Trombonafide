package trombonafide;

import com.model.User;
import com.model.UserList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

/**
 * Tests for the UserList class to verify user management and singleton behavior.
 */
public class UserListTest {

    private UserList userList;

    /**
     * Clears users before each test to keep everything isolated.
     */
    @Before
    public void setUp() {
        userList = UserList.getInstance();
        userList.getUsers().clear();
    }

    /**
     * Tests that a new user is successfully added to the list.
     */
    @Test
    public void testAddUser() {
        User user = new User("John", "Doe", "jdoe", "pass", "john@example.com");
        userList.addUser(user);

        List<User> users = userList.getUsers();
        assertEquals(1, users.size());
        assertEquals("jdoe", users.get(0).getUsername());
    }

    /**
     * Tests that a user is successfully removed from the list.
     */
    @Test
    public void testRemoveUser() {
        User user = new User("Jane", "Smith", "jsmith", "pass", "jane@example.com");
        userList.addUser(user);
        userList.removeUser(user);

        assertTrue(userList.getUsers().isEmpty());
    }

    /**
     * Tests that the singleton instance returns the same object.
     */
    @Test
    public void testSingletonInstance() {
        UserList anotherInstance = UserList.getInstance();
        assertSame(userList, anotherInstance);
    }

    /**
     * Tests that getUsers returns a list that reflects additions.
     */
    @Test
    public void testGetUsersReflectsChanges() {
        assertEquals(0, userList.getUsers().size());

        userList.addUser(new User("Alice", "Wonder", "alice", "pass", "alice@example.com"));
        assertEquals(1, userList.getUsers().size());
    }

    /**
     * Tests that toString returns a summary string.
     */
    @Test
    public void testToString() {
        userList.addUser(new User("Bob", "Builder", "bob", "pass", "bob@example.com"));
        assertNotNull(userList.toString());
        assertTrue(userList.toString().contains("users="));
    }
}
