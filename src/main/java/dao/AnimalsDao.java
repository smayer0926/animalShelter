package dao;

import shelter.Animals;

import java.util.List;

public interface AnimalsDao {

    void add(Animals animals);

    List<Animals> getAll();

    Animals findById(int id);


}
