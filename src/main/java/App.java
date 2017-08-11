import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import models.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: homepage/displays all teams
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("teams", Team.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show form to enter new post
        get("/teams/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"newTeam-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new post form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> listOfTeams = Team.getAll();
            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("teamDescription");
            Team newTeam = new Team(teamName,teamDescription);
            model.put("teams", listOfTeams);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show detailed information about a restaurant
        get("/restaurants/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfTeam = Integer.parseInt(request.params("id"));
            Team newTeam = Team.findById(idOfTeam);
            model.put("newTeam", newTeam);
            return new ModelAndView(model, "restaurant-detail.hbs");
        }, new HandlebarsTemplateEngine());
    }
}