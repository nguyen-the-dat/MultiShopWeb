/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.CookieStore;
import java.util.List;
import model.Cart;
import model.Category;
import model.CookieUtils;
import model.Customer;
import model.Product;

/**
 *
 * @author Admin
 */
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
        DAO d = new DAO();
        List<Product> list = d.getAllProduct();
        String[] buy_raw = request.getParameterValues("buy");
        // doc lai cookie cu
        Cookie[] arr = request.getCookies();
        String txt = "";
        HttpSession session = request.getSession();

        // check xem da dang nhap hay chua
        Customer c = (Customer) session.getAttribute("customer");
        if (c == null) {
            response.sendRedirect("login.jsp");

        }

        String id = (c == null) ? "" : (c.getId() + "");

        if (arr != null) {
            // doc lai het cookie ra
            for (Cookie o : arr) {
                if (o.getName().equals("cart" + id)) {

                    txt += CookieUtils.decode(o.getValue());
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }

        // chinh sua lai string txt : giu lai cai nao khong mua thoi, cai nao mua, chuyen sang String buy
        String buy = "";
        String[] tmp = txt.split(",");
        txt = "";
        for (int i = 0; i < tmp.length; i++) {
            boolean check = false;
            String[] b = tmp[i].split(":");
            String b_id = b[0];
            String b_quantity = b[1];
            for (String j : buy_raw) {
                if (j.equals(b_id)) { // san pham duoc mua
                    check = true;
                    break;
                }
            }
            if (check) {
                if (buy == "") {
                    buy += b_id + ":" + b_quantity;
                } else {
                    buy += "," + b_id + ":" + b_quantity;
                }
            } else {
                if (txt == "") {
                    txt += b_id + ":" + b_quantity;
                } else {
                    txt += "," + b_id + ":" + b_quantity;
                }
            }
        }

        System.out.println("buy la: " + buy);
        System.out.println("txt con lai la: " + txt);
        Cart cart = new Cart(buy, list);

        if (c == null) { // chua dang nhap thi chuyen vao trang login
            response.sendRedirect("login.jsp");
        } else {
            d.addOrder(c, cart);

            Cookie co = new Cookie("cart" + id, "");
            co.setMaxAge(0);
            Cookie cookie = new Cookie("cart" + id, CookieUtils.encode(txt));
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);
          
            response.sendRedirect("home");
        }
      
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
