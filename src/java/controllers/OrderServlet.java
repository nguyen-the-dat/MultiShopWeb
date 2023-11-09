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
import java.util.List;
import model.Cart;
import model.Category;
import model.CookieUtils;
import model.Customer;
import model.Item;
import model.Product;

/**
 *
 * @author Admin
 */
public class OrderServlet extends HttpServlet {

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
            out.println("<title>Servlet OrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Customer cu = (Customer) session.getAttribute("customer");
        if (cu != null) { // da login
            DAO d = new DAO();
            List<Category> cateList = d.getAllCategory();

            request.setAttribute("allCateList", cateList);

//            Customer ce = (Customer) session.getAttribute("customer");
//           
            String ide = (cu == null) ? "" : (cu.getId() + "");
            // doc lai cookie cu
            Cookie[] arr = request.getCookies();
            String txt = "";
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart" + ide)) {
                      
                        txt += CookieUtils.decode(o.getValue());
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }

            String num = request.getParameter("num");
            String id = request.getParameter("id");
            if (txt.isEmpty()) { // chua mua bat ki san pham nao
                txt = id + ":" + num;
//                txt = cu.getId() + "$" + id + ":" + num;

            } else {
                txt = txt + "," + id + ":" + num;
            }

            //    Cookie c = new Cookie("cart", txt);
            Cookie c = new Cookie("cart" + ide, CookieUtils.encode(txt));
            c.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(c);

            request.getRequestDispatcher("home").forward(request, response);

        } else {
            response.sendRedirect("login.jsp");
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
        String newValue = request.getParameter("newValue");
        String id_raw = request.getParameter("id");

        DAO d = new DAO();
        List<Product> list = d.getAllProduct();
        // doc lai cookie cu
        Cookie[] arr = request.getCookies();
        HttpSession session = request.getSession();
        Customer cu = (Customer) session.getAttribute("customer");
        String ide = (cu == null) ? "" : (cu.getId() + "");
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart" + ide)) {

                    txt += CookieUtils.decode(o.getValue());
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        System.out.println(txt);
        Cart cart = new Cart(txt, list); // lay ra gio hang

        try {
            int id = Integer.parseInt(id_raw);
            int val = Integer.parseInt(newValue);
            if (val == 0) { // remove
                cart.removeItem(id);

            } else {
                // cap nhat lai
                Item item = cart.getItemById(id);
                item.setQuantity(val);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        List<Item> items = cart.getItems(); // lay ra tat ca cac item trong gio hang
        txt = "";
        if (items.size() > 0) {
            txt = items.get(0).getProduct().getId() + ":" + items.get(0).getQuantity();
            for (int i = 1; i < items.size(); i++) {
                txt += "," + items.get(i).getProduct().getId() + ":" + items.get(i).getQuantity();
            }
        }
        System.out.println(txt);
        Cookie c = new Cookie("cart" + ide, CookieUtils.encode(txt));
        c.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(c);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("show").forward(request, response);
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
