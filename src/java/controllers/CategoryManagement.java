/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.DAO;
import dal.DAOAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
public class CategoryManagement extends HttpServlet {

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
            out.println("<title>Servlet CategoryManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryManagement at " + request.getContextPath() + "</h1>");
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
        DAO dao = new DAO();
        String action = request.getParameter("action");
        if (action != null && action != "") {
            // form update
            if (action.equals("update")) {

                if (request.getParameter("cId") != null) {
                    int cId = Integer.parseInt(request.getParameter("cId"));

                    Category c = dao.getCategoryById(cId);
                    request.setAttribute("cateUpdate", c);
                }

            } else if (action.equals("add")) {
                request.setAttribute("add", dao);
            } else if (action.equals("delete")) {
                DAOAdmin d = new DAOAdmin();
                if (request.getParameter("cId") != null) {
                    int cId = Integer.parseInt(request.getParameter("cId"));

                    d.deleteCategory(cId);

                }
            }

        }
        List<Category> cList = dao.getAllCategory();
        request.setAttribute("cateList", cList);

        request.getRequestDispatcher("category.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        DAOAdmin d = new DAOAdmin();

        if (action != null && action != "") {
            switch (action) {
                case "add":
                    String name = request.getParameter("name");
                    String img = request.getParameter("image");

                    if (name != null && img != null) {

                        d.insertCategory(name, "images/" + img);
                        doGet(request, response);

                    }
                    break;
                case "update":
                    String nameU = request.getParameter("name");
                    String imgU = request.getParameter("image");
                    int id = Integer.parseInt(request.getParameter("id"));
                    if (nameU != null && nameU != "" && imgU != null && imgU != "") {
                        // thuc hien update
                        d.updateCategory(id, nameU, "images/" + imgU);
                        doGet(request, response);
                    }
                    break;
                case "delete":

                    break;
            }
        }

    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
