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
        assertEquals("Best dodgeballers this side of the Miss", testTeam.getTeamDescription());
    }
    @Test
    public void all_returnsAllInstancesOfTeam_4() {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        assertEquals(true, Team.getAll().contains(testTeam));
        assertEquals(true, Team.getAll().contains(secondTeam));
    }
    @Test
    public void teamAddMembers_5() {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        testTeam.setMembers("Dagger");
        testTeam.setMembers("Max");
        secondTeam.setMembers("Blaze");
        assertEquals(2, testTeam.getMembers().size());
        assertEquals("Blaze", secondTeam.getMembers().get(0));
    }
    @Test
    public void clearAllMembers_6() {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        testTeam.setMembers("Dagger");
        testTeam.setMembers("Max");
        secondTeam.setMembers("Blaze");
        assertEquals(2, testTeam.getMembers().size());
        assertEquals("Blaze", secondTeam.getMembers().get(0));
    }
    @Test
    public void clearAllTeams_7() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        testTeam.setMembers("Dagger");
        Team.clearAllTeams();
        assertEquals(0, Team.getAll().size());
    }
    @Test
    public void Team_createsAUniqueId_8() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        assertEquals(2, secondTeam.getId());
    }
    @Test
    public void Team_findbyId_9() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        assertEquals(1, testTeam.findById(testTeam.getId()).getId());
        assertEquals("Average Joe's", Team.findById(secondTeam.getId()).getTeamName());
    }
    @Test
    public void Restaurant_removeSpecificEntryById_10() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        testTeam.deleteTeam(1);
        assertEquals(1, Team.getAll().size());
        assertEquals(Team.getAll().get(0).getId(), 2);
    }
    @Test
    public void Team_updateSpecificTeam_11() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        secondTeam.update("Predators");
        assertEquals("Predators", secondTeam.getTeamName());
    }
    @Test
    public void Team_searchByTeamMate_11() throws Exception {
        Team testTeam = setupTeam();
        Team secondTeam = setupTeam2();
        secondTeam.setMembers("Scott Maxwell");
        Team searchTeam = Team.searchByMember("Scott Maxwell");
        assertEquals("Predators", searchTeam.getTeamName());
    }


    public Team setupTeam(){
        return new Team ("Cobras","Best dodgeballers this side of the Miss");
    }
    public Team setupTeam2(){
        return new Team ("Average Joe's","We're the most OKest");
    }

}