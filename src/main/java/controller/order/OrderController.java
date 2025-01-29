package controller.order;

import controller.item.ItemController;
import db.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {

    public boolean placeOrder(Order order) throws SQLException {
        String SQL="INSERT into Orders VALUES (?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();

        try {

            connection.setAutoCommit(false);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,order.getId());
            psTm.setObject(2,order.getDate());
            psTm.setObject(3,order.getCustomerId());

            Boolean isOrderAdd = psTm.executeUpdate()>0;

            if(isOrderAdd){
                boolean isOrderDetailAdd = new OrderDetailController().addOrderDetails(order.getOrderDetails());

                if(isOrderDetailAdd){
                  boolean isUpdateIstock= new ItemController().updateStock(order.getOrderDetails());
                  if(isUpdateIstock){
                      connection.commit();
                      return true;
                  }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }
}


