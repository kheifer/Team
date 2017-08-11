package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String description;
    private static ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<String> members;
    private int id;
    private static int incrementTeam;

    public Team(String teamName, String description){
        this.teamName = teamName;
        this.description = description;
        members = new ArrayList<String>();
        teams.add(this);
        incrementTeam++;
        this.id = incrementTeam;
    }

    public static void clearAllTeams() {
        incrementTeam = 0;
        teams.clear();
    }

    //Getters
    public String getTeamName() {
        return teamName;
    }
    public String getDescription() {
        return description;
    }
    public static ArrayList<Team> getAll() {
        return teams;
    }
    public ArrayList<String> getMembers() {
        return members;
    }

    public int getId() {
        return id;
    }

    //Setters
    public void setMembers(String members) {
        this.members.add(members);
    }
}
