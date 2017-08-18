package dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;
    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO members(name, homeTown, occupation, age, memberId) VALUES (:name, :homeTown, :occupation, :age, :memberId)";
        try(Connection con = sql2o.open()){ //opens a connection(con)
            int id = (int) con.createQuery(sql) //makes a new (int)variable and queries the sql database
                    .bind(member) //maps the argument onto the query to pull information from it
                    .executeUpdate() //executes the argument then updates the database
                    .getKey(); //gets the row key and sets it as the int id and finishes the query
            member.setId(id); //sets the team id as the int id from the query
        } catch (Sql2oException ex) {//uses a catch to throw an error if something goes awry
            System.out.println(ex); //if there is an error it outputs the error
        }

    }

    @Override
    public Member findById(int id) {
        String find = "SELECT * FROM members WHERE id = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(find)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public List<Member> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members")
                    .executeAndFetch(Member.class);
        }    }

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
