/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.BookDAO;
import dto.Book;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditBook", urlPatterns = {"/EditBook"})
public class EditBook extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditBook at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("txtid"));
        String txttitle = request.getParameter("txttitle");
        String txtauthor = request.getParameter("txtauthor");
        String txtcategory = request.getParameter("txtcategory");
        String txtisbn = request.getParameter("txtisbn");
        int txtpublished_year = Integer.parseInt(request.getParameter("txtpublished_year"));
        int txttotal_copies = Integer.parseInt(request.getParameter("txttotal_copies"));
        String txtstatus = request.getParameter("txtstatus");
        Book bu = new Book(id, txttitle, txtauthor, txtisbn, txtcategory, txtpublished_year, txttotal_copies, txttotal_copies, txtstatus);
        int t = (new BookDAO()).UpdateNewData(bu);
        String message = "Fail";
        if (t != 0) {
            message = "Update thanh cong";
        }
        request.setAttribute("message", message);
        request.setAttribute("txtid", id);
        request.getRequestDispatcher("editBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("txtid"));
        String txttitle = request.getParameter("txttitle");
        String txtauthor = request.getParameter("txtauthor");
        String txtcategory = request.getParameter("txtcategory");
        String txtisbn = request.getParameter("txtisbn");
        int txtpublished_year = Integer.parseInt(request.getParameter("txtpublished_year"));
        int txttotal_copies = Integer.parseInt(request.getParameter("txttotal_copies"));
        String txtstatus = request.getParameter("txtstatus");
        Book bu = new Book(id, txttitle, txtauthor, txtisbn, txtcategory, txtpublished_year, txttotal_copies, txttotal_copies, txtstatus);
        int t = (new BookDAO()).UpdateNewData(bu);
        String message = "Fail";
        if (t != 0) {
            message = "Update thanh cong";
        }
        request.setAttribute("message", message);
        request.setAttribute("txtid", id);
        request.getRequestDispatcher("editBook.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
