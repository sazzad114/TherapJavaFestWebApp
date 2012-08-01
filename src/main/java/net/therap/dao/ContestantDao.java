package net.therap.dao;

import net.therap.domain.Contestant;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/29/12
 * Time: 5:58 PM
 */
public interface ContestantDao {

   public void saveContestant(Contestant contestant);

   public Contestant getContestantByEmail(String email);

   public Contestant getContestantById (long id);

   public void updateContestant(Contestant contestant);

}
