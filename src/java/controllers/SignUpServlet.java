/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dal.DAOAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SignUpServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignUpServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name") == null ? "" : request.getParameter("name");
        String user = request.getParameter("user") == null ? "" : request.getParameter("user");
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        String pass = request.getParameter("pass") == null ? "" : request.getParameter("pass");
        String phone = request.getParameter("contact") == null ? "" : request.getParameter("contact");
        String passre = request.getParameter("re_pass") == null ? "" : request.getParameter("re_pass");
        String address = request.getParameter("address") == null ? "HCM" : request.getParameter("address");
        int role = Integer.parseInt(request.getParameter("role"));
        request.setAttribute("mess", "I am already member");
        if (!(passre.equals(pass) && !passre.isEmpty())) {
            request.setAttribute("error", "The login password needs to be confirm correctly");
            request.setAttribute("email", email);
            request.setAttribute("name", name);
            request.setAttribute("user", user);
            request.setAttribute("pass", pass);
            request.setAttribute("address", address);
            request.setAttribute("contact", phone);
        } else {
            DAOAdmin dao = new DAOAdmin();
            boolean check = dao.insertCustomer(name, user, passre, phone, address, role,email);
            
          
            if (check == false) {
                request.setAttribute("error", "User name or email has already exist");
                request.setAttribute("name", name);
                request.setAttribute("pass", pass);
                request.setAttribute("re_pass", passre);
                request.setAttribute("address", address);
                request.setAttribute("contact", phone);
            }else{
                request.setAttribute("error", "");
                request.setAttribute("mess", "Succed sign up! Return to hompage here.");
            }
        }
        request.getRequestDispatcher("registration.jsp").forward(request, response);
        //processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
