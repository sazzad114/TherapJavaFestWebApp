package net.therap.component;

import net.therap.domain.Contestant;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.log.Log;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/22/12
 * Time: 11:46 AM
 */
@Name("emailService")
@Scope(ScopeType.EVENT)
@AutoCreate
public class EmailService {
    @Logger
    private Log log;

    @In(create = true)
    private Renderer renderer;

    private Contestant contestant;

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    @Asynchronous
    public void sendMessage() {
        log.info("inside send message");
        try {
            Contexts.getEventContext().set("recipientContestant", contestant);
            //log.debug(((Contestant)Contexts.getEventContext().get("recipientContestant")).getEmail());
            renderer.render("/email.xhtml");
        } catch (Exception e) {
            log.error("Error Email Send #0" + e.getMessage());
        }
    }
}
