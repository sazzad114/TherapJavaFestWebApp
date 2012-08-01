package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.domain.ScreeningTest;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 1:09 PM
 */
public interface ScreeningTestDao {

    public void saveScreeningTest(ScreeningTest screeningTest);

    public void updateScreeningTest(ScreeningTest screeningTest);

    public ScreeningTest getScreeningTestForContestant(Contestant contestant);
}
