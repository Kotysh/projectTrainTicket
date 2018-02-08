package ru.dmitriykotyshov.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Locale;

/**
 * Created by Дмитрий on 08.01.2018.
 */
public class ConnectionDAO {

    private DataSource dataSource = null;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private Context initContext = null;
    private Context envContext = null;


    public ConnectionDAO(){

        Locale.setDefault(Locale.ENGLISH);
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/myoracle");
        } catch (NamingException e) {
            System.out.println("ConnectionDAO: " + e.getMessage());
        }

    }

    public ResultSet getSelect(String select){

        try {

            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(select);

        } catch (SQLException e) {
            System.out.println("getSelect: " + e.getMessage());
        }

        return resultSet;

    }

    public void operatorDML (String sql){

        try {

            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println("operatorDML: " + e.getMessage());
        }

    }

    public void disconnect(){

        try {
            if (connection != null) connection.close();
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (initContext != null) initContext.close();
            if (envContext != null) envContext.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}




