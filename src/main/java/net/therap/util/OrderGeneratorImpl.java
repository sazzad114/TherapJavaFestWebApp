package net.therap.util;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

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
public class OrderGeneratorImpl implements OrderGenerator{

    int inputSetSize;
    int outputSetSize;
    List<Integer> Order;

    public int getInputSetSize() {
        return inputSetSize;
    }

    public void setInputSetSize(int inputSetSize) {
        this.inputSetSize = inputSetSize;
    }

    public int getOutputSetSize() {
        return outputSetSize;
    }

    public void setOutputSetSize(int outputSetSize) {
        this.outputSetSize = outputSetSize;
    }

    public List<Integer> generateOrder() {
        Order = new ArrayList<Integer>();

        Random random = new Random();

        for (int i = 0; i < outputSetSize; i++) {
            Integer questionIndex = random.nextInt(inputSetSize);
            if (Order.contains(questionIndex)) {
                i--;
            } else {
                Order.add(questionIndex);
            }
        }

        for (int i = 0; i < outputSetSize; i++) {
            //System.out.println(Order.get(i));
        }

        return Order;
    }
}
