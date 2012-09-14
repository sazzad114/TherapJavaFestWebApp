package net.therap.domain;

/**
 * Created by
 *
 * @author: tahmid
 * @since: 9/2/12 5:37 PM
 */
public class ContestantPerUniversityCount implements Comparable{

    String university;
    Long contestantCount;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Long getContestantCount() {
        return contestantCount;
    }

    public void setContestantCount(Long contestantCount) {
        this.contestantCount = contestantCount;
    }

    public int compareTo(Object o) {
        ContestantPerUniversityCount contestantPerUniversityCount = (ContestantPerUniversityCount) o;
        if (contestantCount < contestantPerUniversityCount.contestantCount) {
            return 1;
        }
        else if (contestantCount == contestantPerUniversityCount.contestantCount) {
            return 0;
        }
        else
            return -1;
    }
}
