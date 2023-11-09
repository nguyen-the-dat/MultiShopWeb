/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Product {

    private int id;
    private String name, descrip, image;
    private float price;
    private Date insertDate;
    private Category category;
    private int rate;

    public Product() {
    }

    public Product(int id, String name, String descrip, String image, float price, Date insertDate, Category category, int rate) {
        this.id = id;
        this.name = name;
        this.descrip = descrip;
        this.image = image;
        this.price = price;
        this.insertDate = insertDate;
        this.category = category;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", descrip=" + descrip + ", images=" + image + ", price=" + price + ", insertDate=" + insertDate + ", category=" + category + ", rate=" + rate + '}';
    }

}
