package ru.dmitriykotyshov.other;

import static ru.dmitriykotyshov.other.PrintWagon.KUPE;
import static ru.dmitriykotyshov.other.PrintWagon.PLAC;
import static ru.dmitriykotyshov.other.PrintWagon.SIT;

/**
 * Created by Дмитрий on 05.02.2018.
 */
public class Price {

    public static double COEFFICIENT_PRICE = 2.3;
    public static double BIO_TIOLET = 1.02;
    public static double AIR_CONDITION = 1.03;
    public static double EXPRESS = 1.25;

    public static int getPrice(int price, boolean aircondition, boolean tiolet, String type){

        return (int) (price*(aircondition?AIR_CONDITION:1)*(tiolet?BIO_TIOLET:1)*getCoefficientByTypeWagon(type));

    }

    private static double getCoefficientByTypeWagon(String type){

        if (type.equals(SIT)){
            return 1;
        }else if(type.equals(PLAC)){
            return 1.9;
        }else if(type.equals(KUPE)){
            return 3;
        }

        return 0;

    }

}
