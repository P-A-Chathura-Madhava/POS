package controller;

import javafx.scene.control.TextField;

public class OrderReportFormController {
    public TextField txtOrderId;

    public void setOrderId(String id){
        txtOrderId.setText(id);
    }
}
