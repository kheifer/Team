package dao;

import models.Member;
import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTeamDao implements TeamDao {
    private final Sql2o sql2o;
    public Sql2oTeamDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(Team team) {
        String sql = "INSERT INTO team(teamName, teamDescription) VALUES (:teamName, :teamDescription)";
        try(Connection con = sql2o.open()){ //opens a connection(con)
            int id = (int) con.createQuery(sql) //makes a new (int)variable and queries the sql database
                    .bind(team) //maps the argument onto the query to pull information from it
                    .executeUpdate() //executes the argument then updates the database
                    .getKey(); //gets the row key and sets it as the int id and finishes the query
            team.setId(id); //sets the team id as the int id from the query
        } catch (Sql2oException ex) {//uses a catch to throw an error if something goes awry
            System.out.println(ex); //if there is an error it outputs the error
        }
    }

    @Override
    public List<Team> getAll() {
        try(Connection con = sql2o.open()){//opens a connection
            return con.createQuery("SELECT * FROM team")//creates a new query search
                    .executeAndFetch(Team.class);//executesthequery and fetches(returns) the resulting classes
        }
    }

    @Override
    public List<Member> getAllMembersByTeamId(int id) {
        return null;
    }

    @Override
    public Team findById(int id) {
        String sql = "SELECT * FROM team WHERE id = :id"; //creates a new query search command to select a team by Id
        try(Connection con = sql2o.open()){//opens a connection
            return con.createQuery(sql)//starts the return and queries the db
                    .addParameter("id", id)//defines what id means
                    .executeAndFetchFirst(Team.class);//executes the query and returns the first option found
        }
    }

    @Override
    public void update(String name, String description, int id) {
        String search = "UPDATE team SET(teamName, teamDescription)=(:teamName, :teamDescription) WHERE id = :id";
        try(Connection con =sql2o.open()){
            con.createQuery(search)
                    .addParameter("teamName", name)
                    .addParameter("teamDescription", description)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAllTeams() {
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM team")
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {

    }
}
