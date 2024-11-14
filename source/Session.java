public class Session {
    public static Users user;
    private static Session instance = null;

    private Session(Users user) {
        Session.user = user;
    }

    public static synchronized Session getInstance(Users user) {
        if (instance == null) {
            instance = new Session(user);
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
        instance = null;
        user = null;
    }
}