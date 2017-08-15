package shelter;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Animals {
    private String animalName;
    private String gender;
    private String type;
    private String breed;
    private LocalDateTime createdAt;
    private int id;

    public Animals(String animalName, String gender, String type, String breed){
        this.animalName = animalName;
        this.gender = gender;
        this.type = type;
        this.breed = breed;
        this.createdAt = LocalDateTime.now();
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

        Animals animals = (Animals) o;

        if (!animalName.equals(animals.animalName)) return false;
        if (!gender.equals(animals.gender)) return false;
        if (!type.equals(animals.type)) return false;
        return breed.equals(animals.breed);
    }

    @Override
    public int hashCode() {
        int result = animalName.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + breed.hashCode();
        return result;
    }
}
