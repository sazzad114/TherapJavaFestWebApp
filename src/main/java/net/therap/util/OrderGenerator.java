package net.therap.util;

import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/29/12
 * Time: 4:31 PM
 */
public interface OrderGenerator {

    public void setInputSetSize(int inputSetSize);

    public void setOutputSetSize(int outputSetSize);

    public List<Integer> generateOrder();

}
