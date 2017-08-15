package dao;


import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import shelter.Animals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sql2oAnimalsDao implements AnimalsDao{
    private final Sql2o sql2o;
    public Sql2oAnimalsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Animals animals) {
        String sql = "INSERT INTO animals (animalName, gender, type, breed) VALUES (:animalName, :gender, :type, :breed)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql) //make a new variable
                    .bind(animals) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            animals.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }
    @Override
    public List<Animals> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals") //raw sql
                    .executeAndFetch(Animals.class); //fetch a list
        }
    }
    @Override
    public Animals findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Animals.class); //fetch an individual item
        }
    }
    @Override
    public void update(String newAnimalName, String newGender, String newType, String newBreed, int id){
        String sql = "UPDATE animals SET (animalName, gender, type, breed) = (:animalName, :gender, :type, :breed) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("animalName", newAnimalName)
                    .addParameter("gender", newGender)
                    .addParameter("type", newType)
                    .addParameter("breed", newBreed)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE from animals WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void clearAllAnimals() {
        String sql = "DELETE from animals";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public List<Animals> findByBreed(String breed) {
        String sql = "SELECT * FROM animals WHERE breed = :breed";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("breed", breed) //key/value pair, key must match above
                    .executeAndFetch(Animals.class); //fetch an individual item
        }
    }
    @Override
    public List<Animals> findByType(String type) {
        String sql = "SELECT * FROM animals WHERE type = :type";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("type", type) //key/value pair, key must match above
                    .executeAndFetch(Animals.class); //fetch an individual item
        }
    }
    @Override
    public List<Animals> findByName(String name) {
        String sql = "SELECT * FROM animals WHERE name = :name";
        try(Connection con = sql2o.open()){
            List<Animals> empty = con.createQuery(sql)
                    .addParameter("name", name) //key/value pair, key must match above
                    .executeAndFetch(Animals.class); //fetch an individual item
            return Collections.sort(empty);
        }
    }
}
