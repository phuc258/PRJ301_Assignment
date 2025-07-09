/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author SE190585
 */
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "index"; // mặc định nếu không có action
            }
            switch (action) {
                case "index":
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                case "search":
                    request.getRequestDispatcher("SearchBookController").forward(request, response);
                    break;
                // thêm các case khác tùy theo yêu cầu
                case "login":
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                    break;
                case "loginController":
                    request.getRequestDispatcher("LoginController").forward(request, response);
                    break;

                case "findBook":
                    request.getRequestDispatcher("SearchBookController").forward(request, response);
                    break;
                case "addnewbook":
                    request.getRequestDispatcher("AddNewBook.jsp").forward(request, response);
                    break;
                case "addnewbookcontroller":
                    request.getRequestDispatcher("AddNewBook").forward(request, response);
                    break;
                case "adminDashboard":
                    request.setAttribute("listBookBSearch", new BookDAO().getListBookByName(""));
                    request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
                    break;
                case "editBook":
                    request.getRequestDispatcher("editBook.jsp").forward(request, response);
                    break;
                case "editBookController":
                    request.getRequestDispatcher("EditBook").forward(request, response);
                    break;
                case "deletebook":
                    request.getRequestDispatcher("DeleteBook").forward(request, response);
                    break;
                case "quanliuser":
                    request.getRequestDispatcher("QuanliUser.jsp").forward(request, response);
                    break;
                case "setupconfig":
                    request.getRequestDispatcher("systemConfig.jsp").forward(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

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
