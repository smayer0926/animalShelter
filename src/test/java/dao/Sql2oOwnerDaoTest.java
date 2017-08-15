package dao;
import org.junit.Test;
import shelter.Animals;
import shelter.Owner;

import org.junit.After;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oOwnerDaoTest {
    private Sql2oOwnerDao ownerDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        ownerDao = new Sql2oOwnerDao(sql2o); //ignore me for now

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void getlistofAllOwners() throws Exception {
        Owner owner = setupNew();
        ownerDao.add(owner); //add to dao (takes care of saving)
        assertEquals(1, ownerDao.getAll().size());
    }
    @Test
    public void noOwnerListedIfEmpty() throws Exception {
        assertEquals(0, ownerDao.getAll().size());
    }
    @Test
    public void addingAnimalstoDatabasebyId() throws Exception {
        Owner owner = setupNew();
        int originalOwnerId = owner.getId();
        ownerDao.add(owner); //add to dao (takes care of saving)
        assertNotEquals(originalOwnerId, owner.getId()); //how does this work?
    }
    @Test
    public void existingAnimalCanBeFoundById() throws Exception {
        Owner owner = setupNew();
        ownerDao.add(owner); //add to dao (takes care of saving)
        Owner foundOwner = ownerDao.findById(owner.getId()); //retrieve
        System.out.println(foundOwner);
        System.out.println(owner);
        assertEquals(owner, foundOwner); //should be the same
    }
    @Test
    public void sortByBreed() throws Exception {
        Owner owner = setupNew();
        Owner otherOwner = setupOther();
        ownerDao.add(owner);
        ownerDao.add(otherOwner);
        List<Owner> search = ownerDao.sortByBreed();
        assertEquals("golden", search.get(0).getBreed());
    }

    public Owner setupNew(){
        return new Owner("Rich", "dinosaur","golden", "555-555-5555");
    }
    public Owner setupOther(){
        return new Owner("Martha", "whale","orange", "555-565-5555");
    }
}