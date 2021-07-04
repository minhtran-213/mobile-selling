/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnection;

/**
 *
 * @author takah
 */
public class UserDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }

        if (ps != null) {
            ps.close();
        }

        if (connection != null) {
            connection.close();
        }

    }

    public User checkLogin(String username, String password) throws Exception {
        User user = null;

        try {
            String sql = "SELECT * FROM tblUser WHERE Username = ? AND Password = ?";
            DBConnection db = new DBConnection();

            connection = db.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("FullName");
                String uid = rs.getString("UserID");
                user = new User(uid, username, fullname);
            }
        } finally {
            closeConnection();
        }

        return user;
    }
}
