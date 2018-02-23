package ru.dmitriykotyshov.other;

import ru.dmitriykotyshov.trainticketobjects.Wagon;

import java.util.Set;

/**
 * Created by Дмитрий on 08.02.2018.
 */
public class PrintWagon {

    public static final String SIT = "сидячий";
    public static final String PLAC = "плацкарт";
    public static final String KUPE = "купе";

    private static final String WAY_SIT_TIOLET = "../img/sit_tiolet.png";
    private static final String WAY_SIT = "../img/sit.png";
    private static final String WAY_PLAC_TIOLET = "../img/plac_tiolet.png";
    private static final String WAY_PLAC = "../img/plac.png";
    private static final String WAY_KUPE_TIOLET = "../img/kupe_tiolet.png";
    private static final String WAY_KUPE = "../img/kupe.png";


    private PrintWagon() {
    }


    public static String getWayToPNGWagon(String type, boolean tiolet){

        if (type.equals(SIT)){
            if (tiolet) return WAY_SIT_TIOLET;
            return WAY_SIT;
        }else if(type.equals(PLAC)){
            if (tiolet) return WAY_PLAC_TIOLET;
            return WAY_PLAC;
        }else if(type.equals(KUPE)){
            if (tiolet) return WAY_KUPE_TIOLET;
            return WAY_KUPE;
        }

        return null;

    }

    public static StringBuilder printWagon(Wagon wagon, Set<Integer> setPlace, boolean tiolet){

        if (wagon.getTypeWagon().equals(SIT)){
            if (tiolet) return getWagonTiolet(wagon, setPlace);
            return getWagon(wagon, setPlace);
        }else if(wagon.getTypeWagon().equals(PLAC)){
            if (tiolet) return getWagonPlacTiolet(wagon, setPlace);
            return getWagonPlac(wagon, setPlace);
        } else if (wagon.getTypeWagon().equals(KUPE)) {
            if (tiolet) return  getWagonKupeTiolet(wagon, setPlace);
            return getWagonKupe(wagon, setPlace);
        }

        return null;
    }

    private static StringBuilder getWagon(Wagon wagon, Set<Integer> setPlace){

        StringBuilder placeRadioButtons = new StringBuilder();
        placeRadioButtons.append("<div id=\"wagonPlaces\"><table align=\"center\">");
        for (int i=0; i<wagon.getCountPlace(); i++){

            if (i%10 == 0)placeRadioButtons.append("<tr>");

            if (setPlace.contains(i+1)) {
                placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + "</td>\n");
            }else{
                placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + "</td>\n");
            }

            if (i == 29){
                placeRadioButtons.append("<tr><td colspan=\"10\">&nbsp;</td></tr>\n");
            }

