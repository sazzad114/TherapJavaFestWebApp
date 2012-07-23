package net.therap.component;

import net.therap.domain.Contestant;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

/**
 * Created by
 * User: tahmid
 * Date: 7/23/12
 * Time: 10:38 AM
 */

@Name("registrationService")
@Scope(ScopeType.EVENT)
public class RegistrationService {

    private final int TEMPORARY_PASSWORD_LEN = 10;


    @In
    private Session session;

    @In
    private EmailService emailService;



    @Logger
    private Log log;


    public String register(Contestant contestant) {
        log.debug("About to save contestant");
        StringGenerator passwordGenerator = new StringGenerator(TEMPORARY_PASSWORD_LEN);
        String temporaryPassword = passwordGenerator.createString();
        contestant.setPassword(temporaryPassword);
        contestant.setState("temporaryStudent");
        session.save(contestant);
        emailService.setContestant(contestant);
        emailService.sendMessage();
        log.debug("Saved contestant");
        return "success";
    }






}
