package net.therap.dao;

import net.therap.domain.Group;
import org.hibernate.Session;
import org.jboss.seam.annotations.In;



/**
 * Created by
 * User: tahmid
 * Date: 8/2/12
 * Time: 4:13 PM
 */
public interface GroupDao {

    public void saveGroup(Group group);

    public void updateGroup(Group group);
}
