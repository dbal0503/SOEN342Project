public class Instructor extends Users {
    public int phone_number;
    public Specialization specialization;
    public Availabilities[] availabilities;
    
    public Instructor(String name, int id, int phone_number, Specialization specialization, Availabilities[] availabilities) {
        this.name = name;
        this.uniqueId = id;
        this.phone_number = phone_number;
        this.specialization = specialization;
        this.availabilities = availabilities;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.uniqueId;
    }

    public void setId(int id) {
        this.uniqueId = id;
    }
    public int getPhoneNumber() {
        return this.phone_number;
    }
    public int setPhoneNumber(int phone_number) {
        this.phone_number = phone_number;
    }
    public Specialization getSpecialization() {
        return this.specialization;
    }
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
    public Availabilities[] getAvailabilities() {
        return this.availabilities;
    }
    public void setAvailabilities(Availabilities[] availabilities) {
        this.availabilities = availabilities;
    }
}
