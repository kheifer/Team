package dao;

import models.Member;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;
    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {

    }

    @Override
    public Member findById(int id) {
        return null;
    }

    @Override
    public List<Member> getAll() {
        return null;
    }

    @Override
    public void update(String name, String homeTown, String occupation, int age, int id, int memberId) {

    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public void deleteAllMembers() {

    }
}
