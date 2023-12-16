/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shoes.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoes.business.Cart;
import shoes.business.Shoes;
import shoes.business.User;
import shoes.data.ShoesDB;
import shoes.data.CartDB;

/**
 *
 * @author phuqu
 */
@WebServlet(name = "LoadingIndex", urlPatterns = {"/loadingIndex"})
public class LoadingIndex extends HttpServlet {

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
        HttpSession session = request.getSession();
        List<Shoes> recommendList = ShoesDB.getTopProducts(6);
        session.setAttribute("recommendList", recommendList);

        List<Shoes> trendingList = ShoesDB.getTopBestSellingShoes(6);
        session.setAttribute("trendingList", trendingList);

        List<Shoes> airjordan = ShoesDB.getTopNewBrand(6, 51); //51 - categoryID air jordan
        session.setAttribute("airjordanList", airjordan);

        List<Shoes> adidas = ShoesDB.getTopNewBrand(6, 52);
        session.setAttribute("adidasList", adidas);

        List<Shoes> ascis = ShoesDB.getTopNewBrand(6, 53);
        session.setAttribute("ascisList", ascis);

        List<Shoes> converse = ShoesDB.getTopNewBrand(6, 54);
        session.setAttribute("converseList", converse);

        List<Shoes> newbalance = ShoesDB.getTopNewBrand(6, 55);
        session.setAttribute("newbalanceList", newbalance);

        List<Shoes> nike = ShoesDB.getTopNewBrand(6, 56);
        session.setAttribute("nikeList", nike);

        List<Shoes> yeezy = ShoesDB.getTopNewBrand(6, 57);
        session.setAttribute("yeezyList", yeezy);

        User user = (User) session.getAttribute("user");

        if (user == null) {
            session.setAttribute("numberItems", 0);
        } else {
            Long userID = user.getUserId();

            Cart cart = CartDB.selectCartById(user.getCart().getCartID());
            if (cart == null) {
                cart = new Cart();
            }         
            session.setAttribute("numberItems", cart.getProductList().size());
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
