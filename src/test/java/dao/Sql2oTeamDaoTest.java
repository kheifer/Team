package dao;

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
//        memberDao = new Sql2oMemberDao(sql2o);

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

//    @Test
//    public void getAllMembersByTeamId() throws Exception {
//    }
//
//    @Test
//    public void findById() throws Exception {
//    }
//
//    @Test
//    public void update() throws Exception {
//    }
//
//    @Test
//    public void deleteAllTeams() throws Exception {
//    }
//
//    @Test
//    public void deleteById() throws Exception {
//    }

    public Team setNewTeam(){
        return new Team("Warriors","we wear cool red jackets");
    }
}