/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.PhoneDAO;
import dtos.CartItem;
import dtos.Phone;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoadIDController extends HttpServlet {

    static final String SUCCESS = "cartView.jsp";
    static final String ERROR = "error.jsp";
    
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
        try{
            String id = request.getParameter("id");
            PhoneDAO dao = new PhoneDAO();
            Phone p = dao.getPhonebyID(id);
            
            HttpSession session = request.getSession();

            if (session.getAttribute("CART") == null){
                ArrayList<CartItem> cart = new ArrayList<CartItem>();
                cart.add(new CartItem(p, 1));
                session.setAttribute("CART", cart);
            }
            else{
                ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("CART");
                int index = isExisting(p.getId(), cart);
                if(index == -1){
                    cart.add(new CartItem(p, 1));
                }
                else{
                    int quantity = cart.get(index).getQuantity() +1;
                    cart.get(index).setQuantity(quantity);
                }
                session.setAttribute("CART", cart);
            }
            url = SUCCESS;
           
        }
        catch(Exception e){
            log("ERROR at LoadIDController: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
//            request.getRequestDispatcher(url).forward(request, response);
                response.sendRedirect(SUCCESS);
        }
        
    }
    
    private int isExisting(String id, List<CartItem> cart){
        for(int i = 0; i < cart.size(); i++){
            if(cart.get(i).getPhone().getId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
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
