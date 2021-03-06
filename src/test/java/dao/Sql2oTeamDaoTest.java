package dao;

import models.Member;
import models.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        teamDao =  new Sql2oTeamDao(sql2o);
       memberDao = new Sql2oMemberDao(sql2o);

        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void teamAddsNewInstanceWithId() throws Exception {
        Team team = setNewTeam();
        teamDao.add(team);
        assertEquals(1, team.getId());
    }

    @Test
    public void getAllTeams() throws Exception {
        Team team = setNewTeam();
        Team team1 = setNewTeam();
        teamDao.add(team);
        teamDao.add(team1);
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void teamGetAllMembersByTeamId() throws Exception {
        Team team = setNewTeam();
        Team team1 = setNewTeam();
        teamDao.add(team);
        teamDao.add(team1);
        int id = team.getId();
        Member member = new Member("Maxamillion Ulanoff", "New York, NY", "Project Manager", 32,team.getId());
        Member member1 = new Member("Max Maddock", "Portland, OR", "Software Developer", 29,team.getId());
        memberDao.add(member);
        memberDao.add(member1);
        assertEquals(2, teamDao.getAllMembersByTeamId(id).size());
    }

    @Test
    public void findMyName() throws Exception{

    }

    @Test
    public void teamFindById() throws Exception {
        Team team = setNewTeam();
        Team team1 = setNewTeam2();
        Team team2 = setNewTeam();
        teamDao.add(team);
        teamDao.add(team1);
        teamDao.add(team2);
        Team find = teamDao.findById(team.getId());
        assertEquals(team.getTeamName(), find.getTeamName());
    }

    @Test
    public void teamUpdateAnEntry() throws Exception {
        Team team = setNewTeam();
        Team team1 = setNewTeam2();
        Team team2 = setNewTeam();
        teamDao.add(team);
        teamDao.add(team1);
        teamDao.add(team2);
        int find = team.getId();
        teamDao.update("West","West of East", find);
        assertNotEquals(team.getTeamName(),teamDao.findById(find).getTeamName());
    }

    @Test
    public void teamDaoDeletesAllTeams() throws Exception {
        Team team = setNewTeam();
        Team team1 = setNewTeam2();
        Team team2 = setNewTeam();
        teamDao.add(team);
        teamDao.add(team1);
        teamDao.add(team2);
        teamDao.deleteAllTeams();
        assertEquals(0,teamDao.getAll().size());
    }

    @Test
    public void TeamdeleteById() throws Exception {
        Team team = setNewTeam();
        Team team1 = setNewTeam2();
        Team team2 = setNewTeam();
        teamDao.add(team);
        teamDao.add(team1);
        teamDao.add(team2);
        int delete = team.getId();
        teamDao.deleteById(delete);
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void findByMemberName() throws Exception {
        Member member2 = new Member("Scott Mescudi", "Norfolk, CT", "Carpenter", 36, 2);
        memberDao.add(member2);
        Team team = setNewTeam();
        Team team1 = setNewTeam2();
        Team team2 = setNewTeam();
        teamDao.add(team);
        teamDao.add(team1);
        teamDao.add(team2);
        Team search = teamDao.findByMemberName(member2.getName());
        assertEquals(team1.getTeamName(),search.getTeamName());


    }

    public Team setNewTeam(){
        return new Team("Warriors","we wear cool red jackets");
    }
    public Team setNewTeam2(){
        return new Team("NorfNorf","we wear cool blue jackets");
    }
}