package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String description;
    private static ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<String> members;
    private int id;
    private static int incrementTeam = 0;

    public Team(String teamName, String description){
        this.teamName = teamName;
        this.description = description;
        members = new ArrayList<String>();
        incrementTeam++;
        this.id = incrementTeam;
        teams.add(this);
    }

    public static void clearAllTeams() {
        incrementTeam = 0;
        teams.clear();
    }
    public static Team findById(int id){
        Team find= null;
        for (Team team : teams){
            if(team.getId() == id){
                find = team;
            }
        }
        return find;
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
