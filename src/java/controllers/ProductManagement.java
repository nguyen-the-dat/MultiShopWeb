/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Paging;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductManagement extends HttpServlet {

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
            out.println("<title>Servlet ProductManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManagement at " + request.getContextPath() + "</h1>");
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
        String cateName = request.getParameter("cateName");
        List<Product> tmp = new ArrayList<>();
        if (cateName != null && cateName != "" && !cateName.equals("All")) {
            List<Product> li1 = d.getProductByCategoryName(cateName);
            tmp = li1;
            request.setAttribute("proBC", li1);
            request.setAttribute("searchName", cateName);
        } else {
            List<Product> list = d.getAllProduct();
            tmp = list;
            request.setAttribute("searchName", "All");
            request.setAttribute("proBC", list);
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

        Paging p = new Paging(nrpp, index, tmp.size());
//        System.out.println("Size la " + productByCategory.size());
        p.calc();
        request.setAttribute("page", p);
        request.setAttribute("nrppArr", nrppArr);
        request.getRequestDispatcher("detaillistproductadmin.jsp").forward(request, response);
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
