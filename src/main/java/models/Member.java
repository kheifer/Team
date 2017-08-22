package models;

public class Member {
    private String name;
    private String homeTown;
    private String occupation;
    private int age;
    private int id;
    private int teamId;

    public Member(String name, String homeTown, String occupation, int age, int teamId){
        this.name = name;
        this.homeTown = homeTown;
        this.occupation = occupation;
        this.age = age;
        this.teamId = teamId;
    }
    //Getters
    public String getName() {
        return name;
    }
    public String getHomeTown() {
        return homeTown;
    }
    public String getOccupation() {
        return occupation;
    }
    public int getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
    public int getTeamId() {
        return teamId;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    //equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (age != member.age) return false;
        if (teamId != member.teamId) return false;
        if (!name.equals(member.name)) return false;
        if (!homeTown.equals(member.homeTown)) return false;
        return occupation.equals(member.occupation);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + homeTown.hashCode();
        result = 31 * result + occupation.hashCode();
        result = 31 * result + age;
        result = 31 * result + teamId;
        return result;
    }
}
