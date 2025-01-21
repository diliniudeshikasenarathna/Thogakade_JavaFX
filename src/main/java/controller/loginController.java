package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class loginController {

    @FXML
    private Label lblThogakadeEmail;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException, IOException {
        String SQL="SELECT * from users WHERE email="+"'"+txtEmail.getText()+"'";

        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL);

        if(resultSet.next()){
          User user=  new User(resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));



            if(user.getPassword().equals(txtPassword.getText())){

                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
                stage.show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Enter Correct Password").show();
            }


        }else {
            new Alert(Alert.AlertType.ERROR,"User not found!!!").show();
        }

    }

    @FXML
    void hyperlinkRegisterOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/register_form.fxml"))));
        stage.show();

    }

}
