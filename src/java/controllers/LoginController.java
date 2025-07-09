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

/**
 *
 * @author SE190585
 */
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */

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
        String email = request.getParameter("txtemail");
        String password = request.getParameter("txtpassword");

        PrintWriter out = response.getWriter();
        if (email != null && password != null) {
            //check email trung
            UserDAO d = new UserDAO();
            User us = d.getUser(email, password);
            if (us != null && us.getStatus().endsWith("active")) {
                //luu us vao session de co dâta cho cac tinh nang: welcome, logout, change profile
                HttpSession s = request.getSession();
                s.setAttribute("USER", us);
                String role = us.getRole();
                if (role.equalsIgnoreCase("admin")) {
                    //de welcome: coming soon
                    request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
                } else {

                    request.getRequestDispatcher("UserDashboard.jsp").forward(request, response);

                }
            } else {
                String s = "<h1> email or password is invalid !!</h1>";
                request.setAttribute("ERROR", s);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
