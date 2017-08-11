package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String description;
    private static ArrayList<Team> team = new ArrayList<Team>();

    public Team(String teamName, String description){
        this.teamName = teamName;
        this.description = description;
        team.add(this);
    }
    //Getters
    public String getTeamName() {
        return teamName;
    }
    public String getDescription() {
        return description;
    }
    public static ArrayList<Team> getAll() {
        return team;
    }

}
