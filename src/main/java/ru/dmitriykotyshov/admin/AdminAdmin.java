package ru.dmitriykotyshov.admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.entity.TypeAdmin;
import ru.dmitriykotyshov.support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Дмитрий on 12.02.2018.
 */
public class AdminAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Locale.setDefault(Locale.ENGLISH);

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");

        List<String> recAdmins = selectDAO.getRecursiveAdmins();
        System.out.println("Выводим админов!!!! РЕКУРСИВНО");
        for (String s: recAdmins){
            System.out.println(s);
        }

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Admin");
        List<Admin> admins = query.getResultList();

        System.out.println(admins);

        query = session.createQuery("from TypeAdmin where id != 1");
        List<TypeAdmin> types = query.getResultList();

        transaction.commit();
        session.close();
        sessionFactory.close();

        req.setAttribute("recAdmins", recAdmins);
        req.setAttribute("types", types);
        req.setAttribute("admins", admins);
        req.getRequestDispatcher("admin/admins.jsp").forward(req, resp);

    }
}
