package dao;

import models.Member;

import java.util.List;

public interface MemberDao {

    void add(Member member);//adds a new member

    Member findById(int id);//searches for member by int Id

    List<Member> getAll(); //pulls a list of all members

    List<Member> getAllMembersByMemberName(String input); //creates a kust if all members by a member name


    void update(String name, String homeTown, String occupation, int age, int id, int teamId); //updates member info using teamId

    void deleteById(int id);

    void deleteAllMembers();


}
