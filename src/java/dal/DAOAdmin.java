/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Admin
 */
public class DAOAdmin extends DBContext {

    public boolean insertCustomer(String full_name,
            String username, String password, String phone_number, String address, int role, String email) {
        if (checkUserName(username) == false || checkEmail(email) == false) {
            return false;
        }

        String sql = " INSERT INTO customerHE172223 ([full_name] ,[username] ,[password] ,[phone_number] ,[address] ,[role] ,[email]) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, full_name);
            stm.setString(2, username);
            stm.setString(3, password);
            stm.setString(4, phone_number);
            stm.setString(5, address);
            stm.setInt(6, role);
            stm.setString(7, email);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkUserName(String username) {
        String sql = " SELECT *  FROM [dbo].[customerHE172223] where [username] = ? ";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                System.out.println("Loi tai username");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkEmail(String email) {
        String sql = " SELECT *  FROM [dbo].[customerHE172223] where [email]  = ? ";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                System.out.println("Loi tai email");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean insertCategory(String cate_name, String cate_img) {
        String sql = " INSERT INTO categoryHE172223 ( category_name, category_img) "
                + " VALUES ( ?, ?) ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cate_name);
            stm.setString(2, cate_img);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertProduct(String product_name,
            String description, String image, float price, String insert_date, String cate_name) {
        String sql = " INSERT INTO productHE172223 (product_name, description, image, price, insert_date, cate_id) "
                + " VALUES ( ?, ?, ?, ?, ?, ?) ";
        String sql2 = " select category_id "
                + " from categoryHE172223 c "
                + " where c.category_name like ? ";
        int cate_id = 1;
        if (cate_name == null || cate_name.isEmpty()) {
            cate_name = "Dresses";
            cate_id = 1;
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql2);
            stm.setString(1, "%" + cate_name + "%");

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                cate_id = rs.getInt("category_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product_name);
            stm.setString(2, description);
            stm.setString(3, image);
            stm.setFloat(4, price);
            stm.setString(5, insert_date);
            stm.setInt(6, cate_id);

            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertProduct(String product_name,
            String description, String image, float price, String insert_date, int cate_id) {
        String sql = " INSERT INTO productHE172223 (product_name, description, image, price, insert_date, cate_id) "
                + " VALUES ( ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product_name);
            stm.setString(2, description);
            stm.setString(3, image);
            stm.setFloat(4, price);
            stm.setString(5, insert_date);
            stm.setInt(6, cate_id);

            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int pid) {
        String sql = " DELETE FROM [dbo].[productHE172223] WHERe product_id =? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCategory(int cid) {
        String sql = " DELETE FROM [dbo].[categoryHE172223] WHERE category_id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //rate them khi insertReview nen khong co
    public boolean updateProduct(int pid, String name, String description, String image, float price, String date, int cateid) {
        String sql = " UPDATE [dbo].[productHE172223] SET [product_name] = ?  ,[description] = ? ,[image] = ?\n"
                + "      ,[price] = ? "
                + "      ,[insert_date] = ? "
                + "      ,[cate_id] = ? "
                //+ "      ,[rate] = ? "
                + " WHERE product_id =? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, description);
            stm.setString(3, image);
            stm.setFloat(4, price);
            stm.setString(5, date);
            stm.setInt(6, cateid);
            stm.setInt(7, pid);
            stm.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProduct(int pid, String name, String description, String image, float price, String date, String cate_name) {
        String sql = " UPDATE [dbo].[productHE172223] SET [product_name] = ?  ,[description] = ? ,[image] = ?\n"
                + "      ,[price] = ? "
                + "      ,[insert_date] = ? "
                + "      ,[cate_id] = ? "
                //+ "      ,[rate] = ? "
                + " WHERE product_id =? ";
        String sql2 = " select category_id "
                + " from categoryHE172223 c "
                + " where c.category_name like ? ";
        int cate_id = 1;
        if (cate_name == null || cate_name.isEmpty()) {
            cate_name = "Dresses";
            cate_id = 1;
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql2);
            stm.setString(1, "%" + cate_name + "%");

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                cate_id = rs.getInt("category_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, description);
            stm.setString(3, image);
            stm.setFloat(4, price);
            stm.setString(5, date);
            stm.setInt(6, cate_id);
            stm.setInt(7, pid);
            stm.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCategory(int cate_id, String cate_name, String cate_img) {
        String sql = " UPDATE [dbo].[categoryHE172223] "
                + "   SET [category_name] = ?, [category_img] = ? "
                + " WHERE category_id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cate_name);
            stm.setString(2, cate_img);
            stm.setInt(3, cate_id);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<OrderDetail> getOrderDetailByOrderId(int id) {
        List<OrderDetail> list = new ArrayList<>();
        String query = "  select * from [orderdetailHE172223] o where o.order_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderDetail o = new OrderDetail();
                o.setOrderId(id);
                o.setQuantity(rs.getInt("quantity"));
                o.setPrice(rs.getFloat("price"));
                DAO d = new DAO();
                Product p = d.getProductById(rs.getInt("product_id"));
                o.setProduct(p);

                list.add(o);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public Customer getCustomerByOrderID(int id) {
        DAO d = new DAO();
        String query = "select * from [orderHE172223] o where o.id = ?";
        int idCustomer = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                idCustomer = rs.getInt("customer_id");
            }
        } catch (Exception e) {
        }

        if (idCustomer != 0) {

            return d.getCustomerById(idCustomer);
        } else {
            return null;
        }
    }

    public boolean updateCustomer(String name, String user, String phone, String address, String email, int cid) {
        String tmp = " SELECT * FROM customerHE172223 WHERE email = ? AND customer_id <> ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(tmp);
            stm.setString(1, email);
            stm.setInt(2, cid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = " UPDATE [dbo].[customerHE172223]\n "
                + "   SET [full_name]= ? ,[username] = ? ,[phone_number] = ? ,[address] = ? ,[email] = ?  "
                + " WHERE [customer_id] = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, user);
            stm.setString(3, phone);
            stm.setString(4, address);
            stm.setString(5, email);
            stm.setInt(6, cid);
            stm.executeUpdate();
            int rowsUpdated = stm.executeUpdate();
            if (rowsUpdated > 0) {
                // Truy vấn đã thực hiện thành công
                System.out.println("Update successful for customer ID: " + cid);
                return true;
            } else {
                System.out.println("Update failed for customer ID: " + cid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
}
//   
//} public OrderDetail getOrderDetail(int pro_id, int cate_id) {
//        String sql = "SELECT [order_id] "
//                + "      ,[product_id] "
//                + "      ,[quantity] "
//                + "      ,[price] "
//                + "  FROM [dbo].[orderdetail]\n"
//                + "  where order_id =? and product_id =? "
//                + " order by insert_date desc ";
//        try {
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, pro_id);
//            stm.setInt(2, cate_id);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                int oid = rs.getInt("order_id");
//                int pid = rs.getInt("product_id");
//                Product product = new Product();
//                product.setId(pid);
//                Order o = getOrderById(oid);
//
//                int quantity = rs.getInt("quantity");
//                float price = rs.getFloat("price");
//                OrderDetail or = new OrderDetail(o, quantity, product, price);
//                return or;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Vector<OrderDetail> getAllOrderDetail(int pro_id, int cate_id) {
//        String sql = " SELECT od.order_id,p.product_id,od.quantity,od.price, "
//                + "  p.product_name,p.[description],p.[image],p.price,p.insert_date,p.cate_id, "
//                + "  o.order_date, o.customer_id,o.total_money "
//                + " FROM orderdetail od join product p on od.product_id=p.product_id "
//                + "  join [order] o on o.id=od.order_id "
//                + "  where p.product_id = ? and order_id= ? "
//                + " order by insert_date desc ";
//        try {
//            Vector<OrderDetail> list = new Vector<>();
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(2, pro_id);
//            stm.setInt(1, cate_id);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                int oid = rs.getInt("order_id");
//                int pid = rs.getInt("product_id");
//                Product product = new Product();
//                product = new DAO().getProductDetail(pid);
//                Order o = getOrderById(oid);
//
//                int quantity = rs.getInt("quantity");
//                float price = rs.getFloat("price");
//                OrderDetail or = new OrderDetail(o, quantity, product, price);
//                list.add(or);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Vector<OrderDetail> getAllOrderDetailBySearch(int cusid, String dateFrom, String dateTo) {
//        String search = "";
//        String cus_id = Integer.toString(cusid);
//        if (!(cus_id == null || cus_id.isEmpty() || cus_id.equals("0"))) {
//            search += " customer_id = " + cus_id;
//            if (dateFrom == null || dateFrom.isEmpty()) {
//                search += "and o.order_date >= '1999-01-01' ";
//            } else {
//                search += "and o.order_date >= '" + dateFrom + "' ";
//            }
//        } else {
//            if (dateFrom == null || dateFrom.isEmpty()) {
//                search += " o.order_date >= '1999-01-01' ";
//            } else {
//                search += " o.order_date >= '" + dateFrom + "' ";
//            }
//        }
//
//        if (dateTo == null || dateTo.isEmpty()) {
//            search += "and o.order_date <= '3000-12-31' ";
//        } else {
//            search += "and o.order_date <= '" + dateTo + "' ";
//        }
//        String sql = " SELECT od.order_id,p.product_id,od.quantity,od.price, "
//                + "  p.product_name,p.[description],p.[image],p.price,p.insert_date,p.cate_id, "
//                + "  o.order_date, o.customer_id,o.total_money "
//                + " FROM orderdetail od join product p on od.product_id=p.product_id "
//                + "  join [order] o on o.id=od.order_id "
//                + "  where " + search
//                + "  order by insert_date desc";
//        try {
//            Vector<OrderDetail> list = new Vector<>();
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                int oid = rs.getInt("order_id");
//                int pid = rs.getInt("product_id");
//                Product p = new Product();
//                p = new DAO().getProductDetail(pid);
//                Order o = getOrderById(oid);
//
//                int quantity = rs.getInt("quantity");
//                float price = rs.getFloat("price");
//                OrderDetail or = new OrderDetail(o, quantity, p, price);
//                list.add(or);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
