package dao;
import shelter.Animals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import shelter.Animals;

import static org.junit.Assert.*;

public class Sql2oAnimalsDaoTest {
    private Sql2oAnimalsDao animalsDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        animalsDao = new Sql2oAnimalsDao(sql2o); //ignore me for now

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void addingAnimalstoDatabasebyId() throws Exception {
        Animals animals = new Animals ("Bob", "male", "dinosaur","unicorn");
        int originalTaskId = animals.getId();
        animalsDao.add(animals);
        assertNotEquals(originalTaskId, animals.getId()); //how does this work?
    }
    @Test
    public void existingAnimalCanBeFoundById() throws Exception {
        Animals animals = new Animals ("Bob", "male", "dinosaur","unicorn");
        animalsDao.add(animals); //add to dao (takes care of saving)
        Animals foundTask = animalsDao.findById(animals.getId()); //retrieve
        assertEquals(animals, foundTask); //should be the same
    }
    @Test
    public void getlistofAllAnimals() throws Exception {
        Animals animals = new Animals ("Bob", "male", "dinosaur","unicorn");
        animalsDao.add(animals); //add to dao (takes care of saving)
        assertEquals(0, animalsDao.getAll().size());
    }
    @Test
    public void noAnimalsListedIfEmpty() throws Exception {
        assertEquals(0, animalsDao.getAll().size());
    }
}