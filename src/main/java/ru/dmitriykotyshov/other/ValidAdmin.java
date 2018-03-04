package ru.dmitriykotyshov.other;

/**
 * Created by Дмитрий on 04.03.2018.
 */
public class ValidAdmin {

    private ValidAdmin(){

    }

    public static String menuAdmin(int typeAdmin){

        String menu = null;

        if (typeAdmin == 1){
            menu = "<li><a href=\"/customer\">Клиент(CUSTOMER)</a></li>\n"+
                    "<li><a href=\"/city\">Город(CITY)</a></li>\n"+
                    "<li><a href=\"/station\">Станция(STATION)</a></li>\n"+
                    "<li><a href=\"/document\">Документ(DOCUMENT)</a></li>\n"+
                    "<li><a href=\"/route\">Маршрут(ROUTE)</a></li>\n"+
                    "<li><a href=\"/routestation\">Маршруты и станции(ROUTE_STATION)</a></li>\n"+
                    "<li><a href=\"/train\">Поезд(TRAIN)</a></li>\n"+
                    "<li><a href=\"/wagon\">Вагон(WAGON)</a></li>\n"+
                    "<li><a href=\"/admins\">Администратор(ADMIN)</a></li>\n";
        } else if (typeAdmin == 2){
            menu = "<li><a href=\"/train\">Поезд(TRAIN)</a></li>\n"+
                    "<li><a href=\"/wagon\">Вагон(WAGON)</a></li>\n";
        }else if (typeAdmin == 3){
            menu = "<li><a href=\"/city\">Город(CITY)</a></li>\n"+
                    "<li><a href=\"/station\">Станция(STATION)</a></li>\n"+
                    "<li><a href=\"/route\">Маршрут(ROUTE)</a></li>\n"+
                    "<li><a href=\"/routestation\">Маршруты и станции(ROUTE_STATION)</a></li>\n";
        }

        return menu;

    }

    public static boolean validationSuperAdmin(int typeAdmin){
        return typeAdmin == 1;
    }

    public static boolean validationTrainAdmin(int typeAdmin){
        return typeAdmin == 1 || typeAdmin == 2;
    }

    public static boolean validationRouteAdmin(int typeAdmin){
        return typeAdmin == 1 || typeAdmin == 3;
    }

}
