/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import dao.BookDAO;
import dao.InventoryLogDAO;
import dto.Book;
import dto.InventoryLog;
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

public class InventoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try ( Connection conn = DBUtils.getConnection()) {
            BookDAO bookDAO = new BookDAO();
            ArrayList<Book> books = bookDAO.getListBookByName("");
            request.setAttribute("books", books);

            String bookIdStr = request.getParameter("bookId");
            if (bookIdStr != null) {
                int bookId = Integer.parseInt(bookIdStr);
                Book selectedBook = bookDAO.getBookById(bookId);
                if (selectedBook != null) {
                    InventoryLogDAO logDAO = new InventoryLogDAO();
                    ArrayList<InventoryLog> logs = logDAO.getLogsByBookId(bookId);
                    request.setAttribute("logs", logs);
                }
                request.setAttribute("selectedBook", selectedBook);

            }
            request.getRequestDispatcher("update_inventory.jsp").forward(request, response);
        } catch (Exception e) {
                request.setAttribute("MESS", "Khong co so nao");
                request.getRequestDispatcher("update_inventory.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BookDAO bookDAO = new BookDAO();
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Book b = bookDAO.getBookById(bookId);
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String note = "" + request.getParameter("note");
            String updateInventory = request.getParameter("updateInventory");
            if (updateInventory.equals("tru")) {
                quantity = -quantity;
            }

            String action;
            if (quantity > 0) {
                action = "increase";
            } else if (quantity < 0) {
                if (-quantity > b.getTotal_copies()) {
                    ArrayList<Book> books = bookDAO.getListBookByName("");
                    request.setAttribute("books", books);
                    request.setAttribute("selectedBook", b);
                    request.setAttribute("MESS", "Số trừ quá lớn");
                    request.getRequestDispatcher("update_inventory.jsp").forward(request, response);
                    return;
                }
                action = "decrease";
            } else {
                action = "adjust";
            }

            InventoryLog log = new InventoryLog();
            log.setBookId(bookId);
            log.setAction(action);
            log.setNote(note);
            log.setQuantity(quantity);
            InventoryLogDAO logDAO = new InventoryLogDAO();
            logDAO.insertLog(log);
            bookDAO.updateBookTotal(bookId, quantity);

            response.sendRedirect("InventoryController?bookId=" + bookId);

        } catch (Exception e) {
            throw new ServletException("Lỗi InventoryController.doPost: " + e.getMessage());
        }
    }

}
