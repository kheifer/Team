//import static spark.Spark.*;
//import spark.ModelAndView;
//import spark.template.handlebars.HandlebarsTemplateEngine;
//import models.Team;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class App {
//    public static void main(String[] args) {
//        staticFileLocation("/public");
//
//        //get: homepage/displays all teams
//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("teams", Team.getAll());
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//        //get: show form to enter new post
//        get("/teams/new",(request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model,"newTeam-form.hbs");
//        }, new HandlebarsTemplateEngine());
//        //post: process new post form
//        post("/teams/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            ArrayList<Team> listOfTeams = Team.getAll();
//            String teamName = request.queryParams("teamName");
//            String teamDescription = request.queryParams("teamDescription");
//            Team newTeam = new Team(teamName,teamDescription);
//            model.put("teams", listOfTeams);
//            return new ModelAndView(model,"index.hbs");
//        }, new HandlebarsTemplateEngine());
//        //get: show detailed information about a restaurant
//        get("/teams/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            int idOfTeam = Integer.parseInt(request.params("id"));
//            Team newTeam = Team.findById(idOfTeam);
//            ArrayList<String> newMembers = newTeam.getMembers();
//            model.put("newTeam", newTeam);
//            model.put("newMembers", newMembers);
//            return new ModelAndView(model, "team-detail.hbs");
//        }, new HandlebarsTemplateEngine());
//        //Post: add new members
//        post("/teams/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String newTeamMate = request.queryParams("newTeamMate");
//            int idOfTeam = Integer.parseInt(request.params("id"));
//            Team newTeam = Team.findById(idOfTeam);
//            ArrayList<String> newMembers = newTeam.getMembers();
//            newTeam.setMembers(newTeamMate);
//            model.put("newTeam", newTeam);
//            model.put("newMembers", newMembers);
//            return new ModelAndView(model,"team-detail.hbs");
//        }, new HandlebarsTemplateEngine());
//        //Get: Search by teammate form
//        get("/search",(request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model,"search.hbs");
//        }, new HandlebarsTemplateEngine());
//        //Post: Show team by search name
//        post("/search", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            String memberName = request.queryParams("memberName");
//            Team newTeam = Team.searchByMember(memberName);
//            model.put("newTeam", newTeam);
//            return new ModelAndView(model,"search.hbs");
//        }, new HandlebarsTemplateEngine());
//        //get: Edit entry
//        get("/teams/:id/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            int idOfTeam = Integer.parseInt(request.params("id"));
//            Team newTeam = Team.findById(idOfTeam);
//            model.put("editTeam", newTeam);
//            return new ModelAndView(model, "newTeam-form.hbs");
//        }, new HandlebarsTemplateEngine());
//        //post: Fix updated post
//        post("/teams/:id/update",(request, response) -> {
//            Map<String, Object>model = new HashMap<>();
//            ArrayList<Team> listOfTeams = Team.getAll();
//            String teamName = request.queryParams("teamName");
//            int idOfTeam = Integer.parseInt(request.params("id"));
//            Team editPlace = Team.findById(idOfTeam);
//            editPlace.update(teamName);
//            model.put("teams", listOfTeams);
//            return new ModelAndView(model,"index.hbs");
//        }, new HandlebarsTemplateEngine());
//    }
//}