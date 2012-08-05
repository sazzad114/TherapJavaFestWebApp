package net.therap.dao;

import net.therap.domain.Group;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * Created by
 * User: tahmid
 * Date: 8/2/12
 * Time: 4:19 PM
 */

@Name("groupDao")
@Scope(ScopeType.STATELESS)
public class GroupDaoImpl implements GroupDao{

    @In
    Session session;

    public void saveGroup(Group group) {
        session.save(group);
    }

    public void updateGroup(Group group) {
        session.update(group);
    }
}
