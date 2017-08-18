import static spark.Spark.*;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import models.Team;
import models.Member;
import org.sql2o.Sql2o;

import static spark.Spark.staticFileLocation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/team.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);

        //get: homepage/displays all teams
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> teamList = teamDao.getAll();
            List<Member> memberList = memberDao.getAll();
            model.put("teams", teamList);
            model.put("members", memberList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
//        //get: show form to enter new team
        get("/teams/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model,"newTeam-form.hbs");
        }, new HandlebarsTemplateEngine());
        //post: process new team form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("teamDescription");
            Team newTeam = new Team(teamName,teamDescription);
            teamDao.add(newTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show detailed information about a team
//        get("/teams/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            int idOfTeam = Integer.parseInt(request.params("id"));
//            List<Member>
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
    }
}