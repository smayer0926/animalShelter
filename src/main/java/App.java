import dao.Sql2oAnimalsDao;
import dao.Sql2oOwnerDao;
import org.sql2o.Sql2o;
import shelter.Animals;
import shelter.Owner;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Created by Guest on 8/15/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oOwnerDao ownerDao = new Sql2oOwnerDao(sql2o);
        Sql2oAnimalsDao animalsDao = new Sql2oAnimalsDao(sql2o);

        //get: Get ALL instances of objects
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animals> animals = animalsDao.getAll();
            List<Owner> owner = ownerDao.getAll();
            model.put("owners", owner);
            model.put("animals", animals);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: Delete All Animals
        get("/animals/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            animalsDao.clearAllAnimals();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new animals form
        get("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-input-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a new Animal form
        post("/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String type = request.queryParams("type");
            String breed = request.queryParams("breed");
            Animals newAnimal = new Animals(name, gender, type, breed); //ignore the hardcoded categoryId
            animalsDao.add(newAnimal);
            model.put("newAnimal", newAnimal);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new owner form
        get("/owners/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "owner-input-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a new Owner form
        post("/owners/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String type = request.queryParams("type");
            String breed = request.queryParams("breed");
            String phone = request.queryParams("phone");
            Owner newOwner = new Owner(name, type, breed, phone);
            ownerDao.add(newOwner);
            model.put("newOwner", newOwner);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show an individual animal
        get("animals/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimalToFind = Integer.parseInt(req.params("id"));
            Animals foundAnimals = animalsDao.findById(idOfAnimalToFind);
            model.put("animals", foundAnimals);
            return new ModelAndView(model, "animal-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show an individual person
        get("owners/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfOwnerToFind = Integer.parseInt(req.params("id"));
            Owner foundOwner = ownerDao.findById(idOfOwnerToFind);
            model.put("owners", foundOwner);
            return new ModelAndView(model, "owner-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show a form to update a Animal
        get("/animals/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimalToEdit = Integer.parseInt(req.params("id"));
            Animals editAnimal = animalsDao.findById(idOfAnimalToEdit);
            model.put("editAnimal", editAnimal);
            return new ModelAndView(model, "animal-input-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a Animal
        post("/animals/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String type = request.queryParams("type");
            String breed = request.queryParams("breed");
            int idOfAnimalToEdit = Integer.parseInt(request.queryParams("id"));
            Animals editAnimal = animalsDao.findById(idOfAnimalToEdit);
            animalsDao.update(name, gender, type, breed, idOfAnimalToEdit); //ignore the hardcoded categoryId for now.
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual Animal
        get("categories/:category_id/Animals/:task_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAnimalToDelete = Integer.parseInt(req.params("task_id"));
            Animals deleteAnimal = animalsDao.findById(idOfAnimalToDelete);
            animalsDao.deleteById(idOfAnimalToDelete);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
