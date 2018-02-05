package ru.dmitriykotyshov.admin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.DAO.DeleteDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.dmitriykotyshov.DAO.sql.DeleteSQL.getSqlDeleteDocument;

/**
 * Created by Дмитрий on 14.01.2018.
 */
public class DeleteDocument extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        DeleteDAO deleteDAO = applicationContext.getBean("DeleteDAO", DeleteDAO.class);

        String  documentId = (req.getParameter("document"));

        deleteDAO.deleteDocument(documentId);

        req.getRequestDispatcher("document").forward(req, resp);


    }
}
