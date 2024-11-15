public class Session {
    public static Users user;
    private static Session instance = null;

    private Session(Users user) {
        Session.user = user;
    }

    public static synchronized Session getInstance(Users user) {
        if (instance == null) {
            instance = new Session(user);
            System.out.println("Session created successfully for user: " + user.name);
        } else {
            System.out.println("A session is already active for user: " + instance.getUser().name);
        }
        return instance;
    }

    public static boolean hasSession() {
        return instance != null;
    }

    public Users getUser() {
        return user;
    }

    public static void logout() {
        if (instance != null) {
            System.out.println("Logging out user: " + user.name);
            instance = null;
            user = null;
        } else {
            System.out.println("No active session to log out.");
        }
    }
}