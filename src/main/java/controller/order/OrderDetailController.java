package controller.order;

import db.DBConnection;
import model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {

    public boolean addOrderDetails(List<OrderDetails> orderdetails){
        for(OrderDetails orderDetail:orderdetails){
          Boolean isAddOrderDetails=  addOrderDetails(orderDetail);
          if(!isAddOrderDetails){
              return false;
          }
        }
        return true;
    }

    public boolean addOrderDetails(OrderDetails orderDetail){
        String SQL= "INSERT into orderdetail VALUES(?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,orderDetail.getOrderId());
            psTm.setObject(2,orderDetail.getItemCode());
            psTm.setObject(3,orderDetail.getQty());
            psTm.setObject(4,orderDetail.getPrice());
            return  psTm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
