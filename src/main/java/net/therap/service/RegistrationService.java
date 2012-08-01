package net.therap.service;

import net.therap.dao.ContestantDao;
import net.therap.domain.Contestant;
import net.therap.util.ContestantState;
import net.therap.util.StringGeneratorUtil;
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


    @In(create = true)
    private ContestantDao contestantDao;

    @In
    private EmailService emailService;



    @Logger
    private Log log;


    public String register(Contestant contestant) {

        log.debug("About to save contestant");
        String temporaryPassword = StringGeneratorUtil.generateString(TEMPORARY_PASSWORD_LEN);
        contestant.setPassword(temporaryPassword);
        contestant.setState(ContestantState.TEMPORARY_CONTESTANT);
        contestantDao.saveContestant(contestant);
        emailService.sendMessage();
        log.debug("Saved contestant");
        return "success";
    }






}
