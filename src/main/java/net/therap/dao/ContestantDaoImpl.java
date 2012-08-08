package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.util.ContestantState;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/29/12
 * Time: 5:58 PM
 */
@Name("contestantDao")
@Scope(ScopeType.APPLICATION)
@Startup
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
        query.setString("email", email);
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

    public Contestant getContestantById(long id) {
        return (Contestant) session.get(Contestant.class, id);
    }

    public Contestant getSelectedContestantByEmail(String email) {

        Query query = session.createQuery("from Contestant contestant where contestant.email= :email and contestant.state = :state");
        query.setString("email", email);
        query.setInteger("state", ContestantState.SELECTED_CANDIDATE);
        List<Contestant> contestantList = (List<Contestant>) query.list();


        if (contestantList.size() != 0) {
            return contestantList.get(0);
        } else {
            return null;
        }

    }
}
