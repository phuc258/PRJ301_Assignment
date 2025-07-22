/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.BorrowRecordDAO;
import dao.OverdueDAO;
import dto.OverdueBook;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ThanhToanTienPhat", urlPatterns = {"/ThanhToanTienPhat"})
public class ThanhToanTienPhat extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            BorrowRecordDAO br = new BorrowRecordDAO();
            br.setStatus("returned", id);
            OverdueDAO dao = new OverdueDAO();
        try {
            ArrayList<OverdueBook> overdueList = dao.getOverdueBooks();
            request.setAttribute("OVERDUE_LIST", overdueList);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Không thể tải danh sách quá hạn.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OverdueBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("overdue_books.jsp")
                .forward(request, response);

        }
    }

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        public String getServletInfo
        
        
            () {
        return "Short description";
        }// </editor-fold>

    }
