package main.java.ca.jrvs.apps.jdbc;

import main.java.ca.jrvs.apps.jdbc.util.DataTransferObject;

public class OrderItem implements DataTransferObject {
  private long orderItemID;
  private int orderQuantity;
  private String productName;
  private int productSize;
  private double productPrice;
  private String productCode;
  private String productVariety;

  @Override
  public long getId() {
    return orderItemID;
  }

  public long getOrderItemID() {
    return orderItemID;
  }

  public void setOrderItemID(long orderItemID) {
    this.orderItemID = orderItemID;
  }

  public int getOrderQuantity() {
    return orderQuantity;
  }

  public void setOrderQuantity(int orderQuantity) {
    this.orderQuantity = orderQuantity;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getProductSize() {
    return productSize;
  }

  public void setProductSize(int productSize) {
    this.productSize = productSize;
  }

  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductVariety() {
    return productVariety;
  }

  public void setProductVariety(String productVariety) {
    this.productVariety = productVariety;
  }

  @Override
  public String toString() {
    return "OrderItem{" +
        "orderItemID=" + orderItemID +
        ", orderQuantity=" + orderQuantity +
        ", productName='" + productName + '\'' +
        ", productSize=" + productSize +
        ", productPrice=" + productPrice +
        ", productCode='" + productCode + '\'' +
        ", productVariety='" + productVariety + '\'' +
        '}';
  }
}
