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
import jakarta.servlet.http.HttpSession;
import model.Customer;

/**
 *
 * @author Admin
 */
public class ProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Customer cus = (Customer) sesion.getAttribute("customer");
        if (cus != null) {
            request.setAttribute("cid", cus.getId());
            request.setAttribute("name", cus.getFullName());
            request.setAttribute("user", cus.getUsername());
            request.setAttribute("phone", cus.getPhoneNumber());
            request.setAttribute("address", cus.getAddress());
            request.setAttribute("email", cus.getEmail());
            request.getRequestDispatcher("editcustomerinfo.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }

        // processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int cid = Integer.parseInt(request.getParameter("cid"));

        DAOAdmin dao = new DAOAdmin();
        boolean check = dao.updateCustomer(name, user, phone, address, email, cid);
        if (check == false) {
            request.setAttribute("cid", cid);
            request.setAttribute("name", name);
            request.setAttribute("user", user);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("mess", "Update Failed, email already exist!");
        } else {
            request.setAttribute("cid", cid);
            request.setAttribute("name", name);
            request.setAttribute("user", user);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("email", email);
            request.setAttribute("mess", "Update Success!");

        }
        HttpSession sesion = request.getSession();
        Customer cus = (Customer) sesion.getAttribute("customer");
        cus.setFullName(name);
        cus.setUsername(user);
        cus.setPhoneNumber(phone);
        cus.setAddress(address);
        cus.setEmail(email);
        System.out.println(cus.toString());
        sesion.setAttribute("customer", cus);
        request.getRequestDispatcher("editcustomerinfo.jsp").forward(request, response);
        //processRequest(request, response);
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
