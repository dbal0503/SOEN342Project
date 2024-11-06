class Client{
    String name;
    int uniqueId;
    int age;
    Client guardian;

    public Client(String name, int uniqueId, int age, Client guardian) {
        this.name = name;
        this.uniqueId = uniqueId;
        this.age = age;
        this.guardian = guardian;
    }

    public static void clientLogin(){
        System.out.println("Client Login");
    }
    public static void clientRegistration(){
        System.out.println("Client Registration");
    }
    
}