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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author toila
 */
public class BookController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
//        String name = request.getParameter("BookName");
  String name = request.getParameter("txtsearch");
        BookDAO bd = new BookDAO();
        ArrayList<Book> list = bd.getListBookByName(name);
//        request.setAttribute("result",beforeFind);
//        request.getRequestDispatcher("ResultBook.jsp").forward(request, response);
        if (list != null && !list.isEmpty()) {
               for (Book book : list) {
                    out.print("<div>");
                    out.print("<form>");
                    out.print("<br>Title: " + book.getTitle());
                    out.print("<br>Author: " + book.getAuthor());
                    out.print("<br>Available copy: " + book.getAvailable_copies());
                    out.print("<br>ISBN: " + book.getIsbn());
                    if (book.getAvailable_copies() > 0) {
                        out.print("<input type='submit' value='request borrow book'>");
                    }
                    out.print("</form></div>");
               }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
