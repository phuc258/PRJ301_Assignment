/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.BorrowRequestDAO;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int requestId = Integer.parseInt(req.getParameter("requestId"));

        try ( Connection conn = DBUtils.getConnection()) {
            BorrowRequestDAO dao = new BorrowRequestDAO(conn);
            if (action.equals("approve") || action.equals("reject")) {
                dao.updateRequestStatus(requestId, action);
            }
            resp.sendRedirect("BorrowRequestController");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
