package dao;

import shelter.Animals;
import shelter.Owner;

import java.util.List;

/**
 * Created by Guest on 8/15/17.
 */
public interface OwnerDao {
    void add(Owner owner);

    List<Owner> getAll();

    Owner findById(int id);

    List<Owner> sortByBreed();

}
