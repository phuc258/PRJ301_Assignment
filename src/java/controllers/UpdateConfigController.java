/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dao.SystemConfigDAO;
import dto.Config;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;


@WebServlet(name="UpdateConfigController", urlPatterns={"/UpdateConfigController"})
public class UpdateConfigController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateConfigController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateConfigController at " + request.getContextPath () + "</h1>");
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
  try {
            SystemConfigDAO dao = new SystemConfigDAO();
            ArrayList<Config> configs = dao.getAllConfig();

            for (Config c : configs) {
                String newValue = request.getParameter(c.getConfig_key());
                if (newValue != null && !newValue.equals(c.getConfig_value())) {
                    dao.updateConfig(c.getConfig_key(), newValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("systemConfig.jsp"); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
