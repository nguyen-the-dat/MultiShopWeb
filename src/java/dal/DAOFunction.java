/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import model.*;

/**
 *
 * @author ADMIN
 */
public class DAOFunction extends DBContext {

    public Vector<Product> searchProduct(String[] typePrice, String cateName,
            String productName, String sortType) {

        String priceFrom = "", priceTo = "";
        boolean check = false;
        String priceCondition = "";

        if (typePrice != null && typePrice.length > 0) {
            for (int i = 0; i < typePrice.length; i++) {
                if (typePrice[i].equals("price-all")) {
                    check = true;
                    priceCondition = " AND price >= 0 AND price <= 99999999 ";
                    break;
                }
            }
        }
        if (check == false && typePrice != null && typePrice.length > 0) {
            priceCondition = " AND (";
            for (int i = 0; i < typePrice.length; i++) {
                String selectedPrice = typePrice[i];
                if (selectedPrice.equals("price-1")) {
                    priceCondition += " (price >= 0 AND price <= 10) ";
                } else if (selectedPrice.equals("price-2")) {
                    priceCondition += " (price >= 10 AND price <= 200) ";
                } else if (selectedPrice.equals("price-3")) {
                    priceCondition += " (price >= 200 AND price <= 300) ";
                } else if (selectedPrice.equals("price-4")) {
                    priceCondition += " (price >= 300 AND price <= 400) ";
                } else if (selectedPrice.equals("price-5")) {
                    priceCondition += " (price >= 400 AND price <= 500) ";
                }

                if (i < typePrice.length - 1) {
                    priceCondition += " OR ";
                }
            }
            priceCondition += ")";
        }
        if (cateName.isEmpty() || cateName.equals("All")) {
            cateName = "";
        }
        if (productName == null || productName.isEmpty()) {
            productName = "";
        }
        if (sortType == null || sortType.isEmpty()) {
            sortType = "";
        }
        Vector<Product> list = new Vector<>();
        String sql = " select * from productHE172223 p join categoryHE172223 c on c.category_id=p.cate_id "
                + "  where category_name like  ? and  p.product_name like ? "
                + sortType + " " + priceCondition + " ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + cateName + "%");
            stm.setString(2, "%" + productName + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setId(rs.getInt("product_id"));
                pro.setName(rs.getString("product_name"));
                pro.setDescrip(rs.getString("description"));
                pro.setImage(rs.getString("image"));
                pro.setPrice(rs.getFloat("price"));
                pro.setInsertDate(rs.getDate("insert_date"));
                Category cate = new Category();
                cate.setId(rs.getInt("cate_id"));
                cate.setName(rs.getString("category_name"));
                cate.setImage(rs.getString("category_img"));
                pro.setCategory(cate);
                pro.setRate(rs.getInt("rate"));
                list.add(pro);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String[] ts = null;
        System.out.println(new DAOFunction().searchProduct(ts, "dresses", "n", ""));
    }
}
