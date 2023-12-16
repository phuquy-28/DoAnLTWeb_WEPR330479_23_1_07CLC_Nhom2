/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import shoes.business.Shoes;
import shoes.business.User;
import shoes.data.ShoesDB;
import shoes.data.UserDB;

@WebServlet(name = "LoadProductDetail", urlPatterns = {"/loadProductDetail"})
public class LoadProductDetail extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String productID = request.getParameter("productID");
        Shoes detailShoes = ShoesDB.selectShoesById(Integer.parseInt(productID));
        List<Shoes> listShoes = ShoesDB.selectShoesByCategory(detailShoes.getCategory());
        request.setAttribute("detailShoes", detailShoes);
        request.setAttribute("listShoes", listShoes);
        request.getRequestDispatcher("/DetailProduct.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
