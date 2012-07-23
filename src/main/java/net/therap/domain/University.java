package net.therap.domain;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/22/12
 * Time: 3:32 PM
 */
public enum University {

    BUET("Bangladesh University of Engineering and Technology"),
    DU("University of Dhaka"),
    SUST("sust");


    private final String universityName;

    University(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    @Override
    public String toString() {
        return getUniversityName();
    }
}
