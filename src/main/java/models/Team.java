package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String teamDescription;
    private int id;

    public Team(String teamName, String description){
        this.teamName = teamName;
        this.teamDescription = description;
    }

    //Getters
    public String getTeamName() {
        return teamName;
    }
    public String getTeamDescription() {
        return teamDescription;
    }
    public int getId() {
        return id;
    }

    //Setters
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }
    public void setId(int id) {
        this.id = id;
    }

    //equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (!teamName.equals(team.teamName)) return false;
        return teamDescription.equals(team.teamDescription);
    }

    @Override
    public int hashCode() {
        int result = teamName.hashCode();
        result = 31 * result + teamDescription.hashCode();
        return result;
    }
}
