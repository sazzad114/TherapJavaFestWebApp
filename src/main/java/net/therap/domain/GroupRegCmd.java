package net.therap.domain;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * Created by
 * User: tahmid
 * Date: 8/2/12
 * Time: 4:57 PM
 */

@Name("groupRegCmd")
@Scope(ScopeType.PAGE)
public class GroupRegCmd {

    private String partnerEmail;
    private String groupName;

    public String getPartnerEmail() {
        return partnerEmail;
    }

    public void setPartnerEmail(String partnerEmail) {
        this.partnerEmail = partnerEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
