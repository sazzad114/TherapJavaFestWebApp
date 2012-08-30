package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.domain.Group;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

import java.io.Serializable;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 8/2/12
 * Time: 4:19 PM
 */

@Name("groupDao")
@Scope(ScopeType.APPLICATION)
@Startup
public class GroupDaoImpl implements GroupDao, Serializable {

    @In
    Session session;

    public void saveGroup(Group group) {
        session.save(group);
    }

    public void updateGroup(Group group) {
        session.update(group);
    }

    public Group getGroupByName(String groupName) {

        Query query = session.createQuery("from Group myGroup where lower(myGroup.groupName) = lower(:groupName)");
        query.setString("groupName", groupName);
        List<Group> groups = (List<Group>) query.list();


        if (groups.size() != 0) {
            return groups.get(0);
        } else {
            return null;
        }

    }
}
