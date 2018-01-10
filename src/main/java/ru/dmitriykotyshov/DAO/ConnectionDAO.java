package ru.dmitriykotyshov.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Дмитрий on 08.01.2018.
 */
public class ConnectionDAO {

    private DataSource dataSource = null;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public ConnectionDAO(){

        Locale.setDefault(Locale.ENGLISH);
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/myoracle");
        } catch (NamingException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public ResultSet getSelect(String select){

        try {

            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = select;
            resultSet = statement.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultSet;

    }

    public void disconnect(){
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
