package shelter;

/**
 * Created by Guest on 8/15/17.
 */
public class Owner {
    private String name;
    private String type;
    private String breed;
    private String phone;
    private int id;


    public Owner(String name, String type, String breed, String phone){
        this.name =  name;
        this.type = type;
        this.breed = breed;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        if (!name.equals(owner.name)) return false;
        if (!type.equals(owner.type)) return false;
        if (!breed.equals(owner.breed)) return false;
        return phone.equals(owner.phone);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + breed.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }
}
