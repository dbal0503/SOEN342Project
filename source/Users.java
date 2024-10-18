
public abstract class Users {
    public String name;
    public int uniqueId;
    public int phone_number;

    public static int parsephoneNumber(String phone_number) {
        String[] phone_numberArray = phone_number.split("-");
        String phoneNumber = "";
        for (String number : phone_numberArray) {
            phoneNumber += number;
        }
        return Integer.parseInt(phoneNumber);
    }

    
}