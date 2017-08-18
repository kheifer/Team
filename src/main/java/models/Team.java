package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String teamDescription;
    private int id;

    public Team(String teamName, String description){
        this.teamName = teamName;
        this.teamDescription = description;
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
    public void deleteTeam (int id) {
        teams.remove(Team.findById(id));
    }
    public void update(String name){
        this.teamName = name;
    }
    public static Team searchByMember(String memberName){
        Team finder= null;
        for (Team team : teams) {
            for (String member : team.members) {
                if (member.equals(memberName)) {
                    finder = team;
                }
            }
        }
        return finder;
    }
    //Getters
    public String getTeamName() {
        return teamName;
    }
    public String getTeamDescription() {
        return teamDescription;
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
