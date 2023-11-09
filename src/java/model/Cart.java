/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart { // mo phong gio hang

    private List<Item> items; // co nhieu san pham

    public Cart() { // khoi tao danh sach luu cac san pham minh dat
        items = new ArrayList<>();
    }

    public List<Item> getItems() { // tra ve full tat ca cac san pham kem so luong trong gio hang cua minh
        return items;
    }

    public int getQuantityById(int id) { // tra ve so luong san pham trong gio hang
        return getItemById(id).getQuantity();
    }

    public void addItem(Item t) {
        if (getItemById(t.getProduct().getId()) != null) { // da co san pham day trong gio hang
            Item m = getItemById(t.getProduct().getId());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public Item getItemById(int id) { // id nay cung chinh la id cua san pham trong gio hang
        for (Item i : items) {
            if (i.getProduct().getId() == id) {
                return i;
            }
        }
        return null;
    }

    public void removeItem(int id) { // xoa 1 san pham khoi gio hang
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public float getTotalMoney() { // tinh total tien khi mua tat ca cac san pham trong gio hang
        float t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * i.getPrice());
        }

        return t;
    }

    public Cart(String txt, List<Product> list) {
        items = new ArrayList<>();

        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split(",");
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    Product p = getProductById(id, list);
                    Item t = new Item(p, quantity, p.getPrice());
                    addItem(t);
                }
            }
        } catch (Exception e) {
        }

    }

    private Product getProductById(int id, List<Product> list) {
        for (Product i : list) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Cart t = new Cart();
        for (int i = 0; i < 10; i++) {
            System.out.println(t.items.get(i).getProduct());
        }
    }
}
