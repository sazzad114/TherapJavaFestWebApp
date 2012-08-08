package net.therap.dao;

import net.therap.domain.AnswerInfo;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 3:34 PM
 */

@Name("answerInfoDao")
@Scope(ScopeType.APPLICATION)
@Startup
public class AnswerInfoDaoImpl implements AnswerInfoDao{

    @In
    Session session;


    public void saveAnswerInfo(AnswerInfo answerInfo) {
        session.save(answerInfo);
    }
}
