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
import java.util.ArrayList;

public class ManagerUsers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerUsers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerUsers at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String yeucau = request.getParameter("yeucau");
        String emailUser = request.getParameter("emailUser");
        String message = "";
        if (yeucau.equals("xoa")) {
            int check = deleteUserByEmail(emailUser);
            response.sendRedirect("QuanliUser.jsp");
        } else if (yeucau.equals("ban")) {
            banAccByEmail(emailUser,1);
            response.sendRedirect("QuanliUser.jsp");
        } else if (yeucau.equals("moKhoa")) {
            banAccByEmail(emailUser,0);
            response.sendRedirect("QuanliUser.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    private UserDAO dao = new UserDAO();

    public int deleteUserByEmail(String email) {
        int result = 0;
        result = dao.deleteUserByEmail(email);
        return result;
    }

    public int banAccByEmail(String email,int check) {
        int result = 0;
        result = dao.banAccByEmail(email,check);
        return result;
    }



    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
