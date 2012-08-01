package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.domain.ScreeningTest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 1:11 PM
 */

@Name("screeningTestDao")
@Scope(ScopeType.STATELESS)
public class ScreeningTestDaoImpl implements ScreeningTestDao{

    @Logger
    Log log;

    @In
    private Session session;

    public void saveScreeningTest(ScreeningTest screeningTest) {
        session.save(screeningTest);
    }

    public void updateScreeningTest(ScreeningTest screeningTest) {
        session.update(screeningTest);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ScreeningTest getScreeningTestForContestant(Contestant contestant) {

        Query query = session.createQuery("from ScreeningTest screeningTest where screeningTest.screeningTestId = :screeningTestId");
        query.setLong("screeningTestId",contestant.getScreeningTest().getScreeningTestId());
        List<ScreeningTest> screeningTestList = (List<ScreeningTest>) query.list();


        if (screeningTestList.size() != 0) {
            log.info("not null");
            session.merge(contestant.getScreeningTest());
            return screeningTestList.get(0);
        } else {
            log.info("null");
            return null;
        }

    }
}
