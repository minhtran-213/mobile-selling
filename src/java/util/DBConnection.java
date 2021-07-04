/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author takah
 */
public class    DBConnection {
    private final String servername = "localhost";
    private final String dbname = "SE1502_Assignment_Groupxx";
    private final String portNumber = "1433";
    private final String userID = "Mem";
    private final String password = "0941767748";
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + servername + ":" + portNumber + ";databaseName=" + dbname;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        return DriverManager.getConnection(url, userID, password);
    }
}
