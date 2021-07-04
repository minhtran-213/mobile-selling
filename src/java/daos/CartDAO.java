/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Brand;
import dtos.CartItem;
import dtos.Phone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;

/**
 *
 * @author Admin
 */
public class CartDAO {

    Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (pstm != null) {
            pstm.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public boolean addOrderDetail(CartItem item, String orderid, String detailid) throws Exception {
        String sql = "INSERT INTO tblOrderDetail("
                + "OrderDetailID, OrderID, PhoneID, Quantity)"
                + "VALUES(?, ?, ?, ?)";

        ArrayList<Brand> lst = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);

                pstm.setString(1, detailid);
                pstm.setString(2, orderid);
                pstm.setString(3, item.getPhone().getId());
                pstm.setInt(4, item.getQuantity());

                pstm.executeUpdate();

                return true;

            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean addOrder(String userid, String orderid) throws Exception {
        String sql = "INSERT INTO tblOrder("
                + "OrderID, UserID)"
                + "VALUES(?, ?)";

        ArrayList<Brand> lst = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);

                pstm.setString(1, orderid);
                pstm.setString(2, userid);

                pstm.executeUpdate();

                return true;

            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<CartItem> getAllOrder(String id) throws Exception {

        String sql = "SELECT p.PhoneID, d.Quantity, p.Price\n"
                + "FROM tblOrder o, tblUser u, tblOrderDetail d, tblPhone p\n"
                + "WHERE	u.UserID = o.UserID\n"
                + "	AND o.OrderID = d.OrderID\n"
                + "	AND d.PhoneID = p.PhoneID"
                + "     AND u.UserID = ?";

        ArrayList<CartItem> lst = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, id);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String pid = rs.getString("PhoneId");
                    int quantity = rs.getInt("Quantity");

                    PhoneDAO dao = new PhoneDAO();
                    Phone p = dao.getPhonebyID(pid);
                    
                    CartItem order = new CartItem(p, quantity);
                    
                    lst.add(order);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

}
