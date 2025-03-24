/**
 * Facade class for music system
 * @author Aiden Campbell
 */

public class MusicSystemFACADE {
    
    private static MusicSystemFACADE facade; // Singleton instance

    private static UserList userList = UserList.getInstance();
    private static LessonList lessonList = LessonList.getInstance();
    private static ProgressTracker progressTracker = ProgressTracker.getInstance();

    private User currentUser;

    private MusicSystemFACADE() {
        this.currentUser = new User("guest", "guest", "guest");
    }

    // Singleton accessor
    public static MusicSystemFACADE getFacadeInstance() {
        if (facade == null) {
            facade = new MusicSystemFACADE();
        }
        return facade;
    }

    // Users login
    public boolean login(String username, String password) {
        User user = userList.findUser(username, password);
        if (user == null) return false;
        currentUser = user;
        return true;
    }
}
