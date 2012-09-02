package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.domain.ContestantPerUniversityCount;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/29/12
 * Time: 5:58 PM
 */
public interface ContestantDao {

    public void saveContestant(Contestant contestant);

    public Contestant getContestantByEmail(String email);

    public Contestant getContestantById(long id);

    public void updateContestant(Contestant contestant);

    public Contestant getSelectedContestantByEmail(String email);

    public byte[] getContestantImageById(long id);

    public List<ContestantPerUniversityCount> getListOfContestantsGroupedByUniversity();

}
