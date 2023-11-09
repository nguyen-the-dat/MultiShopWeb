/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.HTMLDocument;
import model.Category;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Admin
 */
public class DAOCustomer extends DBContext {

    public Customer checkLogin(String username, String password) {
        String query = "select * from customerHE172223 c where c.username = ? and c.password = ?";
        Customer c = new Customer();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                c.setId(rs.getInt("customer_id"));
                c.setFullName(rs.getString("full_name"));
                c.setUsername(rs.getString("username"));
                c.setPhoneNumber(rs.getString("phone_number"));
                c.setAddress(rs.getString("address"));
                c.setPassword(rs.getString("password"));
                c.setRole(rs.getInt("role"));
                c.setEmail(rs.getString("email"));
                System.out.println(c);
                return c;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<OrderDetail> getCustomerOrder(int id) { // id truyen vao la customer id
        List<OrderDetail> list = new ArrayList<>();

        List<Order> orderList = getOrderByCustomerId(id); // tat ca cac order cua khach hang co ma la id
        for (Order o : orderList) {
            int orderID = o.getId();
            String query = "select * from [orderdetailHE172223] where order_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, orderID);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    OrderDetail od = new OrderDetail();

                    od.setOrderId(rs.getInt("order_id"));
                    int product_id = rs.getInt("product_id");
                    od.setProduct(getProductById(product_id));
                    od.setPrice(rs.getFloat("price"));
                    od.setQuantity(rs.getInt("quantity"));
                    list.add(od);
                }

            } catch (Exception e) {
            }
        }

        return list;
    }

    private Product getProductById(int id) {
        String query = "select * from [productHE172223] p  where p.product_id = ? ";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setDescrip(rs.getString("description"));
                p.setImage(rs.getString("image"));

                p.setPrice(rs.getFloat("price"));
                p.setInsertDate(rs.getDate("insert_date"));
                int ide = rs.getInt("cate_id");
                p.setCategory(getCategoryById(ide));
                p.setRate(rs.getInt("rate"));

                return p;
            }
        } catch (Exception e) {
        }

        return null;
    }

    private Category getCategoryById(int id) {
        String query = "select * from categoryHE172223 where category_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("category_name"));
                c.setImage(rs.getString("category_img"));
                return c;
            }
        } catch (Exception e) {
        }

        return null;
    }

    // lay ra tat ca cac order cua id
    public List<Order> getOrderByCustomerId(int id) {
        List<Order> list = new ArrayList<>();
        String query = "select * from [orderHE172223] o where o.customer_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setCusid(rs.getInt("customer_id"));
                o.setDate(rs.getDate("order_date"));
                o.setTotalMoney(rs.getFloat("total_money"));
                list.add(o);
            }
        } catch (SQLException e) {
//            System.out.println(e);
        }

        return list;
    }

    public void updatePassword(String password, String email) {
        String query = "UPDATE [customerHE172223] SET password = ? WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        // updatePassword("12")
    }
}
