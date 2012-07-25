package net.therap.util;

/**
 * Created by
 * User: tahmid
 * Date: 7/25/12
 * Time: 10:25 AM
 */
public interface ContestantState {

    public final int TEMPORARY_CONTESTANT = 1;

    public final int NEW_CONTESTANT = 2;

    public final int READY_FOR_TEST = 3;

    public final int AT_TEST = 4;

    public final int PENDING_TEST_RESULT = 5;

    public final int SELECTED_CANDIDATE = 6;

    public final int ELIMINATED_AT_TEST = 7;

    public final int PENDING_GROUP_FORMATION = 8;

    public final int ELIGIBLE_FOR_GROUP_REG = 9;

    public final int FINISHED_GROUP_REG = 10;

    public final int AT_BUSINESS_PROPOSAL = 11;

    public final int PENDING_PROPOSAL_RESULT = 12;

    public final int ELIMINATED_AT_PROPOSAL = 13;

    public final int AT_FINAL_SUBMISSION = 14;

    public final int PENDING_SUBMISSION_RESULT = 15;

    public final int ELIMINATED_AT_SUBMISSION = 16;

    public final int AT_FINAL_STAGE = 17;

    public final int DISQUALIFIED_AT_TEST = 18;

    public final int DISQUALIFIED_AT_FORMATION = 19;

    public final int DISQUALIFIED_AT_PROPOSAL = 20;

    public final int DISQUALIFIED_AT_SUBMISSION = 21;

    public final int BANNED = 22;



}
