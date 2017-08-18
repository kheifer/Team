package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        memberDao = new Sql2oMemberDao(sql2o);

        con = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void memberAddsNewInstanceWithId() throws Exception {
        Member newMember = newMemberInitiator();
        memberDao.add(newMember);
        assertEquals(1, newMember.getId());
    }

    @Test
    public void memberFindMemberById() throws Exception {
        Member newMember = newMemberInitiator();
        Member newMember1 = newMemberInitiator();
        memberDao.add(newMember);
        memberDao.add(newMember1);
        int finder = newMember.getId();
        assertEquals(newMember.getAge(), memberDao.findById(finder).getAge());
    }

    @Test
    public void getAllMembersForAllTeams() throws Exception {
        Member member = newMemberInitiator();
        Member member1 = newMemberInitiator();
        Member member2 = newMemberInitiator2();
        memberDao.add(member);
        memberDao.add(member1);
        memberDao.add(member2);
        assertEquals(3, memberDao.getAll().size());
    }

//    @Test
//    public void update() throws Exception {
//    }
//
//    @Test
//    public void deleteByID() throws Exception {
//    }
//
//    @Test
//    public void deleteAllMembers() throws Exception {
//    }

    public Member newMemberInitiator(){
        return new Member("Max Maddock", "Portland, OR", "Software Developer", 29,1);
    }
    public Member newMemberInitiator2(){
        return new Member("Maxamillion Ulanoff", "New York, NY", "Project Manager", 32,1);
    }
}