/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAO;
import dal.DAOCustomer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import model.Cart;
import model.Category;
import model.CookieUtils;
import model.Customer;
import model.Item;
import model.OrderDetail;
import model.Product;
import model.Review;

/**
 *
 * @author Admin
 */
public class DetailProduct extends HttpServlet {

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
            out.println("<title>Servlet DetailProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailProduct at " + request.getContextPath() + "</h1>");
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
        List<Category> cateList = d.getAllCategory();
        request.setAttribute("allCateList", cateList);

        HttpSession session = request.getSession();
        Customer ce = (Customer) session.getAttribute("customer");
        request.setAttribute("customer", ce);
        int n = 0;

        String id = (ce == null) ? "" : (ce.getId() + "");
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart" + id)) {

                    txt += CookieUtils.decode(o.getValue());
                }
            }
        }
        List<Product> list = d.getAllProduct();
        Cart cart = new Cart(txt, list); // list : danh sach cac product cua cua hang
        // danh sach gio hang cua mot user
        List<Item> listItem = cart.getItems();
        n = listItem.size();
        request.setAttribute("noo", n);

        int ide = Integer.parseInt(request.getParameter("pId"));

        Product p = d.getProductById(ide);

        request.setAttribute("productInfo", p);
        List<Review> reviewList = d.getReviewByProductId(ide);
        request.setAttribute("rList", reviewList);
        request.getRequestDispatcher("detail.jsp").forward(request, response);

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
        String review = request.getParameter("review");
        int productId = Integer.parseInt(request.getParameter("pId"));
        HttpSession session = request.getSession();
        Customer c = (Customer) session.getAttribute("customer");
        if (c == null) {
            request.setAttribute("error", "Please login to review about this product.");
            doGet(request, response);
        }
        if (c != null) {
            boolean isBuy = false;
            DAOCustomer dc = new DAOCustomer();
            List<OrderDetail> li = dc.getCustomerOrder(c.getId());
            for (OrderDetail o : li) {
                if (o.getProduct().getId() == productId) { // khach hang da tung mua san pham day
                    isBuy = true; // da mua
                    break;
                }
            }

            if (isBuy) { // da tung mua
                // kiem tra xem da tung comment hay chua
                DAO d = new DAO();
                List<Review> li2 = d.getReviewByProductId(productId);
                boolean isReview = false;
                String rew = "";
                for (Review v : li2) {
                    if (v.getCustomer().getId() == c.getId() && v.getProduct().getId() == productId) {
                        // da reivew
                        isReview = true;
                        rew = v.getComment();
                        break;
                    }
                }
                if (isReview) { // da tung danh gia
                    request.setAttribute("myreview", rew);
                    doGet(request, response);
                } else {
// chua tung danh gia ve san phamf
                    java.sql.Date sqlDate = java.sql.Date.valueOf(java.time.LocalDate.now());
                    Product p = d.getProductById(productId);
                    Review r = new Review(p, c, sqlDate, review);// Product product, Customer customer, Date date, String comment)
                    System.out.println(r);
                    d.insertReview(r);
                    doGet(request, response);
                }

            } else { // chua mua
                request.setAttribute("error", "Please buy this product to experience and review!");
                doGet(request, response);
            }
        }

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
