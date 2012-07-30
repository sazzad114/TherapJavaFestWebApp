package net.therap.util;

import net.therap.domain.University;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/22/12
 * Time: 3:46 PM
 */
@Name("factory")
public class FactoryUtil {

    @Factory("universities")
    public List<University> getUniversities() {
        return Arrays.asList(University.values());
    }

    @Factory("Questiontimemer")
    public long getTimes(){
        return 10;
    }


}
