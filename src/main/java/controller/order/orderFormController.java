package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.item.ItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class orderFormController implements Initializable {

    @FXML
    private JFXComboBox cmbCustomerID;

    @FXML
    private JFXComboBox cmbItemCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colQTYOnHand;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblTime;

    @FXML
    private TableView tblOrders;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;

    ObservableList<CartTM> cartList= FXCollections.observableArrayList();
    @FXML
    void btnAddToCarOnAction(ActionEvent event) {

        loadTable();
        getNetTotal();

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        String date = lblDate.getText();
        String cusId = cmbCustomerID.getValue().toString();
        List<OrderDetails> orderDetailList = new ArrayList<>();

        cartList.forEach(cartlist->{
            orderDetailList.add(new OrderDetails(orderId,cartlist.getItemCode(),cartlist.getQtyOnHand(),cartlist.getUnitPrice()));
        });

        Order order = new Order(orderId, date, cusId, orderDetailList);
     boolean b=new OrderController().placeOrder(order);
     if (b){
         new Alert(Alert.AlertType.INFORMATION,"Order Placed Successfully!!!").show();
     }else{
         new Alert(Alert.AlertType.ERROR,"Order Not Placed Successfully!!!").show();
     }


    }
    private void setDateAndTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        lblDate.setText(format);
        /// --------------------------------------

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,e ->{
            LocalTime now=LocalTime.now();
            lblTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTYOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        setDateAndTime();
        loadCustomerId();
        loadItemCode();

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null){
                searchCustomerDetails(newValue.toString());
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            if(newValue!=null){
                searchItemDetails(newValue.toString());
            }
        } );

    }

    private void searchItemDetails(String itemCode) {
      Item item= new ItemController().searchItem(itemCode);

        txtDescription.setText(item.getDescription());
        txtStock.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(item.getUnitPrice().toString());
    }

    private void searchCustomerDetails(String customerId) {
       Customer customer= new CustomerController().searchCustomer(customerId);

        txtCustomerName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
    }

    public void loadCustomerId(){

        cmbCustomerID.setItems(new CustomerController().getCustomerId());
    }

    public void loadItemCode(){
        cmbItemCode.setItems(new ItemController().getItemIds());
    }

    public void loadTable(){


        String code = cmbItemCode.getValue().toString();
        String description=txtDescription.getText();
        Integer qty= Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Double total=0.0;

       cartList.add( new CartTM(code,description,qty,unitPrice,qty*unitPrice));
        tblOrders.setItems(cartList);

    }
    public void getNetTotal(){
        Double netTotal=0.0;
        for(CartTM tm: cartList){
            netTotal+=tm.getTotal();
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

}
