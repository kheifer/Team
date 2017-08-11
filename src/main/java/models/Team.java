package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String teamMember;
    private static ArrayList<Team> team = new ArrayList<Team>();

    public Team(String teamName, String teamMember){
        this.teamName = teamName;
        this.teamMember = teamMember;
        team.add(this);
    }
    //Getters
    public String getTeamName() {
        return teamName;
    }
    public String getTeamMember() {
        return teamMember;
    }
    public static ArrayList<Team> getAll() {
        return team;
    }

}
