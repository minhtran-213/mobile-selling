/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Brand;
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
public class BrandDAO {
    
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
    
    public ArrayList<Brand> getAllBrand() throws Exception{
        
        String sql="SELECT * FROM tblBrand";
        
        ArrayList<Brand> lst = new ArrayList<>();
        try{
            DBConnection db = new DBConnection();
            con = db.getConnection();
            if(con!=null){
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while(rs.next())
                {
                    String id = rs.getString("BrandID");
                    String name = rs.getString("BrandName");
                    
                    Brand b = new Brand(id, name);
                    lst.add(b);
                }
            }
        }
        finally{
            closeConnection();
        }
        return lst;    
    }
    
    
    public Brand getBrandByID(String id) throws Exception{
        Brand brand = null;
        
        try {
            String sql = "SELECT BrandName FROM tblBrand WHERE BrandID = ?";
            
            DBConnection db = new DBConnection();
            
            con = db.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            
            if (rs.next()){
                String brandName = rs.getString("BrandName");
                brand = new Brand(id, brandName);
                
            }
        } finally {
            closeConnection();
        }
        
        return brand;
    }
}
