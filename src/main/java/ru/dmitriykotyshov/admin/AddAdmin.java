package ru.dmitriykotyshov.admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.dmitriykotyshov.entity.TypeAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static ru.dmitriykotyshov.other.HashAdmin.getHashCode;

/**
 * Created by Дмитрий on 12.02.2018.
 */
public class AddAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String password = String.valueOf(getHashCode(req.getParameter("password")));
        int type = Integer.valueOf(req.getParameter("typeAdmin"));
        int boss = Integer.valueOf(req.getParameter("boss"));

        Locale.setDefault(Locale.ENGLISH);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from TypeAdmin where id = :paramId");
        query.setParameter("paramId", type);
        TypeAdmin typeAdmin = (TypeAdmin) query.getResultList().get(0);

        transaction.commit();

        session.beginTransaction();

        ru.dmitriykotyshov.entity.Admin admin = new ru.dmitriykotyshov.entity.Admin();
        admin.setName(name);
        admin.setPass(password);
        admin.setBoss(boss);
        admin.setTypeAdmin(typeAdmin);
        session.save(admin);

        transaction.commit();
        session.close();
        sessionFactory.close();

        req.getRequestDispatcher("admins").forward(req, resp);

    }
}
