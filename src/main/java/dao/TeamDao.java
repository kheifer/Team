package dao;

import models.Member;
import models.Team;

import java.util.List;

public interface TeamDao {

    void add(Team team);

    List<Team> getAll();

    List<Member> getAllMembersByTeamId(int id);

    Team findByMemberName(String memberName);

    Team findById(int id);

    void update(String name, String description, int id);

    void deleteAllTeams();

    void deleteById(int id);

}
