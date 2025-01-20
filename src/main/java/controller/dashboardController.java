package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class dashboardController {

    @FXML
    private AnchorPane loadPages;

    @FXML
    void btnCustomerFormOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/customer_form.fxml");
        assert resource!=null;
        Parent load = FXMLLoader.load(resource);
        loadPages.getChildren().clear();
        loadPages.getChildren().add(load);


    }

    @FXML
    void btnItemFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnOrderFormOnAction(ActionEvent event) {

    }

}
