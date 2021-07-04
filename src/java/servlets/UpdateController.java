/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.BrandDAO;
import daos.PhoneDAO;
import dtos.Brand;
import dtos.ErrorObject;
import dtos.Phone;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 *
 * @author Admin
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "AdminListController";
    private static final String INVALID = "LoadPhoneIDController";
    private static final String UPLOAD_DIR = "images";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("txtName");
            String desc = request.getParameter("txtDesc");
            String br = request.getParameter("txtBrand");
            String image = uploadFile(request);
            String tprice = request.getParameter("txtPrice");
            double price = 0;
            
            boolean valid = true;
            ErrorObject errorObj = new ErrorObject();
            
            if (name.trim().isEmpty()) {
                errorObj.setNameError("Name shouldn't be empty!");
                valid = false;
            }
            if (desc.trim().isEmpty()) {
                errorObj.setDescriptError("Description shouldn't be empty!");
                valid = false;
            }
            if (!tprice.isEmpty()) {
                price = Double.parseDouble(tprice);
                if (price <= 1000) {
                    errorObj.setPriceError("Price shouldn't be less than 1000");
                    valid = false;
                }
            } else {
                errorObj.setPriceError("Price shouldn't be empty");
                valid = false;
            }
            PhoneDAO dao = new PhoneDAO();
            if (valid) {
                BrandDAO bdao = new BrandDAO();

                Brand brand = bdao.getBrandByID(br);
                Phone phone = new Phone(id, name, desc, brand, image, price);
                if (dao.updatePhone(phone)) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Insert Failed!");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
                
            }

        } catch (Exception e) {
            log("ERROR at UpdateController: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
        
    }
    
    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("txtPhoto");
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            System.out.println(basePath);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
