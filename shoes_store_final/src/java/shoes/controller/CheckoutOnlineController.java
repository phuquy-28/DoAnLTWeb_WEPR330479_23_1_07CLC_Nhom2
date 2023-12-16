/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoes.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoes.business.Cart;
import shoes.business.Invoice;
import shoes.business.LineItem;
import shoes.business.PromotionCode;
import shoes.business.ShippingInfo;
import shoes.business.Shoes;
import shoes.business.User;
import shoes.data.CartDB;
import shoes.data.InvoiceDB;
import shoes.data.LineItemDB;
import shoes.data.ShoesDB;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "checkoutOnlineController", urlPatterns = {"/vnpay"})
public class CheckoutOnlineController extends HttpServlet {

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

        List<LineItem> selectedLineItem = (List<LineItem>) session.getAttribute("items");

        User user = (User) session.getAttribute("user");
        Long userID = user.getUserId();

        Cart cart = CartDB.selectCartById(user.getCart().getCartID());
        if (cart == null) {
            cart = new Cart();
        }

        String transactionStatus = request.getParameter("vnp_TransactionStatus");

        String url = "invoice.jsp";
        
        if (transactionStatus.equals("00")) {
            for (LineItem item : selectedLineItem) {
                cart.removeItem(item);
            }

            CartDB.updateCart(cart);

            Invoice invoice = (Invoice) session.getAttribute("invoice");
            InvoiceDB.insert(invoice);
            cart = CartDB.selectCartById(user.getCart().getCartID());
            session.setAttribute("cart", cart);
            session.setAttribute("numberItems", cart.getProductList().size());
            Invoice.sendEmailForCustomer(invoice);
        } else {
            url = "cart.jsp";
        }

        request.getRequestDispatcher(url)
                .forward(request, response);
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
