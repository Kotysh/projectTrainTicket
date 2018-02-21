package ru.dmitriykotyshovtest;

import org.junit.Assert;
import org.junit.Test;
import ru.dmitriykotyshov.DAO.DeleteDAO;
import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.other.MyDate;
import ru.dmitriykotyshov.support.ServiceHelper;

import static ru.dmitriykotyshov.DAO.SelectDAO.*;

/**
 * Created by Дмитрий on 28.01.2018.
 */
public class ClassTest {

    @Test
    public void testForTest(){

        int res = 10;

        Assert.assertEquals(res, 10);

    }


    @Test
    public void testIsThatSelect(){

        Assert.assertEquals(SelectDAO.class, ServiceHelper.getInstance("selectDAO").getClass());

    }


    @Test
    public void testIsThatInsert(){

        Assert.assertEquals(InsertDAO.class, ServiceHelper.getInstance("insertDAO").getClass());

    }


    @Test
    public void testIsThatDelete(){

        Assert.assertEquals(DeleteDAO.class, ServiceHelper.getInstance("deleteDAO").getClass());

    }

}
