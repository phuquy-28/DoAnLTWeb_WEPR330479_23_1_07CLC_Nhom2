/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shoes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoes.business.CategoryShoes;
import shoes.business.PromotionCode;
import shoes.business.ShoeSize;
import shoes.business.Shoes;
import shoes.business.User;
import shoes.data.CategoryDB;
import shoes.data.PromotionDB;
import shoes.data.ShoesDB;
import shoes.data.UserDB;

/**
 *
 * @author phuqu
 */
@WebServlet(name = "LoadEditProduct", urlPatterns = {"/loadEditProduct", "/loadEditUser", "/loadEditCate", "/loadEditPromotion"})
public class LoadEditProduct extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("loadEditUser")) {
            String udiString = request.getParameter("uid");
            long uid = Long.parseLong(udiString);
            User user = UserDB.selectUserById(uid);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/EditUser.jsp").forward(request, response);
        } else if (action.equals("loadEditCate")) {
            String cidString = request.getParameter("cid");
            int cid = Integer.parseInt(cidString);
            CategoryShoes cate = CategoryDB.getCategoryShoesById(cid);
            request.setAttribute("cate", cate);
            request.getRequestDispatcher("/EditCategory.jsp").forward(request, response);
        } else if (action.equals("loadEditPromotion")) {
            String pid = request.getParameter("pid");
            PromotionCode promotion = PromotionDB.selectPromotionByCode(pid);
            request.setAttribute("promotion", promotion);
            request.getRequestDispatcher("/EditPromotion.jsp").forward(request, response);
        } else {
            String pidString = request.getParameter("pid");
            int pid = Integer.parseInt(pidString);
            Shoes product = ShoesDB.selectShoesById(pid);
            List<CategoryShoes> listCategories = CategoryDB.selectCategories();

            // Extract size values as strings from the ShoeSize list
            List<String> productSizeValues = product.getSizes().stream()
                    .map(shoeSize -> String.valueOf(shoeSize.getSizeValue()))
                    .collect(Collectors.toList());

            request.setAttribute("product", product);
            request.setAttribute("listCategories", listCategories);
            request.setAttribute("productSizeValues", productSizeValues);

            request.getRequestDispatcher("/Edit.jsp").forward(request, response);
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
