/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shoes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoes.data.CategoryDB;
import shoes.data.PromotionDB;
import shoes.data.ShoesDB;
import shoes.data.UserDB;

/**
 *
 * @author phuqu
 */
@WebServlet(name = "DeleteProduct", urlPatterns = {"/deleteProduct" ,"/deleteUser" ,"/deleteCate", "/deletePromotion"})
public class DeleteProduct extends HttpServlet {

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
        String action = request.getParameter("action");
        if (action.equals("deleteUser")) {
            String idString = request.getParameter("uid");
            long id = Long.parseLong(idString);
            UserDB.deleteUserById(id);
            response.sendRedirect("loadUserAdmin?action=loadUser");
        } else if (action.equals("deleteCate")){
            String cidString = request.getParameter("cid");
            int cid = Integer.parseInt(cidString);
            CategoryDB.deleteCategory(CategoryDB.getCategoryShoesById(cid));
            response.sendRedirect("loadCategoryAdmin?action=loadCategory");
        } else if (action.equals("deletePromotion")){
            String pid = request.getParameter("pid");
            PromotionDB.deletePromotionByCode(pid);
            response.sendRedirect("loadPromotionAdmin?action=loadPromotion");
        }
        else {
            String pidString = request.getParameter("pid");
            int productID = Integer.parseInt(pidString);
            ShoesDB.deleteShoesById(productID);
            response.sendRedirect("loadProductAdmin?action=loadProduct");
        }
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
