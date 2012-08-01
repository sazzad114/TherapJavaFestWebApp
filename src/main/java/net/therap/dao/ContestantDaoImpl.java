package net.therap.dao;

import net.therap.domain.Contestant;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/29/12
 * Time: 5:58 PM
 */
 @Name("contestantDao")
@Scope(ScopeType.STATELESS)
public class ContestantDaoImpl implements ContestantDao {

    @In
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void saveContestant(Contestant contestant) {
        session.save(contestant);
    }

    public Contestant getContestantByEmail(String email) {
        Query query = session.createQuery("from Contestant contestant where contestant.email= :email");
        query.setString("email",email);
        List<Contestant> contestantList = (List<Contestant>) query.list();


        if (contestantList.size() != 0) {
            return contestantList.get(0);
        } else {
            return null;
        }


    }

    public void updateContestant(Contestant contestant) {
        session.update(contestant);
    }
}
