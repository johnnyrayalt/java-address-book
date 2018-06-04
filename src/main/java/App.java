import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.EntryDao;
import dao.Sql2oEntryDao;
import models.Entry;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oEntryDao entryDao = new Sql2oEntryDao(sql2o);

        get("/entries/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            entryDao.clearAllEntries();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/entries/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEntryToDelete = Integer.parseInt(request.params("id"));
            entryDao.deleteById(idOfEntryToDelete);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Entry> entries = entryDao.getAll();
            model.put("entries", entries);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("entries/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "entry-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/entries", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phoneNumber = request.queryParams("phoneNumber");
            String mailingAddress = request.queryParams("mailingAddress");
            String emailAddress = request.queryParams("emailAddress");
            Entry newEntry = new Entry(name, phoneNumber, mailingAddress, emailAddress);
            entryDao.add(newEntry);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/entries/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(request.params("id"));
            Entry foundEntry = entryDao.findById(idOfPostToFind);
            model.put("entry", foundEntry);
            return new ModelAndView(model, "entry-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/entries/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(request.params("id"));
            Entry editEntry = entryDao.findById(idOfPostToEdit);
            model.put("editEntry", editEntry);
            return new ModelAndView(model, "entry-form.hba");
        }, new HandlebarsTemplateEngine());

        post("/entries/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            int idOfPostToEdit = Integer.parseInt(request.params("id"));
            entryDao.update(idOfPostToEdit, newName);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

    }
}
