package com.example.nxttrendz1.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
   @Id
   @Column(name = "productid")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int productId;
   @Column(name = "productname")
   private String productName;
   @Column(name = "price")
   private double price;

   public Product() {
   };

   public Product(int productId, String productName, double price) {
      this.productId = productId;
      this.productName = productName;
      this.price = price;
   }

   public int getProductId() {
      return productId;
   }

   public void setproductId(int productId) {
      this.productId = productId;
   }

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }
}