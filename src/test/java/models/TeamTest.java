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
        Team.clearAllTeams();
    }
    @Test
    public void NewTestObjectGetsCorrectlyCreated_true_1() throws Exception {
        Team testTeam = setupTeam();
        assertEquals(true, testTeam instanceof Team);
    }
    @Test
    public void newTeaminstantiateswithContent_2() throws Exception {
        Team testTeam = setupTeam();
        assertEquals("Cobras", testTeam.getTeamName());
    }
    @Test
    public void newTeaminstantiateswithDescription_3() throws Exception {
        Team testTeam = setupTeam();
        assertEquals("Best dodgeballers this side of the Miss", testTeam.getDescription());
    }
    @Test
    public void all_returnsAllInstancesOfTeam_4() {
        Team testTeam = setupTeam();
        Team secondTeam = new Team("Average Joe's", "We're the most OKest");
        assertEquals(true, Team.getAll().contains(testTeam));
        assertEquals(true, Team.getAll().contains(secondTeam));
    }
    @Test
    public void teamSetMembers_5() {
        Team testTeam = setupTeam();
        Team secondTeam = new Team("Average Joe's", "We're the most OKest");
        testTeam.setMembers("Dagger");
        testTeam.setMembers("Max");
        secondTeam.setMembers("Blaze");
        assertEquals(2, testTeam.getMembers().size());
        assertEquals("Blaze", secondTeam.getMembers().get(0));
    }
    @Test
    public void clearAllMembers_6() {
        Team testTeam = setupTeam();
        Team secondTeam = new Team("Average Joe's", "We're the most OKest");
        testTeam.setMembers("Dagger");
        testTeam.setMembers("Max");
        secondTeam.setMembers("Blaze");
        assertEquals(2, testTeam.getMembers().size());
        assertEquals("Blaze", secondTeam.getMembers().get(0));
    }
    @Test
    public void clearAllTeams_7() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = new Team("Average Joe's", "We're the most OKest");
        testTeam.setMembers("Dagger");
        Team.clearAllTeams();
        assertEquals(0, Team.getAll().size());
    }
    @Test
    public void Team_createsAUniqueId_8() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = new Team("Average Joe's", "We're the most OKest");
        assertEquals(2, secondTeam.getId());
    }
    @Test
    public void Team_findbyId_9() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = new Team("Average Joe's", "We're the most OKest");
        assertEquals(1, testTeam.findById(testTeam.getId()).getId());
        assertEquals("Tin Shed", Team.findById(secondTeam.getId()).getTeamName());
    }


    public Team setupTeam(){
        return new Team ("Cobras","Best dodgeballers this side of the Miss");
    }
}