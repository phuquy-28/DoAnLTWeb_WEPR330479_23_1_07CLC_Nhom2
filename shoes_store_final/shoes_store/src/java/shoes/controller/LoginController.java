package shoes.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import shoes.business.Cart;
import shoes.business.Shoes;

import shoes.business.User;
import shoes.data.ShoesDB;
import shoes.data.UserDB;
import shoes.util.EmailUtil;

/**
 *
 * @author phuqu
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login", "/register", "/editUsr", "/resetPass", "/home", "/logout"})
public class LoginController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        String url = "";
        if (action.equals("login") || action.equals("signup")) {
            url = "/login.jsp";
        } else if (action.equals("forgetPassword")) {
            url = "/resetpw.jsp";
        } else if (action.equals("submitEmail")) {
            url = VerifyEmail(request, response);
        } else if (action.equals("home")) {
            url = "/loadingIndex";
        } else if (action.equals("register")) {
            url = register(request, response);
        } else if (action.equals("verify")) {
            url = login(request, response);
        } else if (action.equals("submitCode")) {
            url = SetNewPassword(request, response);
        } else if (action.equals("logout")) {
            HttpSession session = request.getSession();
//            session.invalidate();
            session.removeAttribute("user");
            url = "/loadingIndex";
        } else if (action.equals("goToEdit")) {
            url = loadEditProfile(request, response);
        } else if (action.equals("editUsr")) {
            url = editProfile(request, response);
        }

//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("emailCookie")) {
//                    request.setAttribute("username", cookie.getValue());
//                }
//
//                if (cookie.getName().equals("passCookie")) {
//                    request.setAttribute("password", cookie.getValue());
//                }
//            }
//        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);

    }

    private String register(HttpServletRequest request,
            HttpServletResponse response) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
        String password = request.getParameter("password");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setIsAdmin(0);
        user.setCart(new Cart());

        String url;
        String message;
        //check that email address doesn't already exist
        if (UserDB.emailExists(email)) {
            message = "This email address already exists. <br>"
                    + "Please enter another email address.";
            request.setAttribute("message", message);
            request.setAttribute("username", email);
            request.setAttribute("username", password);
            url = "/loadingIndex";
        } else {
            UserDB.insert(user);
            message = "Registrantion Successful. Now you can log in";
            request.setAttribute("message", message);
            url = "/login.jsp";
        }
        return url;
    }

    private String login(HttpServletRequest request,
            HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String url;
        String message;
        //check that email address doesn't already exist
        if (!UserDB.accountExists(email, password)) {
            message = "Wrong email or password. <br>"
                    + "Please re-try again.";
            request.setAttribute("message", message);
            request.setAttribute("email", email);
            url = "/login.jsp";
        } else {
            if (request.getParameter("rememberPassword") != null) {
                Cookie emailCookie = new Cookie("emailCookie", email);  //1 month
                emailCookie.setMaxAge(30 * 24 * 60 * 60);
                emailCookie.setPath("/"); //allow the download application to access it
                response.addCookie(emailCookie);

                Cookie passCookie = new Cookie("passCookie", password); //1 month
                passCookie.setMaxAge(30 * 24 * 60 * 60);
                passCookie.setPath("/"); //allow the download application to access it
                response.addCookie(passCookie);
            }
            message = "";
            request.setAttribute("message", message);
            User user = UserDB.selectAccount(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            url = "/loadingIndex";
        }
        return url;
    }

    private String VerifyEmail(HttpServletRequest request,
            HttpServletResponse response) {

        String email = request.getParameter("email");
        String url;
        String message;

        if (UserDB.emailExists(email)) {
            // Tạo mã xác nhận ngẫu nhiên
            String verificationCode = generateVerificationCode();

            // Lưu mã xác nhận tạm thời vào session (hoặc cache)
            HttpSession session = request.getSession();
            session.setAttribute("verificationCode", verificationCode);
            session.setAttribute("emailResetPass", email);
            // Gửi mã xác nhận đến email của người dùng - gửi email ở đây
            EmailUtil.sendEmail(email, "Verification for STOCK&STOCK", "Your verify code is: " + verificationCode);
            // Chuyển hướng đến trang nhập mã xác nhận
            url = "/verifycode.jsp";
        } else {
            message = "The email doesn't exists";
            request.setAttribute("message", message);
            url = "/resetpw.jsp";
        }
        return url;
    }

    // Hàm tạo mã xác nhận ngẫu nhiên
    private static String generateVerificationCode() {
        // Tạo mã ngẫu nhiên 6 ký tự
        int length = 6;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder code = new StringBuilder();
        Random rnd = new Random();
        while (code.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            code.append(chars.charAt(index));
        }
        return code.toString();
    }

    private String SetNewPassword(HttpServletRequest request,
            HttpServletResponse response) {

        String url;
        String message;
        String userEnteredCode = request.getParameter("verificationCode");

        // Lấy mã xác nhận từ session
        HttpSession session = request.getSession();
        String storedCode = (String) session.getAttribute("verificationCode");
        String email = (String) session.getAttribute("emailResetPass");

        if (userEnteredCode.equals(storedCode)) {
            // Mã xác nhận đúng - có thể tạo mật khẩu mới và gửi về email của người dùng
            // Thực hiện tạo mật khẩu mới và gửi email ở đây
            // Ví dụ: userEnteredCode đúng, thực hiện tạo mật khẩu mới và gửi email
            String newPassword = generateVerificationCode();
            User user = (User) UserDB.selectUser(email);
            user.setPassword(newPassword);
//            System.out.println("the password is: " + user.getPassword());
            UserDB.update(user);
            EmailUtil.sendEmail(email, "New password for STOCK&STOCK account", "Your new password is: " + newPassword);
            // Chuyển hướng đến trang thông báo thành công
            url = "/resetpwcomplete.jsp";
        } else {
            // Mã xác nhận không đúng, có thể hiển thị thông báo lỗi hoặc chuyển hướng về trang nhập lại mã xác nhận
            url = "/verifycode.jsp";
            message = "Wrong verification code";
            request.setAttribute("message", message);
        }
        return url;
    }

    private String loadEditProfile(HttpServletRequest request,
            HttpServletResponse response) {

        String url;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        request.setAttribute("id", user.getUserId());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());
        request.setAttribute("phone", user.getPhoneNumber());
        url = "/editProfile.jsp";
        return url;
    }

    private String editProfile(HttpServletRequest request,
            HttpServletResponse response) {

        String message;
        String url;
        String idString = request.getParameter("id");
        long id = Long.parseLong(idString);
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String newpass = request.getParameter("newpass");

        if (UserDB.accountExists(email, password)) {
            User user = UserDB.selectUserById(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phone);
            if (!newpass.equals("")) {
                user.setPassword(newpass);
            }

            UserDB.update(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            return url = loadEditProfile(request, response);
        } else {
            message = "Wrong password";
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            request.setAttribute("id", user.getUserId());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("phone", user.getPhoneNumber());
            request.setAttribute("message", message);
            return url = "/editProfile.jsp";
        }
    }

}
