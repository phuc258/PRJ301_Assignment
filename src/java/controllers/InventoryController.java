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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try ( Connection conn = DBUtils.getConnection()) {
            BookDAO bookDAO = new BookDAO();
            ArrayList<Book> books = bookDAO.getListBookByName("");
            req.setAttribute("books", books);

            String bookIdStr = req.getParameter("bookId");
            if (bookIdStr != null) {
                int bookId = Integer.parseInt(bookIdStr);
                Book selectedBook = bookDAO.getBookById(bookId);
                if (selectedBook != null) {
                    InventoryLogDAO logDAO = new InventoryLogDAO();
                    ArrayList<InventoryLog> logs = logDAO.getLogsByBookId(bookId);
                    req.setAttribute("logs", logs);
                }
                req.setAttribute("selectedBook", selectedBook);

            }

            req.getRequestDispatcher("update_inventory.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Lỗi InventoryController.doGet: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String note =""+ request.getParameter("note");
            String updateInventory = request.getParameter("updateInventory");
            if (updateInventory.equals("tru")) {
                quantity = -quantity;
            }

            BookDAO bookDAO = new BookDAO();

            bookDAO.updateBookTotal(bookId, quantity);

            String action;
            if (quantity > 0) {
                action = "increase";
            } else if (quantity < 0) {
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

            response.sendRedirect("InventoryController?bookId=" + bookId);

        } catch (Exception e) {
            throw new ServletException("Lỗi InventoryController.doPost: " + e.getMessage());
        }
    }

}
