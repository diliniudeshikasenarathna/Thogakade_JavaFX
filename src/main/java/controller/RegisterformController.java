package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.*;

public class RegisterformController {

    @FXML
    private JFXPasswordField txtCpassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws SQLException {

        String SQL="INSERT into Users (username,email,password) values (?,?,?)";


        if(txtPassword.getText().equals(txtCpassword.getText())){

            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users WHERE email="+"'"+txtEmail.getText()+"'");
            if(!resultSet.next()){

                String password=txtPassword.getText();
                String key= "5129#hCi";
                BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                basicTextEncryptor.setPassword(key);



                User user= new User(txtUserName.getText(),txtEmail.getText(),txtPassword.getText());
                PreparedStatement psTm = connection.prepareStatement(SQL);
                psTm.setString(1,user.getName());
                psTm.setString(2,user.getEmail());
                psTm.setString(3,basicTextEncryptor.encrypt(user.getPassword()));
                psTm.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION,"Registration Succesfully!!!").show();
                txtUserName.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                txtCpassword.setText("");


            }else{
                new Alert(Alert.AlertType.ERROR,"Email already have account!!!!").show();
                txtEmail.setText("");
                txtPassword.setText("");
                txtCpassword.setText("");
            }
        }else{

            new Alert(Alert.AlertType.ERROR,"Re enter password!!!").show();
            txtCpassword.setText("");
        }












    }

}
