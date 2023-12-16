/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shoes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoes.business.CategoryShoes;
import shoes.business.Shoes;
import shoes.data.CategoryDB;
import shoes.data.ShoesDB;

/**
 *
 * @author admin
 */
@WebServlet(name = "SearchingShop", urlPatterns = {"/searchingshop"})
public class SearchingShop extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");

        List<Shoes> listShoesAll = ShoesDB.searchShoesByName(keyword);

        //for the first page
        String pageNumberString = request.getParameter("pageNumber");
        if (pageNumberString == null) {
            pageNumberString = "1";
        }

        //get pageNumber
        int pageNumber = Integer.parseInt(pageNumberString);
        int pageSize = 12;   //default

        //get productCount of keyword
        int productCount = (int) listShoesAll.size();
        int endPage = productCount / pageSize;
        if (productCount % pageSize != 0) {
            endPage++;
        }

        //get offset
        int startIndex = (pageNumber - 1) * pageSize;  //size product per page
        int endIndex = Math.min(startIndex + pageSize, productCount);

        //get listShoes by pageNumber
        List<Shoes> listShoes;
        if (startIndex >= productCount || endIndex > productCount) {
            listShoes = null;
        } else {
            listShoes = listShoesAll.subList(startIndex, endIndex);
        }

        // SET ATTRIBUTE
        request.setAttribute("keyword", keyword);
        request.setAttribute("action", action);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("listShoes", listShoes);
        request.setAttribute("listCategory", CategoryDB.selectCategories());
        request.setAttribute("endPage", endPage);
        request.setAttribute("indexPage", pageNumber);
        request.setAttribute("productCount", productCount);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
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
