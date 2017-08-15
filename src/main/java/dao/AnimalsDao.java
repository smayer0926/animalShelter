package dao;

import shelter.Animals;

import java.util.List;

public interface AnimalsDao {

    void add(Animals animals);

    List<Animals> getAll();

    Animals findById(int id);

    void update(String newAnimalName, String newGender, String newType, String newBreed, int id);

    void deleteById(int id);

    void clearAllAnimals();

    List<Animals> findByBreed(String breed);

    List<Animals> findByType(String type);

    List<Animals> findByName(String name);

}
