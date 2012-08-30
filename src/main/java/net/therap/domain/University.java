package net.therap.domain;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/22/12
 * Time: 3:32 PM
 */
public enum University {

    AUST("Ahsanullah University of Science and Technology"),
    AIUB("American International University-Bangladesh"),
    BRAC("BRAC University"),
    BUET("Bangladesh University of Engineering and Technology"),
    CUET("Chittagong University of Engineering & Technology"),
    DIIT("Daffodil International University"),
    EWU("East West University"),
    GREEN("Green University of Bangladesh"),
    IUB("Independent University, Bangladesh"),
    IIUC("International Islamic University, Chittagong"),
    IUT("Islamic University of Technology"),
    JU("Jahangirnagar University"),
    KU("Khulna University"),
    KUET("Khulna University of Engineering & Technology"),
    MIST("Military Institute of Science and Technology"),
    NSU("North South University"),
    NORTHERN("Northern University, Bangladesh"),
    RU("Rajshahi University"),
    RUET("Rajshahi University of Engineering & Technology"),
    SOUTHEAST("Southeast University"),
    STAMFORD("Stamford University Bangladesh"),
    SUB("State University of Bangladesh"),
    SUST("Shahjalal University of Science and Technology"),
    UIU("United International University"),
    UAP("University of Asia Pacific"),
    CU("University of Chittagong"),
    DU("University of Dhaka"),
    ULAB("University of Liberal Arts Bangladesh"),
    USA("University of South Asia, Bangladesh"),
    OTHER("Other");


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
