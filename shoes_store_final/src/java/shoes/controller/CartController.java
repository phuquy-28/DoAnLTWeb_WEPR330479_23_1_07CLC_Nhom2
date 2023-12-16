/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shoes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoes.business.Shoes;
import shoes.business.Cart;
import shoes.business.LineItem;
import shoes.business.User;
import shoes.business.PromotionCode;
import shoes.data.CartDB;
import shoes.data.InvoiceDB;
import shoes.data.ShoesDB;
import shoes.data.LineItemDB;
import shoes.data.PromotionDB;
import shoes.business.Invoice;

/**
 *
 * @author phuqu
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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
            action = "load";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("shop")) {
            url = "/index.jsp";    // the "index" page
        } else if (action.equals("load")) {
            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            if (user == null) {
                url = "login?action=login";
            } else {
                Long userID = user.getUserId();

                Cart cart = CartDB.selectCartById(user.getCart().getCartID());
                if (cart == null) {
                    cart = new Cart();
                }

                session.setAttribute("cart", cart);

                url = "cart.jsp";
            }

        } else if (action.equals("cart")) {
            String productID = request.getParameter("productID");
            String quantityString = request.getParameter("quantity");
            String size = request.getParameter("selectedSize");

            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            if (user == null) {
                url = "login?action=login";
                session.setAttribute("productID", productID);
            } else {
                Long userID = user.getUserId();

                Cart cart = CartDB.selectCartById(user.getCart().getCartID());
                if (cart == null) {
                    cart = new Cart();
                }

                //if the user enters a negative or invalid quantity,
                //the quantity is automatically reset to 1.
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityString);
                    if (quantity < 0) {
                        quantity = 1;
                    }
                } catch (NumberFormatException nfe) {
                    quantity = 1;
                }

                Shoes shoesItem = ShoesDB.selectShoesById(Integer.parseInt(productID));

                LineItem lineItem = new LineItem();
                lineItem.setProduct(shoesItem);
                lineItem.setQuantity(quantity);
                if (quantity == 0) {
                    size = request.getParameter("size");
                    lineItem.setShoeSize(Double.parseDouble(size));
                } else {
                    lineItem.setShoeSize(Double.parseDouble(size));
                }
                lineItem.setPriceUnit(shoesItem.getProductPrice());

                if (quantity > 0) {
                    cart.addItem(lineItem);
                } else if (quantity == 0) {
                    cart.removeItem(lineItem);
                }

                CartDB.updateCart(cart);

                cart = CartDB.selectCartById(user.getCart().getCartID());

                session.setAttribute("cart", cart);
                session.setAttribute("numberItems", cart.getProductList().size());

                url = "cart.jsp";
            }
        } else if (action.equals("update")) {
            String productID = request.getParameter("productID");
            String quantityString = request.getParameter("quantity");
            String size = request.getParameter("size");
            String change = request.getParameter("change");

            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            if (user == null) {
                url = "login?action=login";
            } else {
                Long userID = user.getUserId();

                Cart cart = CartDB.selectCartById(user.getCart().getCartID());;
                if (cart == null) {
                    cart = new Cart();
                }

                //if the user enters a negative or invalid quantity,
                //the quantity is automatically reset to 1.
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityString);
                    if (quantity < 0) {
                        quantity = 1;
                    }
                } catch (NumberFormatException nfe) {
                    quantity = 1;
                }

                Shoes shoesItem = ShoesDB.selectShoesById(Integer.parseInt(productID));

                LineItem lineItem = new LineItem();
                lineItem.setProduct(shoesItem);
                lineItem.setQuantity(quantity);
                lineItem.setShoeSize(Double.parseDouble(size));
                lineItem.setPriceUnit(shoesItem.getProductPrice());

                cart.updateItem(lineItem, change);

                CartDB.updateCart(cart);

                cart = CartDB.selectCartById(user.getCart().getCartID());

                session.setAttribute("cart", cart);
                session.setAttribute("numberItems", cart.getProductList().size());

                url = "cart.jsp";
            }
        } else if (action.equals("checkout")) {
            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            if (user == null) {
                url = "login?action=login";
            } else {
                Long userID = user.getUserId();

                Cart cart = CartDB.selectCartById(user.getCart().getCartID());
                if (cart == null) {
                    cart = new Cart();
                }

                String message = request.getParameter("message");
                String[] selectedItems = request.getParameterValues("selectedItem");

                List<LineItem> selectedLineItem = new ArrayList<>();

                if (selectedItems != null) {
                    double totalSelectedLineItem = 0;
                    for (String itemID : selectedItems) {
                        LineItem item = (LineItem) LineItemDB.selectLineItemById(Long.parseLong(itemID));
                        selectedLineItem.add(item);
                        totalSelectedLineItem += item.getAmount();
                    }
                    session.setAttribute("items", selectedLineItem);
                    session.setAttribute("totalPrice", totalSelectedLineItem);

                    String promotionCode = request.getParameter("promotion");
                    if (promotionCode.equals("")) {
                        PromotionCode promotion = new PromotionCode();
                        promotion.setPromotionCode("NULL");
                        session.setAttribute("promotion", promotion);
                        url = "/checkout.jsp";
                    } else {
                        PromotionCode promotion = PromotionDB.selectPromotionByCode(promotionCode);
                        if (promotion == null) {
                            message = "Code no exist";
                            request.setAttribute("message", message);
                            session.removeAttribute("promotion");
                            url = "cart.jsp";
                        } else {
                            session.setAttribute("promotion", promotion);
//        STOCKANDSTOCK4    
                            Invoice invoice = InvoiceDB.getInvoiceByUserPromotion(user, promotion.getPromotionCode());
                            if (invoice != null) {
                                message = "Code already use";
                                request.setAttribute("message", message);
                                session.removeAttribute("promotion");
                                url = "cart.jsp";
                            }
                            else {
                                url = "/checkout.jsp";
                            }
                        }
                    }
                } else {
                    message = "No item seleccted";
                    request.setAttribute("message", message);
                    url = "cart.jsp";
                }
            }
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
