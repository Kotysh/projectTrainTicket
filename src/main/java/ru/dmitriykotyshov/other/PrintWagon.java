package ru.dmitriykotyshov.other;

import ru.dmitriykotyshov.trainticketobjects.Wagon;

import java.util.Set;

/**
 * Created by Дмитрий on 08.02.2018.
 */
public class PrintWagon {

    private PrintWagon() {
    }

    public static StringBuilder printWagonSit(Wagon wagon, Set<Integer> setPlace, boolean tiolet){

        if (tiolet == false){
            return getWagon(wagon, setPlace);
        }else{
            return getWagonTiolet(wagon, setPlace);
        }

    }

    private static StringBuilder getWagon(Wagon wagon, Set<Integer> setPlace){

        StringBuilder placeRadioButtons = new StringBuilder();
        placeRadioButtons.append("<div id=\"wagonPlaces\"><table align=\"center\">");
        placeRadioButtons.append("<tr>");
        for (int i=0; i<wagon.getCountPlace(); i++){

            if (i%10 == 0)placeRadioButtons.append("<tr>");

            if (setPlace.contains(i+1)) {
                placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + "</td>");
            }else{
                placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + "</td>");
            }

            if (i == 29){
                placeRadioButtons.append("<tr><td colspan=\"10\">&nbsp;</td></tr>");
            }

            if (i%10 == 9)placeRadioButtons.append("</tr>");

        }
        placeRadioButtons.append("</table></div>");


        return placeRadioButtons;

    }

    private static StringBuilder getWagonTiolet(Wagon wagon, Set<Integer> setPlace){

        StringBuilder placeRadioButtons = new StringBuilder();
        placeRadioButtons.append("<div id=\"wagonPlaces\"><table align=\"center\">");
        placeRadioButtons.append("<tr>");
        for (int i=0; i<wagon.getCountPlace(); i++){

            if (i<30){
                if (i%10 == 0)placeRadioButtons.append("<tr>");

                if (setPlace.contains(i+1)) {
                    placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + "</td>");
                }else{
                    placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + "</td>");
                }

                if (i%10 == 9)placeRadioButtons.append("</tr>");
            }else{
                if ((i-30)%8 == 0)placeRadioButtons.append("<tr>");

                if (setPlace.contains(i+1)) {
                    placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + "</td>");
                }else{
                    placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + "</td>");
                }

                if ((i-30)%8 == 7)placeRadioButtons.append("</tr>");
            }

            if (i == 29){
                placeRadioButtons.append("<tr><td colspan=\"10\">&nbsp;</td></tr>");
            }


        }
        placeRadioButtons.append("</table></div>");


        return placeRadioButtons;

    }

}
