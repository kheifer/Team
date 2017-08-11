package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void NewTestObjectGetsCorrectlyCreated_true_2() throws Exception {
        Team testTeam = setupTeam();
        assertEquals(true, testTeam instanceof Team);
    }
    @Test
    public void newTeaminstantiateswithContent_2() throws Exception {
        Team testTeam = setupTeam();
        assertEquals("Cobras", testTeam.getTeamName());
    }
    @Test
    public void all_returnsAllInstancesOfTeam_true() {
        Team testTeam = setupTeam();
        Team secondTeam = new Team("TeamTwo", "Dagger");
        assertEquals(true, Team.getAll().contains(testTeam));
        assertEquals(true, Team.getAll().contains(secondTeam));
    }


    public Team setupTeam(){
        return new Team ("Cobras","Lazer");
    }
}