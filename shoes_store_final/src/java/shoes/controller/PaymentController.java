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
@WebServlet(name = "PaymentController", urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

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

        String url = "index.jsp";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "pay";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("shop")) {
            url = "/index.jsp";    // the "index" page
        } else if (action.equals("pay")) {
            HttpSession session = request.getSession();

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phone");
            String address = request.getParameter("address");
            String country = request.getParameter("country");
            String zip = request.getParameter("zip");

            double subTotal = (double) session.getAttribute("totalPrice");
            String discount = request.getParameter("discount");
            double total = subTotal - Double.parseDouble(discount);
            String paymentMethod = request.getParameter("paymentMethod");
            PromotionCode promotion = (PromotionCode) session.getAttribute("promotion");

            Date currentDate = new Date();

            List<LineItem> selectedLineItem = (List<LineItem>) session.getAttribute("items");

            User user = (User) session.getAttribute("user");
            Long userID = user.getUserId();

            Cart cart = CartDB.selectCartById(user.getCart().getCartID());
            if (cart == null) {
                cart = new Cart();
            }

            ShippingInfo shipInfo = new ShippingInfo();
            shipInfo.setFirstName(firstName);
            shipInfo.setLastName(lastName);
            shipInfo.setPhoneNumber(phoneNumber);
            shipInfo.setAddress(address);
            shipInfo.setCountry(country);
            shipInfo.setZipcode(zip);

            Invoice invoice = new Invoice();
            invoice.setUser(user);
            invoice.setAddress(shipInfo);
            invoice.setDiscount(Double.parseDouble(discount));
            invoice.setProductList(selectedLineItem);
            invoice.setSubTotal(subTotal);
            invoice.setTotal(total);
            invoice.setPaymentMethod(paymentMethod);
            invoice.setPromotion(promotion);
            invoice.setInvoiceDate(currentDate);

            if (paymentMethod.equals("Bank transfer")) {
                url = "vnpay_pay.jsp";
            } else {
                for (LineItem item : selectedLineItem) {
                    cart.removeItem(item);
                }
                
                CartDB.updateCart(cart);
                
                InvoiceDB.insert(invoice);
                
                Invoice.sendEmailForCustomer(invoice);
                url = "invoice.jsp";
            }

            cart = CartDB.selectCartById(user.getCart().getCartID());
            session.setAttribute("cart", cart);
            session.setAttribute("invoice", invoice);
            session.setAttribute("numberItems", cart.getProductList().size());
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
