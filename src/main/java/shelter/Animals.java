package shelter;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Animals {
    private String animalName;
    private String gender;
    private String type;
    private String breed;
    private LocalDateTime createdAt;
    private static ArrayList<Animals> adoptables = new ArrayList<>();

    public Animals(String Animal, String gender, String type, String breed){
        this.animalName = Animal;
        this.gender = gender;
        this.type = type;
        this.breed = breed;
        this.createdAt = LocalDateTime.now();
        adoptables.add(this);
    }
}
