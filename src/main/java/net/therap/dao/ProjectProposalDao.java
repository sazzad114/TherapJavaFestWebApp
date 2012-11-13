package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.domain.ProjectProposal;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/6/12 1:24 PM
 */
public interface ProjectProposalDao {

    public void saveProjectProposal(ProjectProposal projectProposal);

    public void updateProjectProposal(ProjectProposal projectProposal);

    public ProjectProposal getProjectProposalByContestant(Contestant contestant);

}
