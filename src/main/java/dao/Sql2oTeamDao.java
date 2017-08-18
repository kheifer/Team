package dao;

import models.Member;
import models.Team;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oTeamDao implements TeamDao {
    private final Sql2o sql2o;
    public Sql2oTeamDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(Team team) {

    }

    @Override
    public List<Team> getAll() {
        return null;
    }

    @Override
    public List<Member> getAllMembersByTeamId(int id) {
        return null;
    }

    @Override
    public Team findById(int id) {
        return null;
    }

    @Override
    public void update(String name, String description, int id) {

    }

    @Override
    public void deleteAllTeams() {

    }

    @Override
    public void deleteById(int id) {

    }
}
