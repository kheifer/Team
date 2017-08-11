package models;

public class Team {
    private String teamName;
    private String teamMember;

    public Team(String teamName, String teamMember){
        this.teamName = teamName;
        this.teamMember = teamMember;
    }
    //Getters
    public String getTeamName() {
        return teamName;
    }
    public String getTeamMember() {
        return teamMember;
    }
}
