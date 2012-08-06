package net.therap.dao;

import net.therap.domain.ProjectProposal;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/6/12 1:25 PM
 */
@Name("projectProposalDao")
@Scope(ScopeType.STATELESS)
public class ProjectProposalDaoImpl implements ProjectProposalDao {

    @In
    Session session;

    public void saveProjectProposal(ProjectProposal projectProposal) {
        session.save(projectProposal);
    }

    public void updateProjectProposal(ProjectProposal projectProposal) {
        session.update(projectProposal);
    }
}
