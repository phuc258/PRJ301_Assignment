/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.BorrowDAO;
import dao.BorrowRecordDAO;
import dto.BorrowRecord;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SE190585
 */
public class BorrowBookController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");       
        HttpSession session = request.getSession();
        try {
            // Lấy thông tin người dùng
            User loginUser = (User) session.getAttribute("USER");
            if (loginUser == null) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }
            
            int userId = loginUser.getId();
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            BorrowDAO dao = new BorrowDAO();

            // Kiểm tra đã gửi yêu cầu mượn sách này chưa
            boolean isDuplicate = dao.isBookAlreadyBorrowed(userId, bookId);

            if (isDuplicate) {
                request.setAttribute("MESSAGE", "You have already borrowed or requested this book.");
            } else {
                dao.insertBookRequest(bookId, userId);
                request.setAttribute("MESSAGE", "Borrow request sent successfully.");
            }
             request.getRequestDispatcher("BorrowCart.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            log("Invalid book ID format: " + e.getMessage());
            request.setAttribute("ERROR", "Invalid book ID.");
        } catch (SQLException | ClassNotFoundException e) {
            log("BorrowBookController - Error: " + e.getMessage());
            request.setAttribute("ERROR", "Something went wrong while processing your request.");
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
