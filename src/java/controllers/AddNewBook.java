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

@WebServlet(name = "AddNewBook", urlPatterns = {"/AddNewBook"})
public class AddNewBook extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewBook at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO bd = new BookDAO();
        String message = "";
        String title = request.getParameter("txttitle").trim();
        int txtpublished_year = Integer.parseInt(request.getParameter("txtpublished_year"));
        if (bd.checkBook(title, txtpublished_year)) {
            message = "Da co sach nay trong database";
            request.setAttribute("mess", message);
            request.getRequestDispatcher("AddNewBook.jsp").forward(request, response);
        } else {
            String author = request.getParameter("txtauthor").trim();
            String category = request.getParameter("txtcategory").trim();
            String isbn = request.getParameter("txtisbn").trim();
            int published_year = Integer.parseInt(request.getParameter("txtpublished_year").trim());
            int total_copies = Integer.parseInt(request.getParameter("txttotal_copies").trim());
            String status = request.getParameter("txtstatus");
            Book newBook = new Book(0, title, author, isbn, category, published_year, total_copies, total_copies, status);
            int t = bd.addNewBook(newBook);
            message = "Add sucess book:" + title;
            request.setAttribute("mess", message);
            request.getRequestDispatcher("AddNewBook.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO bd = new BookDAO();
        String message = "";
        String title = request.getParameter("txttitle").trim();
        int txtpublished_year = Integer.parseInt(request.getParameter("txtpublished_year"));
        if (bd.checkBook(title, txtpublished_year)) {
            message = "Da co sach nay trong database";
            request.setAttribute("mess", message);
            request.getRequestDispatcher("AddNewBook.jsp").forward(request, response);
        } else {
            String author = request.getParameter("txtauthor").trim();
            String category = request.getParameter("txtcategory").trim();
            String isbn = request.getParameter("txtisbn").trim();
            int published_year = Integer.parseInt(request.getParameter("txtpublished_year").trim());
            int total_copies = Integer.parseInt(request.getParameter("txttotal_copies").trim());
            String status = request.getParameter("txtstatus");
            Book newBook = new Book(0, title, author, isbn, category, published_year, total_copies, total_copies, status);
            int t = bd.addNewBook(newBook);
            message = "Add sucess book:" + title;
            request.setAttribute("mess", message);
            request.getRequestDispatcher("AddNewBook.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
