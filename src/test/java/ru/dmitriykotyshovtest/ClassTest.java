package ru.dmitriykotyshovtest;

import org.junit.Assert;
import org.junit.Test;
import ru.dmitriykotyshov.other.MyDate;

import static ru.dmitriykotyshov.DAO.SelectDAO.*;

/**
 * Created by Дмитрий on 28.01.2018.
 */
public class ClassTest {

    @Test
    public void test1(){

        int res = new MyDate(2018,10,10).getDay();

        Assert.assertEquals(res, 10);

    }

}
