package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardFormController {
    @FXML
    public JFXButton btnLogOut;

    @FXML
    public JFXButton btnManageCustomer;

    @FXML
    public AnchorPane dashboardContext;

    @FXML
    public JFXButton btnManageItem;

    @FXML
    public JFXButton btnManageOrderDetails;

    @FXML
    public JFXButton btnManageOrder;

    @FXML
    void initialize(){
        assert btnManageCustomer != null : "fx:id=\"btnManageCustomer\" was not injected: check your FXML file 'DashboardForm.fxml'.";
        assert btnLogOut != null : "fx:id=\"btnLogOut\" was not injected: check your FXML file 'DashboardForm.fxml'.";
        assert btnManageItem != null : "fx:id=\"btnManageItem\" was not injected: check your FXML file 'DashboardForm.fxml'.";
        assert btnManageOrder != null : "fx:id=\"btnManageOrder\" was not injected: check your FXML file 'DashboardForm.fxml'.";
        assert btnManageOrderDetails != null : "fx:id=\"btnManageOrderDetails\" was not injected: check your FXML file 'DashboardForm.fxml'.";

        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/CustomerDetailsForm.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dashboardContext.getChildren().add(pane);

        // bt_product.setStyle("-fx-background-color : #6e6e6e");
        btnManageCustomer.setDisable(true);
        btnManageItem.setDisable(false);
        btnManageOrderDetails.setDisable(false);
        btnManageOrder.setDisable(false);
    }

    public void onActionLogOut(ActionEvent actionEvent) {
    }

    public void onActionManageCustomer(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/CustomerDetailsForm.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dashboardContext.getChildren().add(pane);

        // bt_product.setStyle("-fx-background-color : #6e6e6e");
        btnManageCustomer.setDisable(true);
        btnManageItem.setDisable(false);
        btnManageOrderDetails.setDisable(false);
        btnManageOrder.setDisable(false);
    }

    public void onActionManageItem(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/ItemDetailsForm.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dashboardContext.getChildren().add(pane);

        // bt_product.setStyle("-fx-background-color : #6e6e6e");
        btnManageItem.setDisable(true);
        btnManageCustomer.setDisable(false);
        btnManageOrderDetails.setDisable(false);
        btnManageOrder.setDisable(false);
    }

    public void onActionManageOrderDetails(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/OrderDetailsForm.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dashboardContext.getChildren().add(pane);

        // bt_product.setStyle("-fx-background-color : #6e6e6e");
        btnManageOrderDetails.setDisable(true);
        btnManageItem.setDisable(false);
        btnManageCustomer.setDisable(false);
        btnManageOrder.setDisable(false);
    }

    public void btnManageOrderOnAction(ActionEvent actionEvent) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dashboardContext.getChildren().add(pane);

        // bt_product.setStyle("-fx-background-color : #6e6e6e");
        btnManageOrder.setDisable(true);
        btnManageOrderDetails.setDisable(false);
        btnManageItem.setDisable(false);
        btnManageCustomer.setDisable(false);
    }
}
