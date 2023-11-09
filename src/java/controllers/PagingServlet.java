/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAO;
import dal.DAOFunction;
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
import model.Paging;
import model.Product;

/**
 *
 * @author Admin
 */
public class PagingServlet extends HttpServlet {

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
            out.println("<title>Servlet PagingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PagingServlet at " + request.getContextPath() + "</h1>");
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
    private int[] nrppArr = {3, 5, 7, 10, 15, 20, 30, 50, 100, 200, 500};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO d = new DAO();

        List<Category> cateList = d.getAllCategory();
        request.setAttribute("allCateList", cateList);
        HttpSession session = request.getSession();
        Customer ce = (Customer) session.getAttribute("customer");
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

        String cateName = request.getParameter("cateName") == null ? "All" : request.getParameter("cateName");
        String search = request.getParameter("searchProduct") == null ? "" : request.getParameter("searchProduct");
        String[] typePrice = request.getParameterValues("price");
        String sortType = request.getParameter("sort") == null ? "" : request.getParameter("sort");
        DAOFunction dao3 = new DAOFunction();

        request.setAttribute("cateName", cateName);
        request.setAttribute("searchProduct", search);
        request.setAttribute("sort", sortType);
//        if (typePrice != null && typePrice.length > 0) {
//            request.setAttribute("price", typePrice[0]);
//        }
        List<Product> productByCategory = null;

        if (true) {
            productByCategory = dao3.searchProduct(typePrice, cateName, search, sortType);
            request.setAttribute("proBC", productByCategory);
            request.setAttribute("searchName", cateName);
        }

        int nrpp = 3;
        if (request.getParameter("nrpp_f") != null) {
            nrpp = Integer.parseInt(request.getParameter("nrpp_f"));
            request.setAttribute("nrpp_t", nrpp);
        }
        int index = -1;

        //get index from post
        if (request.getAttribute("index") != null) {
            index = (int) request.getAttribute("index");
            request.setAttribute("success", "get success + " + index);
        }

        Paging p = new Paging(nrpp, index, productByCategory.size());
//        System.out.println("Size la " + productByCategory.size());
        p.calc();
        request.setAttribute("page", p);
        request.setAttribute("nrppArr", nrppArr);
        request.getRequestDispatcher("shop.jsp").forward(request, response);

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

        // inline
        int index = Integer.parseInt(request.getParameter("index"));
        int totalPage = Integer.parseInt(request.getParameter("totalPage"));

        if (request.getParameter("btnHome") != null) {
            index = 0;
        }
        if (request.getParameter("btnEnd") != null) {
            index = totalPage;
        }
        if (request.getParameter("btnPre") != null) {
            index -= 1;
        }
        if (request.getParameter("btnNext") != null) {
            index += 1;
        }

        for (int i = 0; i < totalPage; i++) {
            if (request.getParameter("btn" + i) != null) {
                index = i;
            }
        }
        request.setAttribute("index", index);
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
