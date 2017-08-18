package dao;

import models.Member;

import java.util.List;

public interface MemberDao {

    void add(Member member);

    Member findById(int id);

    List<Member> getAll();

    void update(String name, String homeTown, String occupation, int age, int id, int memberId);

    void deleteByID(int id);

    void deleteAllMembers();


}
