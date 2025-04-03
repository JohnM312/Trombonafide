package trombonafide;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.UUID;

import com.model.User;
import com.model.UserProgress;

/**
 * Test class for the User model class.
 */
public class UserTest {
    private User user;
    private final String TEST_FIRST_NAME = "John";
    private final String TEST_LAST_NAME = "Doe";
    private final String TEST_USERNAME = "jdoe";
    private final String TEST_PASSWORD = "password123";
    private final String TEST_EMAIL = "john.doe@example.com";

    @Before
    public void setUp() {
        user = new User(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL);
    }

    @Test
    public void testDefaultConstructor() {
        User defaultUser = new User();
        assertNotNull("UUID should not be null", defaultUser.getUuid());
        assertNotNull("Progress should not be null", defaultUser.getProgress());
        assertNull("First name should be null", defaultUser.getFirstName());
        assertNull("Last name should be null", defaultUser.getLastName());
    }

    @Test
    public void testBasicConstructor() {
        User basicUser = new User(TEST_USERNAME, TEST_PASSWORD, TEST_EMAIL);
        assertEquals("Username should match", TEST_USERNAME, basicUser.getUsername());
        assertEquals("Password should match", TEST_PASSWORD, basicUser.getPassword());
        assertEquals("Email should match", TEST_EMAIL, basicUser.getEmail());
        assertNotNull("UUID should not be null", basicUser.getUuid());
        assertNotNull("Progress should not be null", basicUser.getProgress());
    }

    @Test
    public void testFullConstructor() {
        assertEquals("First name should match", TEST_FIRST_NAME, user.getFirstName());
        assertEquals("Last name should match", TEST_LAST_NAME, user.getLastName());
        assertEquals("Username should match", TEST_USERNAME, user.getUsername());
        assertEquals("Password should match", TEST_PASSWORD, user.getPassword());
        assertEquals("Email should match", TEST_EMAIL, user.getEmail());
        assertNotNull("UUID should not be null", user.getUuid());
        assertNotNull("Progress should not be null", user.getProgress());
    }

    @Test
    public void testSetters() {
        String newFirstName = "Jane";
        String newLastName = "Smith";
        String newUsername = "jsmith";
        String newPassword = "newpassword";
        String newEmail = "jane.smith@example.com";

        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);

        assertEquals("First name should be updated", newFirstName, user.getFirstName());
        assertEquals("Last name should be updated", newLastName, user.getLastName());
        assertEquals("Username should be updated", newUsername, user.getUsername());
        assertEquals("Password should be updated", newPassword, user.getPassword());
        assertEquals("Email should be updated", newEmail, user.getEmail());
    }

    @Test
    public void testUUIDUniqueness() {
        User user1 = new User();
        User user2 = new User();
        assertNotEquals("UUIDs should be unique", user1.getUuid(), user2.getUuid());
    }

    @Test
    public void testProgressAssignment() {
        UserProgress newProgress = new UserProgress();
        newProgress.setTotalPoints(100);
        user.setProgress(newProgress);
        
        assertEquals("Progress points should be updated", 100, user.getProgress().getTotalPoints());
    }

    @Test
    public void testToString() {
        String result = user.toString();
        assertTrue("toString should contain first name", result.contains(TEST_FIRST_NAME));
        assertTrue("toString should contain last name", result.contains(TEST_LAST_NAME));
        assertTrue("toString should contain username", result.contains(TEST_USERNAME));
        assertTrue("toString should contain email", result.contains(TEST_EMAIL));
        assertFalse("toString should not contain password", result.contains(TEST_PASSWORD));
    }
}