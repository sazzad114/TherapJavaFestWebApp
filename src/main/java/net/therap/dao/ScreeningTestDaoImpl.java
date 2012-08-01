package net.therap.dao;

import net.therap.domain.ScreeningTest;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 1:11 PM
 */

@Name("screeningTestDao")
@Scope(ScopeType.STATELESS)
public class ScreeningTestDaoImpl implements ScreeningTestDao{

    @In
    private Session session;

    public void saveScreeningTest(ScreeningTest screeningTest) {
        session.save(screeningTest);
    }

    public void updateScreeningTest(ScreeningTest screeningTest) {
        session.update(screeningTest);
    }
}
