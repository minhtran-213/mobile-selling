/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Brand;
import dtos.Phone;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

/**
 *
 * @author Admin
 */
public class PhoneDAO implements Serializable {

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

    public ArrayList<Phone> getAllPhone() throws Exception {

        String sql = "SELECT * FROM tblPhone";

        ArrayList<Phone> lst = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("PhoneID");
                    String name = rs.getString("PhoneName");
                    String desc = rs.getString("Description");
                    String brandID = rs.getString("BrandID");
                    String url = rs.getString("ImageURL");
                    double price = rs.getDouble("Price");

                    BrandDAO dao = new BrandDAO();
                    Brand brand = dao.getBrandByID(brandID);
                    Phone p = new Phone(id, name, desc, brand, url, price);
                    lst.add(p);
                }
            }
        } finally {
            closeConnection();
        }
        return lst;
    }

    public boolean addPhone(Phone p) throws Exception {

        String sql = "INSERT INTO tblPhone(PhoneID, "
                + "PhoneName, Description, BrandID, ImageURL, Price)"
                + "VALUES(?, ?, ?, ?, ?, ?)";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);

                pstm.setString(1, p.getId());
                pstm.setString(2, p.getName());
                pstm.setString(3, p.getDescript());
                pstm.setString(4, p.getBrand().getId());
                pstm.setString(5, p.getImgURL());
                pstm.setDouble(6, p.getPrice());

                pstm.executeUpdate();

                return true;
            }

        } finally {
            closeConnection();
        }
        return false;
    }

    public Phone getPhonebyID(String pid) throws Exception {

        String sql = "SELECT * FROM tblPhone WHERE PhoneID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, pid);
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String id = rs.getString("PhoneID");
                    String name = rs.getString("PhoneName");
                    String desc = rs.getString("Description");
                    String brandID = rs.getString("BrandID");
                    String url = rs.getString("ImageURL");
                    double price = rs.getDouble("Price");
                    
                    BrandDAO dao = new BrandDAO();
                    Brand brand = dao.getBrandByID(brandID);
                    Phone p = new Phone(pid, name, desc, brand, url, price);

                    return p;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }
    
    public Phone getPhonebyName(String name) throws Exception {

        String sql = "SELECT * FROM tblPhone WHERE PhoneName=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, name);
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String id = rs.getString("PhoneID");
                    String desc = rs.getString("Description");
                    String brandID = rs.getString("BrandID");
                    String url = rs.getString("ImageURL");
                    double price = rs.getDouble("Price");
                    
                    BrandDAO dao = new BrandDAO();
                    Brand brand = dao.getBrandByID(brandID);
                    Phone p = new Phone(id, name, desc, brand, url, price);

                    return p;
                }
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean deletePhone(String id) throws Exception {

        String sql = "DELETE FROM tblPhone WHERE PhoneID=?";
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, id);

                pstm.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updatePhone(Phone p) throws Exception {

        String sql = "UPDATE tblPhone SET PhoneName=?, Description=?,"
                + "BrandID=?, imageURL=?, Price=? WHERE PhoneID=?";

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);

                pstm.setString(1, p.getName());
                pstm.setString(2, p.getDescript());
                pstm.setString(3, p.getBrand().getId());
                pstm.setString(4, p.getImgURL());
                pstm.setDouble(5, p.getPrice());
                pstm.setString(6, p.getId());

                pstm.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<Phone> getAllPhoneByBrand(String brandID) throws Exception {
        String sql = "SELECT p.* FROM tblPhone p, tblBrand b WHERE b.BrandID = ? AND p.BrandID = b.BrandID";
        List<Phone> phoneList = new ArrayList();

        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, brandID);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("PhoneID");
                    String name = rs.getString("PhoneName");
                    String desc = rs.getString("Description");
                    String branddID = rs.getString("BrandID");
                    String url = rs.getString("ImageURL");
                    double price = rs.getDouble("Price");
                    
                    BrandDAO dao = new BrandDAO();
                    Brand brand = dao.getBrandByID(branddID);
                    Phone phone = new Phone(id, name, desc, brand, url, price);
                    phoneList.add(phone);
                }
            }
        } finally {
            closeConnection();
        }
        return phoneList;
    }
    
    public List<Phone> searchName(String searchValue) throws Exception {
        String sql = "SELECT * FROM tblPhone WHERE PhoneName LIKE ?";
        List<Phone> phoneList = new ArrayList();
        try {
            DBConnection db = new DBConnection();
            con = db.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, "%" + searchValue + "%");
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("PhoneID");
                String name = rs.getString("PhoneName");
                String desc = rs.getString("Description");
                String branddID = rs.getString("BrandID");
                String url = rs.getString("ImageURL");
                double price = rs.getDouble("Price");

                BrandDAO dao = new BrandDAO();
                Brand brand = dao.getBrandByID(branddID);
                Phone phone = new Phone(id, name, desc, brand, url, price);
                phoneList.add(phone);
            }
        } finally {
            closeConnection();
        }
        return phoneList;
    }
    
}
