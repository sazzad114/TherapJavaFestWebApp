package net.therap.service;

import net.therap.dao.ContestantDao;
import net.therap.dao.GroupDao;
import net.therap.domain.Contestant;
import net.therap.domain.Group;
import net.therap.domain.GroupRegCmd;
import net.therap.util.ContestantState;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 8/2/12
 * Time: 4:29 PM
 */

@Name("groupRegistrationService")
@Scope(ScopeType.EVENT)
public class GroupRegistrationService {

    @In
    private GroupDao groupDao;

    @In
    private ContestantDao contestantDao;

    @In
    private Contestant loggedInContestant;

    @In
    private FacesMessages facesMessages;


    public String registerGroup(GroupRegCmd groupRegCmd) {
        if (loggedInContestant.getState() == ContestantState.SELECTED_CANDIDATE) {
            Contestant partner = contestantDao.getSelectedContestantByEmail(groupRegCmd.getPartnerEmail());
            Group existingGroup = groupDao.getGroupByName(groupRegCmd.getGroupName());

            if (partner != null && existingGroup == null) {
                Group group = new Group();
                group.setGroupName(groupRegCmd.getGroupName());

                List<Contestant> groupMembers = new ArrayList<Contestant>();

                groupMembers.add(loggedInContestant);
                groupMembers.add(partner);

                loggedInContestant.setMyGroup(group);
                partner.setMyGroup(group);

                loggedInContestant.setState(ContestantState.FINISHED_GROUP_REG);
                partner.setState(ContestantState.FINISHED_GROUP_REG);

                groupDao.saveGroup(group);
                contestantDao.updateContestant(loggedInContestant);

                return "success";

            } else if (partner == null) {
                facesMessages.addToControl("partnerEmail", "Email does not exist or has already been registered");
                return "failure";
            } else if (existingGroup != null) {
                facesMessages.addToControl("groupName", "A Group having this name has already been registered");
                return "failure";
            }
        }
        return "failure";

    }
}
