/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dao.BorrowRequestDAO;
import dto.BorrowRequest;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author SE190585
 */
public class BorrowCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws  ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");

            if (user == null) {
                response.sendRedirect("Login.jsp");
                return;
            }

            BorrowRequestDAO dao = new BorrowRequestDAO();
            List<BorrowRequest> requests = dao.getBorrowRequestsByUserId(user.getId());
            System.out.println("User ID: " + user.getId());
            

            request.setAttribute("BORROW_REQUESTS", requests);
            request.getRequestDispatcher("BorrowCart.jsp").forward(request, response);
        } catch(Exception e){
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




