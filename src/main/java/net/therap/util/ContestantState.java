package net.therap.util;

import org.jboss.seam.annotations.Name;

/**
 * Created by
 * User: tahmid
 * Date: 7/26/12
 * Time: 1:09 PM
 */

@Name("contestantState")
public class ContestantState {

    public static final int TEMPORARY_CONTESTANT = 1;

    public static final int NEW_CONTESTANT = 2;

    public static final int READY_FOR_TEST = 3;

    public static final int AT_TEST = 4;

    public static final int PENDING_TEST_RESULT = 5;

    public static final int SELECTED_CANDIDATE = 6;

    public static final int ELIMINATED_AT_TEST = 7;

    public static final int PENDING_GROUP_FORMATION = 8;

    public static final int ELIGIBLE_FOR_GROUP_REG = 9;

    public static final int FINISHED_GROUP_REG = 10;

    public static final int AT_BUSINESS_PROPOSAL = 11;

    public static final int PENDING_PROPOSAL_RESULT = 12;

    public static final int ELIMINATED_AT_PROPOSAL = 13;

    public static final int AT_FINAL_SUBMISSION = 14;

    public static final int PENDING_SUBMISSION_RESULT = 15;

    public static final int ELIMINATED_AT_SUBMISSION = 16;

    public static final int AT_FINAL_STAGE = 17;

    public static final int DISQUALIFIED_AT_TEST = 18;

    public static final int DISQUALIFIED_AT_FORMATION = 19;

    public static final int DISQUALIFIED_AT_PROPOSAL = 20;

    public static final int DISQUALIFIED_AT_SUBMISSION = 21;

    public static final int BANNED = 22;

    public int getTEMPORARY_CONTESTANT() {
        return TEMPORARY_CONTESTANT;
    }

    public int getNEW_CONTESTANT() {
        return NEW_CONTESTANT;
    }

    public int getREADY_FOR_TEST() {
        return READY_FOR_TEST;
    }

    public int getAT_TEST() {
        return AT_TEST;
    }

    public int getPENDING_TEST_RESULT() {
        return PENDING_TEST_RESULT;
    }

    public int getSELECTED_CANDIDATE() {
        return SELECTED_CANDIDATE;
    }

    public int getELIMINATED_AT_TEST() {
        return ELIMINATED_AT_TEST;
    }

    public int getPENDING_GROUP_FORMATION() {
        return PENDING_GROUP_FORMATION;
    }

    public int getELIGIBLE_FOR_GROUP_REG() {
        return ELIGIBLE_FOR_GROUP_REG;
    }

    public int getFINISHED_GROUP_REG() {
        return FINISHED_GROUP_REG;
    }

    public int getAT_BUSINESS_PROPOSAL() {
        return AT_BUSINESS_PROPOSAL;
    }

    public int getPENDING_PROPOSAL_RESULT() {
        return PENDING_PROPOSAL_RESULT;
    }

    public int getELIMINATED_AT_PROPOSAL() {
        return ELIMINATED_AT_PROPOSAL;
    }

    public int getAT_FINAL_SUBMISSION() {
        return AT_FINAL_SUBMISSION;
    }

    public int getPENDING_SUBMISSION_RESULT() {
        return PENDING_SUBMISSION_RESULT;
    }

    public int getELIMINATED_AT_SUBMISSION() {
        return ELIMINATED_AT_SUBMISSION;
    }

    public int getAT_FINAL_STAGE() {
        return AT_FINAL_STAGE;
    }

    public int getDISQUALIFIED_AT_TEST() {
        return DISQUALIFIED_AT_TEST;
    }

    public int getDISQUALIFIED_AT_FORMATION() {
        return DISQUALIFIED_AT_FORMATION;
    }

    public int getDISQUALIFIED_AT_PROPOSAL() {
        return DISQUALIFIED_AT_PROPOSAL;
    }

    public int getDISQUALIFIED_AT_SUBMISSION() {
        return DISQUALIFIED_AT_SUBMISSION;
    }

    public int getBANNED() {
        return BANNED;
    }
}
