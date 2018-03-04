package ru.dmitriykotyshov.other;

import org.apache.log4j.Logger;
import ru.dmitriykotyshov.Buy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 19.02.2018.
 */
public class Message {

    private static final String PLACE_BOUGHT = "Извините, но выбранное место уже было куплено";
    private static final String NO_5_MINUTE = "Извините, но на данный поезд покупка билетов приостановлена";
    private static final String ABOUT_US = "Мы новая молодая компания в команде которой работают специалисты с многолетним "+
                                            "опытом работы, обеспечивающие безопасность, комфорт и Ваш отдых во время "+
                                            "переездов на поездах компании Train&Ticket.<br>\n"+
                                            "Счастливого пути!!!";
    private static final String CONTACTS = "Наши контакты:<br>\n"+
                                            "email: kotyshex2k17@mail.ru<br>\n"+
                                            "телефон: 555-007";
    private static final String NO_SUPER_ADMIN = "Нельзя создать супер администратора!!!";
    private static final String NAME_ALREADY_EXISTS = "Администратор с именем %s, уже существует!!!";
    private static final String NO_DELETE_SUPER_ADMIN = "Нельзя удалить супер администратора!!!";
    private static final String INSUFFICIENT_RIGHTS = "Недостаточно прав";


    private static final String MESSAGE_PAGE = "messagepage.jsp";
    private static final String ADMIN_MESSAGE = "admin/adminmessage.jsp";
    private static final String IN_ADMIN_MESSAGE = "adminmessage.jsp";


    private final static Logger logger = Logger.getLogger(Buy.class);


    private static void getMessage (HttpServletRequest request, HttpServletResponse responce, String mess, String page) {

        request.setAttribute("message", mess);
        try {
            request.getRequestDispatcher(page).forward(request, responce);
        } catch (ServletException e) {
            logger.debug("ServletException - placeBought: ", e);
        } catch (IOException e) {
            logger.debug("IOException - placeBought: ", e);
        }

    }

    public static void no5Minute(HttpServletRequest request, HttpServletResponse responce){

        getMessage(request, responce, NO_5_MINUTE, MESSAGE_PAGE);

    }

    public static void placeBought(HttpServletRequest request, HttpServletResponse responce){

        getMessage(request, responce, PLACE_BOUGHT, MESSAGE_PAGE);

    }

    public static void aboutUs(HttpServletRequest request, HttpServletResponse responce){

        getMessage(request, responce, ABOUT_US, MESSAGE_PAGE);

    }

    public static void contacts(HttpServletRequest request, HttpServletResponse responce){

        getMessage(request, responce, CONTACTS, MESSAGE_PAGE);

    }

    public static void noSuperAdmin(HttpServletRequest request, HttpServletResponse response){

        getMessage(request, response, NO_SUPER_ADMIN, ADMIN_MESSAGE);

    }

    public static void nameAlreadyExists(HttpServletRequest request, HttpServletResponse response, String name){

        getMessage(request, response, String.format(NAME_ALREADY_EXISTS, name), ADMIN_MESSAGE);

    }

    public static void noDeleteSuperAdmin(HttpServletRequest request, HttpServletResponse response){

        getMessage(request, response, NO_DELETE_SUPER_ADMIN, ADMIN_MESSAGE);

    }

    public static void insufficientRights(HttpServletRequest request, HttpServletResponse response){

        getMessage(request, response, INSUFFICIENT_RIGHTS, IN_ADMIN_MESSAGE);

    }

    public static void noRight(HttpServletRequest request, HttpServletResponse response){

        getMessage(request, response, INSUFFICIENT_RIGHTS, ADMIN_MESSAGE);

    }


}
