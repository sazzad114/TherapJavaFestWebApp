package net.therap.dao;

import net.therap.domain.AnswerInfo;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;

import java.io.Serializable;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 3:34 PM
 */

@Name("answerInfoDao")
@Scope(ScopeType.EVENT)

public class AnswerInfoDaoImpl implements AnswerInfoDao, Serializable {

    @In
    Session session;


    public void saveAnswerInfo(AnswerInfo answerInfo) {
        session.save(answerInfo);
    }
}
