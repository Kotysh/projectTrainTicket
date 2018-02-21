package ru.dmitriykotyshov.otherpage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.dmitriykotyshov.other.Message.contacts;

/**
 * Created by Дмитрий on 19.02.2018.
 */
public class Contacts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        contacts(req, resp);

    }
}
