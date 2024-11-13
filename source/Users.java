
public abstract class Users {
    public String name;
    public int uniqueId;
    public String phone_number;

    public static String parsephoneNumber(String phone_number) {
        String[] phone_numberArray = phone_number.split("-");
        String phoneNumber = "";
        for (String number : phone_numberArray) {
            phoneNumber += number;
        }
        System.out.println(phoneNumber);
        return phoneNumber;
    }

    
}