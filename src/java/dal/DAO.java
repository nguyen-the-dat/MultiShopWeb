/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.HTMLDocument;
import model.Cart;
import model.Category;
import model.Customer;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.Review;

/**
 *
 * @author Admin
 */
public class DAO extends DBContext {

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from categoryHE172223";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("category_name"));
                c.setImage(rs.getString("category_img"));
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM productHE172223 ORDER BY insert_date DESC";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setDescrip(rs.getString("description"));
                p.setImage(rs.getString("image"));

                p.setPrice(rs.getFloat("price"));
                p.setInsertDate(rs.getDate("insert_date"));
                int id = rs.getInt("cate_id");
                p.setCategory(getCategoryById(id));
                p.setRate(rs.getInt("rate"));

                list.add(p);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> getProductByCategoryName(String name) {
        List<Product> list = new ArrayList<>();
        String query = "select * from productHE172223 p inner join category c on p.cate_id = c.category_id where c.category_name = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setDescrip(rs.getString("description"));
                p.setImage(rs.getString("image"));

                p.setPrice(rs.getFloat("price"));
                p.setInsertDate(rs.getDate("insert_date"));
                int id = rs.getInt("cate_id");
                p.setCategory(getCategoryById(id));
                p.setRate(rs.getInt("rate"));

                list.add(p);

            }
        } catch (Exception e) {
        }

        return list;
    }

    public Category getCategoryById(int id) {
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

//    public static void main(String[] args) {
//        DAO d = new DAO();
//        List<Product> list = d.getProductByCategoryId("Cosmetics");
//        System.out.println(list.size());
//    }
    public void addOrder(Customer c, Cart cart) {
        System.out.println(c);
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            // thuc hien update o bang order
            String sql = "insert into [orderHE172223] values (?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, c.getId());
            st.setFloat(3, cart.getTotalMoney());
            st.executeUpdate();

            // lay id cua order vua dd
            String sql1 = "select top 1 id from [orderHE172223] order by id desc"; // lay ra dong dau tien cua bang [Order] co gia tri id lon nhat

            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            // add vao bang order detail
            if (rs.next()) {
                int oid = rs.getInt("id"); // oid : gia tri order_id
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into [orderdetailHE172223] values (?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getId());
                    st2.setInt(3, i.getQuantity());
                    st2.setFloat(4, i.getPrice());
                    st2.executeUpdate();

                }
            }

        } catch (Exception e) {
        }
    }

    public List<Product> getFeaturedProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * \n"
                + "FROM (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY p.rate DESC) as rn\n"
                + "    FROM [productHE172223] p\n"
                + ") as ranked\n"
                + "WHERE rn <= 8;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setDescrip(rs.getString("description"));
                p.setImage(rs.getString("image"));

                p.setPrice(rs.getFloat("price"));
                p.setInsertDate(rs.getDate("insert_date"));
                int id = rs.getInt("cate_id");
                p.setCategory(getCategoryById(id));
                p.setRate(rs.getInt("rate"));

                list.add(p);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Product> getRecentProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * \n"
                + "FROM (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY p.insert_date DESC) as rn\n"
                + "    FROM [productHE172223] p\n"
                + ") as ranked\n"
                + "WHERE rn <= 8;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setDescrip(rs.getString("description"));
                p.setImage(rs.getString("image"));

                p.setPrice(rs.getFloat("price"));
                p.setInsertDate(rs.getDate("insert_date"));
                int id = rs.getInt("cate_id");
                p.setCategory(getCategoryById(id));
                p.setRate(rs.getInt("rate"));

                list.add(p);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [orderHE172223] o order by o.id desc";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                /*
                  private int id;
    private String date;
    private int cusid;
    private double totalMoney;
                 */
                order.setId(rs.getInt("id"));
                order.setCusid(rs.getInt("customer_id"));
                order.setTotalMoney(rs.getFloat("total_money"));
                order.setDate((rs.getDate("order_date")));
                list.add(order);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<OrderDetail> getAllOrderDetail() { // id truyen vao la customer id
        List<OrderDetail> list = new ArrayList<>();

        String query = "select * from [orderdetailHE172223]";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

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

        return list;
    }

    public Product getProductById(int id) {
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

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        String query = "select * from [customerHE172223]";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customer_id"));
                c.setFullName(rs.getString("full_name"));
                c.setAddress(rs.getString("address"));
                c.setEmail(rs.getString("email"));
                list.add(c);

            }
        } catch (Exception e) {
        }

        return list;
    }

    public List<Review> getReviewByProductId(int id) {
        List<Review> list = new ArrayList<>();
        String query = "select r.comment, p.product_id, r.cus_id, r.comment_date\n"
                + "from [productHE172223] p inner join \n"
                + "[reviewHE172223] r on p.product_id = r.pro_id\n"
                + "where p.product_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setCustomer(getCustomerById(rs.getInt("cus_id")));
                review.setComment(rs.getString("comment"));
                review.setDate(rs.getDate("comment_date"));
                review.setProduct(getProductById(rs.getInt("product_id")));
                list.add(review);
            }

        } catch (Exception e) {
        }

        return list;
    }

//    public static void main(String[] args) {
//        DAO d = new DAO();
//        System.out.println(d.getReviewByProductId(1));
//    }
    public Customer getCustomerById(int id) {
        String query = "select * from [customerHE172223] c where c.customer_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(id);
                c.setFullName(rs.getString("full_name"));
                c.setPhoneNumber(rs.getString("phone_number"));
                c.setAddress(rs.getString("address"));
                c.setRole(rs.getInt("role"));
                c.setEmail(rs.getString("email"));
                c.setUsername(rs.getString("username"));
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void insertReview(Review r) {
        String query = "insert into [reviewHE172223] values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, r.getProduct().getId());
            preparedStatement.setInt(2, r.getCustomer().getId());
            preparedStatement.setString(3, r.getComment());
            preparedStatement.setDate(4, r.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
//    public static void main(String[] args) {
//        DAO d = new DAO();
//        System.out.println(d.getFeaturedProducts().size());
//    }
}
