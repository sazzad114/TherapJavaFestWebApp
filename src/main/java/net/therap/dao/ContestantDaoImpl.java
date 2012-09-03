package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.domain.ContestantPerUniversityCount;
import net.therap.util.ContestantState;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/29/12
 * Time: 5:58 PM
 */
@Name("contestantDao")
@Scope(ScopeType.EVENT)

public class ContestantDaoImpl implements ContestantDao, Serializable {

    @In
    private Session session;

    @Logger
    private Log log;

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

        Query query = session.createQuery("from Contestant contestant where contestant.email = :email and contestant.state = :state");
        query.setString("email", email);
        query.setInteger("state", ContestantState.SELECTED_CANDIDATE);
        List<Contestant> contestantList = (List<Contestant>) query.list();


        if (contestantList.size() != 0) {
            return contestantList.get(0);
        } else {
            return null;
        }

    }

    public byte[] getContestantImageById(long id) {
        Query query = session.createQuery("select contestant.photo from Contestant contestant where contestant.id = :id");
        query.setLong("id", id);
        return (byte[]) query.uniqueResult();
    }

    public List<ContestantPerUniversityCount> getListOfContestantsGroupedByUniversity() {
        Query query = session.createQuery("select contestant.university,count(contestant.email) from Contestant contestant group by contestant.university");

        List objectList = query.list();

        List<ContestantPerUniversityCount> contestantPerUniversityCountList = new ArrayList<ContestantPerUniversityCount>();
        long total = 0;

        for (Object o : objectList) {

            Object[] objArray = (Object[]) o;
            ContestantPerUniversityCount contestantPerUniversityCount = new ContestantPerUniversityCount();
            contestantPerUniversityCount.setUniversity((String) objArray[0]);
            contestantPerUniversityCount.setContestantCount((Long) objArray[1]);
            total += (Long)objArray[1];
            contestantPerUniversityCountList.add(contestantPerUniversityCount);
        }

        ContestantPerUniversityCount contestantPerUniversityCount = new ContestantPerUniversityCount();
        contestantPerUniversityCount.setUniversity("Total");
        contestantPerUniversityCount.setContestantCount(total);
        contestantPerUniversityCountList.add(contestantPerUniversityCount);

        Collections.sort(contestantPerUniversityCountList);
        return contestantPerUniversityCountList;
    }
}
