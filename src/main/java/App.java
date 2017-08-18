import static spark.Spark.*;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import models.Team;
import models.Member;
import org.sql2o.Sql2o;

import static spark.Spark.staticFileLocation;
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
            Map<String, Object> model = new HashMap<>();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
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
       // get: show detailed information about a team
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeam = Integer.parseInt(request.params("id"));
            List<Member> membersByTeam = teamDao.getAllMembersByTeamId(idOfTeam);
            model.put("members", membersByTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            Team thisTeam = teamDao.findById(idOfTeam);
            model.put("thisTeam",thisTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());
        //get:edit team info
        get("/teams/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeam = Integer.parseInt(request.params("id"));
            Team editTeam = teamDao.findById(idOfTeam);
            model.put("editTeam", editTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model,"newTeam-form.hbs");
        }, new HandlebarsTemplateEngine());
        //Post: edit team info
        post("/teams/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeam = Integer.parseInt(request.params("id"));
            String newTeamName = request.queryParams("teamName");
            String newTeamDescription = request.queryParams("TeamDescription");
            teamDao.update(newTeamName,newTeamDescription, idOfTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show for, to enter new teammate
        get("/members/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model,"member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new teammate
        post("/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String homeTown = request.queryParams("homeTown");
            String occupation = request.queryParams("occupation");
            Integer age= Integer.parseInt(request.queryParams("age"));
            int memberId = Integer.parseInt(request.queryParams("memberId"));
            Member member = new Member(name, homeTown,occupation,age,memberId);
            memberDao.add(member);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show individual member info
        get("/teams/:memberId/members/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamMember = Integer.parseInt(request.params("id"));
            Member member = memberDao.findById(idOfTeamMember);
            int idOfTeam =  Integer.parseInt(request.params("memberId"));
            Team team = teamDao.findById(idOfTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("team",team);
            model.put("member", member);
            model.put("teams", teamList);
            return new ModelAndView(model,"member-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get a form to update  a member's info
        get("/teams/:memberId/members/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Member editMember = memberDao.findById(Integer.parseInt(request.params("id")));
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            model.put("editMember", editMember);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //Post: process an update with a member's info
        post("/teams/:memberId/members/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String homeTown = request.queryParams("homeTown");
            String occupation = request.queryParams("occupation");
            Integer age= Integer.parseInt(request.queryParams("age"));
            int memberId = Integer.parseInt(request.queryParams("memberId"));
            int id = Integer.parseInt(request.params("id"));
            memberDao.update(name, homeTown,occupation,age,id, memberId);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a teammate
        get("/teams/:memberId/members/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.deleteById(Integer.parseInt(request.params("id")));
            List<Team> teamList = teamDao.getAll();
            model.put("neighborhoods", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all teammamtes for all teams
        get("/members/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.deleteAllMembers();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a team
        get("/teams/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            teamDao.deleteById(id);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: prompt to search for team by username
        get("/search",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model,"search.hbs");
        }, new HandlebarsTemplateEngine());

        //post: run a find function on the input
        post("/search", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String memberName = request.queryParams("memberName");
            if (  teamDao.findByMemberName(memberName) != null ) {
                Team search = teamDao.findByMemberName(memberName);
                model.put("team", search);
            } else if( teamDao.findByMemberName(memberName) == null ){
                return null;
            }
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "search.hbs");
        }, new HandlebarsTemplateEngine());
    }
}