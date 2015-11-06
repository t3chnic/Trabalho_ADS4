/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Programador
 */
public class ConexaoBD {
    public static Connection getConexao() throws SQLException
    {
        try 
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://localhost;"
                    + "databaseName=Ecommerce", "sa", "123456");
        } 
        catch (ClassNotFoundException erro) 
        {
            throw new SQLException(erro.getMessage());
        }

    }
    
}
