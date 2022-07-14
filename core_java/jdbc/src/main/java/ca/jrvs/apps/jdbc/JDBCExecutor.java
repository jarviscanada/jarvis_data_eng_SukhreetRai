package main.java.ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {

  public static void main(String[] args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager(
        "localhost",
        "hplussport",
        "postgres",
        "password");

    try {
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);

      Customer customer = new Customer();
      customer.setFirstName("Sukhreet");
      customer.setLastName("Rai");
      customer.setEmail("rai@jrvs.ca");
      customer.setAddress("101 yonge street");
      customer.setCity("Toronto");
      customer.setState("ON");
      customer.setPhone("(467) 666-9877");
      customer.setZipCode("M2L5X8");

      OrderDAO orderDAO = new OrderDAO(connection);
      Order order = orderDAO.findById(1001);
      System.out.println(order);

      //Customer dbCustomer = customerDAO.create(customer);
      //System.out.println(dbCustomer);

      //dbCustomer = customerDAO.findById(dbCustomer.getId());
     // System.out.println(dbCustomer);

      //dbCustomer.setCity("Brampton");
      //dbCustomer = customerDAO.update(dbCustomer);
      //System.out.println(dbCustomer);

      //customerDAO.delete(dbCustomer.getId());

      //Customer dbCustomer = customerDAO.create(customer);
      // System.out.println(customer);

      //customerDAO.findAllSorted(20).forEach(System.out::println);

     /* System.out.println("Paged");
      for (int i=1; i<=3;i++){
        System.out.println("Page Number: "+ i);
        customerDAO.findAllPaged(10,i).forEach(System.out::println);
      }*/


    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}