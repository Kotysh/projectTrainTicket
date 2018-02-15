package ru.dmitriykotyshov.admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 12.02.2018.
 */
public class DeleteAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Integer adminId = Integer.valueOf(req.getParameter("delAdmin"));

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("DELETE Admin WHERE id = :paramId").setParameter("paramId", adminId).executeUpdate();

        transaction.commit();
        session.close();
        sessionFactory.close();

        req.getRequestDispatcher("admins").forward(req, resp);


    }
}
