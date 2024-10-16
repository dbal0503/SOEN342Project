public class Instructor extends Users {
    
    public Instructor(String name, int id) {
        this.name = name;
        this.uniqueId = id;
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
}