            if (i%10 == 9)placeRadioButtons.append("</tr>\n");

        }
        placeRadioButtons.append("</table></div>\n");


        return placeRadioButtons;

    }

    private static StringBuilder getWagonTiolet(Wagon wagon, Set<Integer> setPlace){

        StringBuilder placeRadioButtons = new StringBuilder();
        placeRadioButtons.append("<div id=\"wagonPlaces\"><table align=\"center\">");
        for (int i=0; i<wagon.getCountPlace(); i++){

            if (i<30){
                if (i%10 == 0)placeRadioButtons.append("<tr>");

                if (setPlace.contains(i+1)) {
                    placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + "</td>\n");
                }else{
                    placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + "</td>\n");
                }

                if (i%10 == 9)placeRadioButtons.append("</tr>\n");
            }else{
                if ((i-30)%8 == 0)placeRadioButtons.append("<tr>");

                if (setPlace.contains(i+1)) {
                    placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + "</td>\n");
                }else{
                    placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + "</td>\n");
                }

                if ((i-30)%8 == 7)placeRadioButtons.append("</tr>\n");
            }

            if (i == 29){
                placeRadioButtons.append("<tr><td colspan=\"10\">&nbsp;</td></tr>\n");
            }


        }
        placeRadioButtons.append("</table></div>\n");


        return placeRadioButtons;

    }

    private static final StringBuilder getWagonPlacTiolet(Wagon wagon, Set<Integer> setPlace){

        StringBuilder placeRadioButtons = new StringBuilder();
        placeRadioButtons.append("<div id=\"wagonPlaces\"><table align=\"center\">");
        String polka;
        for (int i=0; i<wagon.getCountPlace(); i++){

            if (i<20){
                if (i-10<0) polka = "<br>вп";
                else polka = "<br>нп";

                if (i%10 == 0)placeRadioButtons.append("<tr class=\"plackartV\">");

                if (setPlace.contains(i+1)) {
                    placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + polka +"</td>\n");
                }else{
                    placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + polka + "</td>\n");
                }

                if (i%10 == 9)placeRadioButtons.append("</tr>\n");
            }else{

                if (i%2 == 1) polka = "<br>вп";
                else polka = "<br>нп";

                if ((i-20)%8 == 0)placeRadioButtons.append("<tr class=\"plackartN\">");

                if (setPlace.contains(i+1)) {
                    placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + polka + "</td>\n");
                }else{
                    placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + polka + "</td>\n");
                }

                if ((i-20)%8 == 7)placeRadioButtons.append("</tr>\n");
            }

            if (i == 19){
                placeRadioButtons.append("<tr><td colspan=\"10\">&nbsp;</td></tr>\n<tr><td colspan=\"10\">&nbsp;</td></tr>\n");
            }


        }
        placeRadioButtons.append("</table></div>\n<p>вп - верхняя полка<br>нп - нижняя полка</p>");


        return placeRadioButtons;
    }

    private static final StringBuilder getWagonPlac(Wagon wagon, Set<Integer> setPlace){

        StringBuilder placeRadioButtons = new StringBuilder();
        placeRadioButtons.append("<div id=\"wagonPlaces\"><table align=\"center\">");
        String polka;
        for (int i=0; i<wagon.getCountPlace(); i++){

            if (i<20){
                if (i-10<0) polka = "<br>вп";
                else polka = "<br>нп";

                if (i%10 == 0)placeRadioButtons.append("<tr class=\"plackartV\">");

                if (setPlace.contains(i+1)) {
                    placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + polka + "</td>\n");
                }else{
                    placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + polka + "</td>\n");
                }

                if (i%10 == 9)placeRadioButtons.append("</tr>\n");
            }else{
                if (i%2 == 1) polka = "<br>вп";
                else polka = "<br>нп";
                if ((i-20)%10 == 0)placeRadioButtons.append("<tr class=\"plackartN\">");

                if (setPlace.contains(i+1)) {
                    placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + polka + "</td>\n");
                }else{
                    placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + polka + "</td>\n");
                }

            }

            if (i == 19){
                placeRadioButtons.append("<tr><td colspan=\"10\">&nbsp;</td></tr>\n<tr><td colspan=\"10\">&nbsp;</td></tr>\n");
            }


        }
        placeRadioButtons.append("</table></div>\n<p>вп - верхняя полка<br>нп - нижняя полка</p>");


        return placeRadioButtons;
    }

    private static final StringBuilder getWagonKupeTiolet(Wagon wagon, Set<Integer> setPlace){

        StringBuilder placeRadioButtons = new StringBuilder();
        placeRadioButtons.append("<div id=\"wagonPlaces\"><table align=\"center\">");
        String polka;
        for (int i=0; i<wagon.getCountPlace(); i++){

                if (i-9<0) polka = "<br>вп";
                else polka = "<br>нп";

                if (i%9 == 0)placeRadioButtons.append("<tr class=\"kupe\">");

                if (setPlace.contains(i + 1)) {
                        placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + polka + "</td>\n");
                    } else {
                        placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + polka + "</td>\n");
                    }

                if (i%10 == 8)placeRadioButtons.append("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>\n");


        }
        placeRadioButtons.append("</table></div>\n<p>вп - верхняя полка<br>нп - нижняя полка</p>");


        return placeRadioButtons;
    }



    private static final StringBuilder getWagonKupe(Wagon wagon, Set<Integer> setPlace){

        StringBuilder placeRadioButtons = new StringBuilder();
        placeRadioButtons.append("<div id=\"wagonPlaces\"><table align=\"center\">");
        String polka;
        for (int i=0; i<wagon.getCountPlace(); i++){

            if (i-10<0) polka = "<br>вп";
            else polka = "<br>нп";

            if (i%10 == 0)placeRadioButtons.append("<tr class=\"kupe\">");

            if (setPlace.contains(i+1)) {
                placeRadioButtons.append("<td class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + polka + "</td>\n");
            }else{
                placeRadioButtons.append("<td class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + polka + "</td>\n");
            }

            if (i%10 == 9)placeRadioButtons.append("</tr>\n");


        }
        placeRadioButtons.append("</table></div>\n<p>вп - верхняя полка<br>нп - нижняя полка</p>");


        return placeRadioButtons;
    }

}
