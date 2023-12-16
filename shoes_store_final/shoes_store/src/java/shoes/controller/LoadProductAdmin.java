/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shoes.controller;

import shoes.data.CategoryDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoes.business.CategoryShoes;
import shoes.business.Invoice;
import shoes.business.PromotionCode;
import shoes.business.Shoes;
import shoes.business.User;
import shoes.data.InvoiceDB;
import shoes.data.PromotionDB;
import shoes.data.ShoesDB;
import shoes.data.UserDB;

/**
 *
 * @author phuqu
 */
@WebServlet(name = "LoadProductAdmin", urlPatterns = {"/loadProductAdmin", "/loadUserAdmin", "/loadCategoryAdmin", "/loadPromotionAdmin", "/loadInvoiceAdmin"})
public class LoadProductAdmin extends HttpServlet {

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
        if (action.equals("loadUser")) {
//            List<User> listUser = UserDB.selectUsers();
//            request.setAttribute("listAccount", listUser);
            String pageNumberString = request.getParameter("pageNumber");
            if (pageNumberString == null) {
                pageNumberString = "1";
            }

            int pageNumber = Integer.parseInt(pageNumberString);

            int userCount = (int) UserDB.getUserCount();

            int endPage = userCount / 10;
            if (userCount % 10 != 0) {
                endPage++;
            }

            List<User> listUser = UserDB.getUserByPage(pageNumber, 10);

            request.setAttribute("listUser", listUser);
            request.setAttribute("endPage", endPage);
            request.setAttribute("index", pageNumber);
            request.setAttribute("userCount", userCount);

            request.getRequestDispatcher("/ManagerAccount.jsp").forward(request, response);
        } else if (action.equals("loadCategory")) {
            List<CategoryShoes> listCategories = CategoryDB.selectCategories();
            int cateCount = (int) listCategories.size();
            request.setAttribute("listCategories", listCategories);
            request.setAttribute("cateCount", cateCount);
            request.getRequestDispatcher("/ManagerCategory.jsp").forward(request, response);
        } else if (action.equals("loadPromotion")) {
            List<PromotionCode> listPromotion = PromotionDB.selectPromotions();
            int promotionCount = (int) listPromotion.size();
            request.setAttribute("listPromotion", listPromotion);
            request.setAttribute("promotionCount", promotionCount);
            request.getRequestDispatcher("/ManagerPromotion.jsp").forward(request, response);
        } else if (action.equals("loadInvoice")) {
            List<Invoice> listInvoice = InvoiceDB.selectsInvoices();
            int invoiceCount = listInvoice.size();
            request.setAttribute("listInvoice", listInvoice);
            request.setAttribute("invoiceCount", invoiceCount);
            request.getRequestDispatcher("/ManagerInvoice.jsp").forward(request, response);
        } else {
            List<CategoryShoes> listCategories = CategoryDB.selectCategories();
            request.setAttribute("listCategories", listCategories);

            String pageNumberString = request.getParameter("pageNumber");
            if (pageNumberString == null) {
                pageNumberString = "1";
            }

            int pageNumber = Integer.parseInt(pageNumberString);

            int productCount = (int) ShoesDB.getProductCount();

            int endPage = productCount / 5;
            if (productCount % 5 != 0) {
                endPage++;
            }

            List<Shoes> listShoes = ShoesDB.getShoesByPage(pageNumber, 5);

            request.setAttribute("listShoes", listShoes);
            request.setAttribute("endPage", endPage);
            request.setAttribute("index", pageNumber);
            request.setAttribute("productCount", productCount);

            request.getRequestDispatcher("/ManagerProduct.jsp").forward(request, response);

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
