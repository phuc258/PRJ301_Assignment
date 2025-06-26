/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteBook", urlPatterns = {"/DeleteBook"})
public class DeleteBook extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteBook at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xoa_Name = request.getParameter("xoa_Name");
        int xoa_Year = Integer.parseInt(request.getParameter("xoa_Year"));
        String deleteBookButton = request.getParameter("deleteBookButton");
        if (deleteBookButton.equals("Lock")) {
            String attributeMessage = "Xoa khong thanh cong";
            if (xoa_Name != null && !xoa_Name.isEmpty()) {
                BookDAO dao = new BookDAO();
                dao.deleteBookByTitle(xoa_Name, xoa_Year);
                attributeMessage = "xoa thanh cong sach: " + xoa_Name + " " + xoa_Year;
            }
            request.setAttribute("attributeMessage", attributeMessage);
            request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
        } else {
            String attributeMessage = "Unlock khong thanh cong";
            if (xoa_Name != null && !xoa_Name.isEmpty()) {
                BookDAO dao = new BookDAO();
                dao.unlockBookByTitle(xoa_Name, xoa_Year);
                attributeMessage = "Unlock thanh cong sach: " + xoa_Name + " " + xoa_Year;
            }
            request.setAttribute("attributeMessage", attributeMessage);
            request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
