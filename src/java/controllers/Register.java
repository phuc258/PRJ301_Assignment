/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author SE190585
 */
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String email = request.getParameter("txtemail");
            UserDAO udao = new UserDAO();
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");
            String name = request.getParameter("name");

            User user = new User();

            if (udao.isEmailDuplicated(email)) {
                user.setName(name);
                user.setEmail(email);
                request.setAttribute("message", "Email really exists");
                request.setAttribute("user", user);
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
                return;
            }
            if (!password.equals(passwordConfirm)) {
                user.setName(name);
                user.setEmail(email);
                request.setAttribute("message", "*Password don't match passwordConfirm");
                request.setAttribute("user", user);
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
                return;
            }

            udao.insertNewUser(name, email, password);

            //validate session then login again
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("messagRegister", "Register susscessfully, please Login");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
