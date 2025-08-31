/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.BookDAO;
import dao.BorrowRecordDAO;
import dao.BorrowRequestDAO;
import dao.UserDAO;
import dto.BorrowRequest;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.DBUtils;

public class BorrowRequestController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try ( Connection conn = DBUtils.getConnection()) {
            BorrowRequestDAO dao = new BorrowRequestDAO(conn);
            ArrayList<BorrowRequest> requests = dao.getAllRequests();
            req.setAttribute("requests", requests);
            req.getRequestDispatcher("borrow_requests.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            int requestId = Integer.parseInt(request.getParameter("requestId"));
            BorrowRequestDAO borrowRequestdao = new BorrowRequestDAO();
            BorrowRequest brq = borrowRequestdao.getById(requestId);
            UserDAO ud = new UserDAO();
            try ( Connection conn = DBUtils.getConnection()) {
                BorrowRequestDAO dao = new BorrowRequestDAO(conn);
                if (action.equals("approve") || action.equals("reject")) {
                    dao.updateRequestStatus(requestId, action);
                    if (action.equals("approve")) {
                        BorrowRecordDAO brD = new BorrowRecordDAO();
                        int user_id = ud.getUserByName( brq.getUserName()).getId();
                        int book_id = (new BookDAO()).getByName(brq.getBookTitle()).getId();
                        String borrow_date = brq.getRequestDate();
                        brD.insert(user_id, book_id, borrow_date);
                    }
                }
                response.sendRedirect("BorrowRequestController");
            } catch (Exception e) {
                throw new ServletException(e);
            }
        } catch (Exception ex) {
            Logger.getLogger(BorrowRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
