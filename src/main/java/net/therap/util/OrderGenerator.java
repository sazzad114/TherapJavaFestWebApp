package net.therap.util;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by
 * User: tahmid
 * Date: 7/29/12
 * Time: 3:15 PM
 */

@Name("orderGenerator")
@Scope(ScopeType.STATELESS)
public class OrderGenerator implements Serializable {

    private List<Integer> Order;

    public List<Integer> generateOrder(int range, int outputListSize) {
        Order = new ArrayList<Integer>();

        Random random = new Random();

        for (int i = 0; i < outputListSize; i++) {
            Integer questionIndex = random.nextInt(range);
            if (Order.contains(questionIndex)) {
                i--;
            } else {
                Order.add(questionIndex);
            }
        }

        return Order;
    }
}
