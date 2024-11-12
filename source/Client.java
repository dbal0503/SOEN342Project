
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends Users{
    public int age;
    public Client guardian;
    private static List<Client> clients;

    private static final Scanner scanner = new Scanner(System.in);

    private Client(String name, int uniqueId, int phone_number, int age,  Client guardian) {
        this.name = name;
        this.uniqueId = uniqueId;
        this.age = age;
        this.guardian = guardian;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static Client clientLogin(){
        System.out.println("Client Login");
        System.out.println("Enter your phone number in the following format xxx-xxx-xxxx: ");
        String phone_number = scanner.nextLine();
        Client client = findClient(phone_number);
        if (client != null) {
            System.out.println("Logged in successfully"); 
            Session.getInstance(client);
            return client;
        }
        else {
            System.out.println("Login failed");
            return null;
        }
    }

    public static Client findClient(String phone_number) {
        for (Client client : clients) {
            if (client.phone_number == parsephoneNumber(phone_number)) {
                System.out.println("Instructor found");
                return client;
            }
        }
        System.out.println("Instructor not found");
        return null;
    }
    public static void clientRegistration(){
        System.out.println("Client Registration");
        System.out.println("Please enter your name");
        String name = scanner.nextLine();
        System.out.println("Please enter your phone number");
        String phone_number = scanner.nextLine();
        System.out.println("Please enter your age");
        int age = scanner.nextInt();
        registerClient(name, phone_number, age);
    }

    private  static boolean registerClient(String name, String phone_number, int age) {
        int int_phone_number;
        int_phone_number= parsephoneNumber(phone_number);
        boolean condition = true;
        int id =0;
        Client guardian = null;
        if (age<18){
            boolean guardian_condition = true;
            System.out.println("Please enter the name of the guardian");
            String guardian_name = scanner.nextLine();
            System.out.println("Please enter the phone number of the guardian");
            String guardianphone = scanner.nextLine();
            int guardian_int_phonenumber = parsephoneNumber(guardianphone);
            for (Client client : clients) {

            if (client.phone_number == int_phone_number) {
                System.out.println("A Client with this phone number already exists");
                return false;
            }
            }
            
            while(guardian_condition){
            id = (int)(Math.random() * 1000);
            for(Client client : clients){
                if(client.uniqueId == id){
                    condition = true;
                    break;
                }
                else{
                    condition = false;
                }
            }
            }
            guardian = new Client(guardian_name, 0, guardian_int_phonenumber, 0, null);
        }
        if (Client.clients == null){
            clients = new ArrayList<>();
            Client newClient = new Client(name, id, int_phone_number, age, guardian);
            clients.add(newClient);
            return true;

        }
        for (Client client : clients) {

            if (client.phone_number == int_phone_number) {
                System.out.println("A Client with this phone number already exists");
                return false;
            }
        }
        while(condition){
        id = (int)(Math.random() * 1000);
        for(Client client : clients){
            if(client.uniqueId == id){
                condition = true;
                break;
            }
            else{
                condition = false;
            }
        }
        }
        Client newClient = new Client(name, id, int_phone_number, age, guardian);
        clients.add(newClient);
        return true;
    }



}