
import java.util.List;
import java.util.Scanner;

public class Client extends Users {
    public int age;
    public Client guardian;
    private static List<Client> clients;
    private static final Scanner scanner = new Scanner(System.in);

    public Client(String name, int uniqueId, String phone_number, int age, Client guardian) {
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

    public int getUniqueId() {
        return uniqueId;
    }

    public static Client clientLogin() {
        System.out.println("Client Login");
        System.out.println("Enter your phone number in the following format XXX-XXX-XXXX: ");
        String phoneNumber = scanner.nextLine();

        String parsedPhoneNumber = Users.parsephoneNumber(phoneNumber);

        Client client = ClientDAO.getClientByPhoneNumber(parsedPhoneNumber);

        if (client != null) {
            System.out.println("Logged in successfully. Welcome, " + client.name + "!");
            Session.getInstance(client);
            return client;
        } else {
            System.out.println("Login failed. No client found with the provided phone number.");
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

    public static void clientRegistration() {
        System.out.println("Client Registration");
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        System.out.println("Please enter your phone number in format: XXX-XXX-XXXX");
        String phone_number = scanner.nextLine();
        System.out.println("Please enter your age:");
        int age = scanner.nextInt();
        scanner.nextLine();

        String phoneNumber = Users.parsephoneNumber(phone_number);

        if (ClientDAO.getClientByPhoneNumber(phoneNumber) != null) {
            System.out.println("A client with this phone number already exists.");
            return;
        }

        Integer guardianId = null;
        if (age < 18) {
            System.out.println("Please enter the guardian's name:");
            String guardianName = scanner.nextLine();
            System.out.println("Please enter the guardian's phone number in format: XXX-XXX-XXXX");
            String guardianPhone = scanner.nextLine();
            System.out.println("Please enter the guardian's age:");
            int guardianAge = scanner.nextInt();
            scanner.nextLine();

            String guardianPhoneC = Users.parsephoneNumber(guardianPhone);

            // Check if the guardian already exists
            Client guardian = ClientDAO.getClientByPhoneNumber(guardianPhoneC);
            if (guardian == null) {
                // Insert guardian into database and get the uniqueId
                guardianId = ClientDAO.insertClient(guardianName, guardianPhoneC, guardianAge, null);
                if (guardianId == null) {
                    System.out.println("Failed to register guardian.");
                    return;
                }
            }
        }

            Integer clientIdDB = ClientDAO.insertClient(name, phoneNumber, age, guardianId);
            if (clientIdDB != null) {
                System.out.println("Client registered successfully with ID: " + clientIdDB);
            } else {
                System.out.println("Failed to register client.");
            }

    }
}