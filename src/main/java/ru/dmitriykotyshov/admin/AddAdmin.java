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
import static ru.dmitriykotyshov.other.Message.nameAlreadyExists;
import static ru.dmitriykotyshov.other.Message.noSuperAdmin;

/**
 * Created by Дмитрий on 12.02.2018.
 */
public class AddAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name").trim();
        String password = String.valueOf(getHashCode(req.getParameter("password")));
        int type = Integer.valueOf(req.getParameter("typeAdmin"));
        if (type == 1) noSuperAdmin(req, resp);
        else {
            int boss = Integer.valueOf(req.getParameter("boss"));

            Locale.setDefault(Locale.ENGLISH);

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

            Session session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from TypeAdmin where id = :paramId");
            query.setParameter("paramId", type);
            TypeAdmin typeAdmin = (TypeAdmin) query.getResultList().get(0);

            query = session.createQuery("from Admin");
            List<ru.dmitriykotyshov.entity.Admin> adminSet = query.getResultList();
            boolean nameAlreadyExist = false;

            for(ru.dmitriykotyshov.entity.Admin a: adminSet)
                if (a.getName().equals(name)) nameAlreadyExist = true;

            if (nameAlreadyExist){

                nameAlreadyExists(req, resp, name);

            }else {

                ru.dmitriykotyshov.entity.Admin admin = new ru.dmitriykotyshov.entity.Admin();
                admin.setName(name);
                admin.setPass(password);
                admin.setBoss(boss);
                admin.setTypeAdmin(typeAdmin);
                session.save(admin);

                req.getRequestDispatcher("admins").forward(req, resp);

            }

            transaction.commit();
            session.close();
            sessionFactory.close();
        }

    }
}
