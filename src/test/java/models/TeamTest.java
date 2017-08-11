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
        assertEquals("Cobra", testTeam.getTeamName());
    }



    public Team setupTeam(){
        return new Team ("Cobras","Lazer");
    }
}