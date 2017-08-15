package dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import shelter.Owner;

import java.util.List;

/**
 * Created by Guest on 8/15/17.
 */
public class Sql2oOwnerDao implements OwnerDao {
    private final Sql2o sql2o;
    public Sql2oOwnerDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public List<Owner> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM owner") //raw sql
                    .executeAndFetch(Owner.class); //fetch a list
        }
    }
    @Override
    public Owner findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM owner WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Owner.class); //fetch an individual item
        }
    }
    @Override
    public void add(Owner owner) {
        String sql = "INSERT INTO owner (name, type, breed, phone) VALUES (:name, :type, :breed, :phone)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql) //make a new variable
                    .bind(owner) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            owner.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }
    @Override
    public List<Owner> sortByBreed() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM owner ORDER BY breed") //raw sql
                    .executeAndFetch(Owner.class); //fetch a list
        }
    }
}
