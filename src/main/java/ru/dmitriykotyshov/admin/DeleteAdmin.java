package ru.dmitriykotyshov.admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.dmitriykotyshov.other.Message.noDeleteSuperAdmin;

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

        Query query = session.createQuery("from Admin where id = :paramId").setParameter("paramId", adminId);
        ru.dmitriykotyshov.entity.Admin admin = (ru.dmitriykotyshov.entity.Admin) query.getResultList().get(0);

        if(admin.getTypeAdmin().getId() == 1){
            noDeleteSuperAdmin(req, resp);
        }else{
            session.remove(admin);
            resp.sendRedirect("admins");
        }

        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
