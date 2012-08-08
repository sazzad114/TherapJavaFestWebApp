package net.therap.service;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
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
public class EmailService {
    @Logger
    private Log log;

    @In(create = true)
    private Renderer renderer;

    public void sendMessage() {
        log.info("inside send message");
        try {
            renderer.render("/email.xhtml");
        } catch (Exception e) {
            log.error("Error Email Send #0" + e.getMessage());
        }
    }
}
