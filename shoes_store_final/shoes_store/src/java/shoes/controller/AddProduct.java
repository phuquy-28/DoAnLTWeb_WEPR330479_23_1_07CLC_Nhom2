/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package shoes.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoes.business.Cart;
import shoes.business.CategoryShoes;
import shoes.business.PromotionCode;
import shoes.business.ShoeSize;
import shoes.business.Shoes;
import shoes.business.User;
import shoes.business.sImage;
import shoes.data.CategoryDB;
import shoes.data.PromotionDB;
import shoes.data.ShoesDB;
import shoes.data.UserDB;

/**
 *
 * @author phuqu
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/addProduct", "/addUser", "/addCategory", "/addPromotion"})
public class AddProduct extends HttpServlet {

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
        if (action.equals("addUser")) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phone");
            String roleString = request.getParameter("role");
            int role = Integer.parseInt(roleString);
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setIsAdmin(role);
            newUser.setCart(new Cart());
            if (!UserDB.emailExists(email)) {
                UserDB.insert(newUser);
            } else {
                String message = "The email is already exists";
                request.setAttribute("message", message);
                request.getRequestDispatcher("loadUserAdmin?action=loadUser").forward(request, response);
            }
            response.sendRedirect("loadUserAdmin?action=loadUser");
        } else if (action.equals("addCate")) {
            String name = request.getParameter("nameCate");
            CategoryShoes category = new CategoryShoes(name);
            CategoryDB.insertCategory(category);
            response.sendRedirect("loadCategoryAdmin?action=loadCategory");
        } else if (action.equals("addPromotion")) {
            String code = request.getParameter("code");
            String amountString = request.getParameter("amount");
            double amount = Double.parseDouble(amountString);
            String start = request.getParameter("start");
            String end = request.getParameter("end");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = dateFormat.parse(start);
                endDate = dateFormat.parse(end);
            } catch (ParseException ex) {

            }
            if (PromotionDB.selectPromotionByCode(code) != null) {
                String message = "The Promotion Code is already exists";
                request.setAttribute("message", message);
                request.getRequestDispatcher("loadPromotionAdmin?action=loadPromotion").forward(request, response);
            } else {
                PromotionCode promotion = new PromotionCode(code, amount, startDate, endDate);
                PromotionDB.insert(promotion);
                response.sendRedirect("loadPromotionAdmin?action=loadPromotion");
            }
        } else {
            String name = request.getParameter("name");
            String imageURL1 = request.getParameter("image1");
            String imageURL2 = request.getParameter("image2");
            String imageURL3 = request.getParameter("image3");
            String color = request.getParameter("color");
//            String sizeString = request.getParameter("size");
//            double size = 0;  // Giá trị mặc định là 0
//            try {
//                size = Double.parseDouble(sizeString);
//            } catch (NumberFormatException e) {
//                // Xử lý khi người dùng nhập không phải là số nguyên
//            }

            // Lấy giá trị của các checkbox có tên "size"
            String[] selectedSizes = request.getParameterValues("size");

            List<ShoeSize> shoesize = new ArrayList<>();
            if (selectedSizes != null) {
                for (String size : selectedSizes) {
                    double dsize = 0;
                    try {
                        dsize = Double.parseDouble(size);
                        shoesize.add(new ShoeSize(dsize));
                    } catch (NumberFormatException e) {
                        // Xử lý khi người dùng nhập không phải là số nguyên
                    }
                }
            } else {
                shoesize = null;
            }

            String priceString = request.getParameter("price");
            long price = 0L;  // Giá trị mặc định là 0
            try {
                price = Long.parseLong(priceString);
            } catch (NumberFormatException e) {
                // Xử lý khi người dùng nhập không phải là số long
            }
            String description = request.getParameter("description");
            String categoryIDString = request.getParameter("category");
            int categoryID = Integer.parseInt(categoryIDString);
            CategoryShoes category = CategoryDB.getCategoryShoesById(categoryID);

            Shoes product = new Shoes();
            product.setProductName(name);
//            product.setProductSize(size);
            product.setSizes(shoesize);
            product.setProductColor(color);
            product.setProductDecription(description);
            product.setProductPrice(price);

//            product.setImageURL(imageURL);
            sImage image1 = new sImage(imageURL1);
            sImage image2 = new sImage(imageURL2);
            sImage image3 = new sImage(imageURL3);
            List<sImage> images = new ArrayList<>();
            images.add(image1);
            images.add(image2);
            images.add(image3);
            product.setImages(images);

            product.setCategory(category);

            ShoesDB.insertShoes(product);
            response.sendRedirect("loadProductAdmin?action=loadProduct");
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
