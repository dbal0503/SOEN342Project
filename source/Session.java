public class Session{
    public static Users user;
    private static Session instance = null;
    private Session(Users user) {
        Session.user = user;
    }

    public static Session getInstance(Users user) {
        if (instance == null) {
            instance = new Session(user);
        }
        return instance;
    }
}