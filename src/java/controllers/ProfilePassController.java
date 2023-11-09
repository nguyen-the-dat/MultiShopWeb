/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAOCustomer;
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
public class ProfilePassController extends HttpServlet {

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
            out.println("<title>Servlet ProfilePassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfilePassController at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("passc", cus.getPassword());
            request.setAttribute("email", cus.getEmail());
            request.getRequestDispatcher("editcustomerpass.jsp").forward(request, response);
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
        String pass = request.getParameter("pass") == null ? "" : request.getParameter("pass");
        String passc = request.getParameter("passc") == null ? "" : request.getParameter("passc");
        String newpass = request.getParameter("newpass") == null ? "" : request.getParameter("newpass");
        String check = request.getParameter("check") == null ? "" : request.getParameter("check");

        String email = request.getParameter("email");
        DAOCustomer dao = new DAOCustomer();

//        DAOAdmin dao = new DAOAdmin();
//        boolean check = dao.updateCustomer(name, user, phone, address, email, cid);
//        request.setAttribute("cid", cid);
//        request.setAttribute("name", name);
//        request.setAttribute("user", user);
//        request.setAttribute("phone", phone);
//        request.setAttribute("address", address);
//        request.setAttribute("email", email);
//        request.setAttribute("mess", "Update Success!");
        if (pass.isEmpty() || newpass.isEmpty() || check.isEmpty()) {
            request.setAttribute("mess", "You must fill all input!");
            request.setAttribute("passc", passc);
            request.setAttribute("email", email);

        } else if (passc.compareTo(pass) != 0) {
            request.setAttribute("mess", "Wrong password");
            request.setAttribute("passc", passc);
            request.setAttribute("email", email);

        } else if (!(check.equals(newpass))) {
            request.setAttribute("passc", passc);
            request.setAttribute("email", email);
            request.setAttribute("mess", "Update Failed, confirm pass correct pls!");
        } else {
            request.setAttribute("pass", newpass);
            request.setAttribute("passc", passc);
            request.setAttribute("email", email);
            request.setAttribute("mess", "Update Success!");
            dao.updatePassword(newpass, email);

        }

        request.getRequestDispatcher("editcustomerpass.jsp").forward(request, response);
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
