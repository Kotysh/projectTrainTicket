package ru.dmitriykotyshov.admin;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static ru.dmitriykotyshov.other.HashAdmin.getHashCode;

/**
 * Created by Дмитрий on 09.02.2018.
 */
public class InputAdmin extends HttpServlet {

    private static final Logger logger = Logger.getLogger(InputAdmin.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = String.valueOf(getHashCode(req.getParameter("password")));

        Locale.setDefault(Locale.ENGLISH);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        HttpSession httpSession = req.getSession();

        Query query = session.createQuery("from Admin where name = :parName and pass = :parPass");
        query.setParameter("parName", login);
        query.setParameter("parPass", password);

        List<ru.dmitriykotyshov.entity.Admin> admins = query.getResultList();

        Boolean search = false;

        if (admins.size() == 0){
            logger.trace("Ничего не найдено");
        }else{
            logger.trace("Найден администратор");

            search = true;

            httpSession.setAttribute("login", login);
            httpSession.setAttribute("password", password);
            httpSession.setAttribute("typeAdmin", String.valueOf(admins.get(0).getTypeAdmin().getId()));

            for (ru.dmitriykotyshov.entity.Admin a:
                    admins) {
                logger.trace("Object Admin - "+a);
            }
        }


        transaction.commit();
        session.close();
        sessionFactory.close();


        logger.trace("login - "+login);
        logger.trace("pass - "+password);

        if (search == true){
            req.getRequestDispatcher("admin/admin.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("admin/inputAdmin.jsp").forward(req, resp);
        }

    }
}
