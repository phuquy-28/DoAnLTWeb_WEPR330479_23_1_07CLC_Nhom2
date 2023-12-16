package shoes.controller;

import shoes.data.CategoryDB;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Collections.list;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoes.business.CategoryShoes;
import shoes.business.Shoes;
import shoes.data.ShoesDB;

@WebServlet(name = "LoadProductShop", urlPatterns = {"/loadProductShop"})
public class LoadProductShop extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // GET PARAMETER
        String action = request.getParameter("action");

        if (action.equals("loadAll")) {
            // CODE
            int pageNumber = 1;
            int productCount = (int) ShoesDB.getProductCount();
            int pageSize = 12;
            int endPage = productCount / pageSize;
            if (productCount % pageSize != 0) {
                endPage++;
            }
            List<Shoes> listShoes = ShoesDB.getShoesByPage(pageNumber, pageSize);
            // SET ATTRIBUTE
            request.setAttribute("action", action);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("listShoes", listShoes);
            request.setAttribute("listCategory", CategoryDB.selectCategories());
            request.setAttribute("endPage", endPage);
            request.setAttribute("indexPage", pageNumber);
            request.setAttribute("productCount", productCount);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } else if (action.equals("loadByPage")) {
            // GET PARAMETER
            String pageNumberString = request.getParameter("pageNumber");
            // CODE
            if (pageNumberString.equals(null)) {
                pageNumberString = "1";
            }
            int pageNumber = Integer.parseInt(pageNumberString);
            int productCount = (int) ShoesDB.getProductCount();
            int pageSize = 12;
            int endPage = productCount / pageSize;
            if (productCount % pageSize != 0) {
                endPage++;
            }
            List<Shoes> listShoes = ShoesDB.getShoesByPage(pageNumber, pageSize);
            // SET ATTRIBUTE
            request.setAttribute("action", action);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("listShoes", listShoes);
            request.setAttribute("listCategory", CategoryDB.selectCategories());
            request.setAttribute("endPage", endPage);
            request.setAttribute("indexPage", pageNumber);
            request.setAttribute("productCount", productCount);
            request.getRequestDispatcher("shop.jsp").forward(request, response);

        } else if (action.equals("loadByCategory")) {
            // GET PARAMETER
            String CID = request.getParameter("CID");
            String pageNumberString = request.getParameter("pageNumber");
            // CODE
            if (pageNumberString.equals(null)) {
                pageNumberString = "1";
            }
            int pageNumber = Integer.parseInt(pageNumberString);
            int categoryID = Integer.parseInt(CID);
            CategoryShoes category = CategoryDB.getCategoryShoesById(categoryID);
            int pageSize = 12;
            List<Shoes> l1 = ShoesDB.selectShoesByCategory(category);
            List<Shoes> listShoes = ShoesDB.getShoesByPageCategory(category, pageNumber, pageSize);
            int productCount = l1.size();
            int endPage = productCount / pageSize;
            if (productCount % pageSize != 0) {
                endPage++;
            }
            // SET ATTRIBUTE
            request.setAttribute("CID", CID);
            request.setAttribute("action", action);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("listShoes", listShoes);
            request.setAttribute("listCategory", CategoryDB.selectCategories());
            request.setAttribute("endPage", endPage);
            request.setAttribute("indexPage", pageNumber);
            request.setAttribute("productCount", productCount);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } else if (action.equals("loadbysize")) {
            // GET PARAMETER
            String sizeString = request.getParameter("size");
            String pageNumberString = request.getParameter("pageNumber");
            // CODE
            if (pageNumberString.equals(null)) {
                pageNumberString = "1";
            }
            int pageNumber = Integer.parseInt(pageNumberString);
            double size = Double.parseDouble(sizeString);
            int pageSize = 12;
            List<Shoes> l1 = ShoesDB.getShoesBySize(size);
            List<Shoes> listShoes = ShoesDB.getShoesByPageSize(size, pageNumber, pageSize);
            int productCount = l1.size();
            int endPage = productCount / pageSize;
            if (productCount % pageSize != 0) {
                endPage++;
            }
            // SET ATTRIBUTE
            request.setAttribute("size", size);
            request.setAttribute("action", action);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("listShoes", listShoes);
            request.setAttribute("listCategory", CategoryDB.selectCategories());
            request.setAttribute("endPage", endPage);
            request.setAttribute("indexPage", pageNumber);
            request.setAttribute("productCount", productCount);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } else if (action.equals("loadbyprice")) {

            // GET PARAMETER
            String priceRange = request.getParameter("priceRange");
            String pageNumberString = request.getParameter("pageNumber");
            // CODE
            if (pageNumberString.equals(null)) {
                pageNumberString = "1";
            }
            int pageNumber = Integer.parseInt(pageNumberString);
//            double size = Double.parseDouble(sizeString);
            int pageSize = 12;
            
            List<Shoes> l1 ;
            List<Shoes> listShoes;
            if (priceRange.equals("under100")){
                l1 = ShoesDB.getShoesByPrice(0L,100L);
                listShoes = ShoesDB.getShoesByPagePrice(0L,100L, pageNumber, pageSize);
            }
            else if (priceRange.equals("100to200")){
                l1 = ShoesDB.getShoesByPrice(100L,200L);
                listShoes = ShoesDB.getShoesByPagePrice(100L,200L, pageNumber, pageSize);
            }
            else if (priceRange.equals("200to300")){
                l1 = ShoesDB.getShoesByPrice(200L,300L);
                listShoes = ShoesDB.getShoesByPagePrice(200L,300L, pageNumber, pageSize);
            }
            else if (priceRange.equals("300to400")){
                l1 = ShoesDB.getShoesByPrice(300L,400L);
                listShoes = ShoesDB.getShoesByPagePrice(300L,400L, pageNumber, pageSize);
            }
            else{
                l1 = ShoesDB.getShoesByPrice(400L,1000000L);
                listShoes = ShoesDB.getShoesByPagePrice(400L,1000000L, pageNumber, pageSize);
            }
            
            
            int productCount = l1.size();
            int endPage = productCount / pageSize;
            if (productCount % pageSize != 0) {
                endPage++;
            }
            // SET ATTRIBUTE
            request.setAttribute("priceRange", priceRange);
            request.setAttribute("action", action);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("listShoes", listShoes);
            request.setAttribute("listCategory", CategoryDB.selectCategories());
            request.setAttribute("endPage", endPage);
            request.setAttribute("indexPage", pageNumber);
            request.setAttribute("productCount", productCount);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
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
