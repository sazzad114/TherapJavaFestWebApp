package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.domain.ProjectProposal;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/6/12 1:25 PM
 */
@Name("projectProposalDao")
@Scope(ScopeType.EVENT)

public class ProjectProposalDaoImpl implements ProjectProposalDao, Serializable {

    @In
    Session session;

    public void saveProjectProposal(ProjectProposal projectProposal) {
        session.save(projectProposal);
    }

    public void updateProjectProposal(ProjectProposal projectProposal) {
        session.update(projectProposal);
    }

    public ProjectProposal getProjectProposalByContestant(Contestant contestant) {
        Query query = session.createQuery("from ProjectProposal projectProposal where projectProposal.proposingGroup.groupId = :groupId");
        query.setLong("groupId",contestant.getMyGroup().getGroupId());
         List<ProjectProposal> projectProposalList = (List<ProjectProposal>)query.list();

        if(projectProposalList.size() != 0){
            return projectProposalList.get(0);
        }

        return null;
    }
}
