/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author SE190585
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "Error.jsp";
    private static final String FIRST = "index.jsp";
    private static final String LOGIN = "Login.jsp";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH_BOOK_CONTROLLER = "SearchBookController";
    private static final String ADD_NEW_BOOK_VIEW = "AddNewBook.jsp";
    private static final String ADD_NEW_BOOK_CONTROLLER = "AddNewBook";
    private static final String MANAGER_BOOK_VIEW = "AdminDashboard.jsp";
    private static final String EDIT_BOOK_VIEW = "editBook.jsp";
    private static final String EDIT_BOOK_CONTROLLER = "EditBook";
    private static final String EDIT_BOOK_DELETE = "DeleteBook";
    private static final String MANAGER_USER_VIEW = "QuanliUser.jsp";
    private static final String MANAGER_USER_CONTROLLER = "ManagerUsers";
    private static final String FIND_USER = "FindUser";
    private static final String CONFIG_VIEW = "UpdateConfigController";
    private static final String INVENTORY_UPDATE = "InventoryController";
    private static final String THANH_TOAN_TIEN_PHAT = "ThanhToanTienPhat";
    private static final String BORROW_BOOK_CONTROLLER = "BorrowBookController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String url = FIRST;
        try {
            String action = request.getParameter("action");

            switch (action) {

                case "search":
                    url = SEARCH_BOOK_CONTROLLER;
                    break;
                case "login":
                    url = LOGIN;
                    break;
                case "loginController":
                    url = LOGIN_CONTROLLER;
                    break;
                case "findBook":
                    url = SEARCH_BOOK_CONTROLLER;
                    break;
                case "addnewbook":
                    url = ADD_NEW_BOOK_VIEW;
                    break;
                case "addnewbookcontroller":
                    url = ADD_NEW_BOOK_CONTROLLER;
                    break;
                case "adminDashboard":
                    request.setAttribute("listBookBSearch", new BookDAO().getListBookByName(""));
                    url = MANAGER_BOOK_VIEW;
                    break;
                case "editBook":
                    url = EDIT_BOOK_VIEW;
                    break;
                case "editBookController":
                    url = EDIT_BOOK_CONTROLLER;
                    break;
                case "deletebook":
                    url = EDIT_BOOK_DELETE;
                    break;
                case "quanliuser":
                    url = MANAGER_USER_VIEW;
                    break;
                case "ManagerUsers":
                    url = MANAGER_USER_CONTROLLER;
                    break;
                case "setupconfig":
                    url = CONFIG_VIEW;
                    break;
                case "updateInventory":
                    url = INVENTORY_UPDATE;
                    break;
                case "Find User":
                    url = FIND_USER;
                    break;
                case "ThanhToanTienPhat":
                    url = THANH_TOAN_TIEN_PHAT;
                    break;
                case "logout":
                    url = "LogoutController";
                    break;
                case "viewbook":
                    url = "ListBookController";
                    break;
                case "viewcategory":
                    String category = request.getParameter("category");
                    request.setAttribute("category", category);
                    url = "index.jsp";
                    break;
                case "viewProfile":
                    url = "ViewProfile.jsp";
                    break;
                case "UpdateUser":
                    url = "UpdateUser";
                    break;
                case "SearchBookUser":
                    url = "SearchBookUserController";
                    break;
                case "register":
                    url = "SignUp.jsp";
                    break;
                case "Register":
                    url = "Register";
                    break;
                case "borrowBook":
                    url = BORROW_BOOK_CONTROLLER;
                    break;
                case "borrowHistory":
                    url = "BorrowHistoryController";
                    break;
                case "cancelBorrowRequest":
                    url = "CancelBorrowRequestController";
                    break;
                default:
                    url = ERROR;
                    break;
            }

        } catch (Exception e) {
            log("error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
