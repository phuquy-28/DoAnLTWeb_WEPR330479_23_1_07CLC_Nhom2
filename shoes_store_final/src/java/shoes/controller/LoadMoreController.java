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
import shoes.business.Shoes;
import shoes.data.ShoesDB;

/**
 *
 * @author phuqu
 */
@WebServlet(name = "LoadMoreController", urlPatterns = {"/loadMore"})
public class LoadMoreController extends HttpServlet {

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
        if (action.equals("recommend")) {
            String offsetString = request.getParameter("recommendAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 18) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopNewProductNext(6, offset);

            for (Shoes o : recommendList) {
                out.println("<div class=\"recommendAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
        } else if (action.equals("trending")) {
            String offsetString = request.getParameter("trendingAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 18) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopBestSellingShoesNext(6, offset);

            for (Shoes o : recommendList) {
                out.println("<div class=\"trendingAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
        } else if (action.equals("airjordan")) {
            String offsetString = request.getParameter("airjordanAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 12) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopNewBrandNext(6, offset, 51);

            for (Shoes o : recommendList) {
                out.println("<div class=\"airjordanAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
        } else if (action.equals("adidas")) {
            String offsetString = request.getParameter("adidasAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 12) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopNewBrandNext(6, offset, 52);

            for (Shoes o : recommendList) {
                out.println("<div class=\"adidasAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
        } else if (action.equals("ascis")) {
            String offsetString = request.getParameter("ascisAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 12) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopNewBrandNext(6, offset, 53);

            for (Shoes o : recommendList) {
                out.println("<div class=\"ascisAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
        } else if (action.equals("converse")) {
            String offsetString = request.getParameter("converseAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 12) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopNewBrandNext(6, offset, 54);

            for (Shoes o : recommendList) {
                out.println("<div class=\"converseAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
        } else if (action.equals("newbalance")) {
            String offsetString = request.getParameter("newbalanceAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 12) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopNewBrandNext(6, offset, 55);

            for (Shoes o : recommendList) {
                out.println("<div class=\"newbalanceAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
        } else if (action.equals("nike")) {
            String offsetString = request.getParameter("nikeAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 12) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopNewBrandNext(6, offset, 56);

            for (Shoes o : recommendList) {
                out.println("<div class=\"nikeAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
        } else if (action.equals("yeezy")) {
            String offsetString = request.getParameter("yeezyAmount");
            int offset = Integer.parseInt(offsetString);
            if (offset >= 12) {
                return;
            }
            PrintWriter out = response.getWriter();
            List<Shoes> recommendList = ShoesDB.getTopNewBrandNext(6, offset, 57);

            for (Shoes o : recommendList) {
                out.println("<div class=\"yeezyAmount col-md-2 p-2\">\n"
                        + "                <a href=\"loadProductDetail?productID="+o.getProductID()+"\" class=\"text-decoration-none text-dark\">\n"
                        + "                    <div class=\"border border-dark\">\n"
                        + "                        <div class=\"col px-2 py-1\">\n"
                        + "                            <img src=\"" + o.getImages().get(0).getImageURL() + "\" alt=\"alt\" class=\"rounded mx-auto d-block img-fluid p-2\">\n"
                        + "                        </div>\n"
                        + "                        <div class=\"col px-2 py-1\">" + o.getProductName() + "</div>\n"
                        + "                        <div class=\"col px-2 py-1 h5\">$" + o.getProductPrice() + "</div>\n"
                        + "                    </div>\n"
                        + "                </a>\n"
                        + "            </div>");
            }
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
