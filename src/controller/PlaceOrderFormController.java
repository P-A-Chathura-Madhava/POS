package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.PlaceOrderBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Or;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import view.tm.CustomerTM;
import view.tm.OrderTM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class PlaceOrderFormController {
    public JFXComboBox cmbCustomerId;
    public Label lblCustomerName;
    public Label lblCustomerAddress;
    public JFXComboBox cmbOrderCode;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public JFXButton btnAddItem;
    public JFXTextField txtQty;

    public TableView<OrderTM> tblOrders;
    public TableColumn<OrderTM, String> colCode;
    public TableColumn<OrderTM, String> colDescription;
    public TableColumn<OrderTM, Integer> colQty;
    public TableColumn<OrderTM, Double> colUnitPrice;
    public TableColumn<OrderTM, Double> colTotal;
    public JFXButton btnRemoveItem;
    public Label lblTotalCost;
    public Label lblOrderId;

    CustomerBo customerBo = BoFactory.getInstance().getBo(BoFactory.BoTypes.CUSTOMER);
    ItemBo itemBo = BoFactory.getInstance().getBo(BoFactory.BoTypes.ITEM);
    PlaceOrderBo orderBo = BoFactory.getInstance().getBo(BoFactory.BoTypes.ORDER);
    public Label lblOrderDate;

    public void initialize() throws SQLException, ClassNotFoundException {
        colCode.setCellValueFactory(new PropertyValueFactory<OrderTM, String>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<OrderTM, String>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<OrderTM, Double>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<OrderTM, Integer>("qtyOnHand"));
        colTotal.setCellValueFactory(new PropertyValueFactory<OrderTM, Double>("total"));


        loadDate();
        loadCustomerIDs();
        loadItemsCodes();
        loadOrderId();
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                try {
                    loadCustomer(newValue.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        cmbOrderCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadItemDetails(newValue.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadOrderId() throws SQLException, ClassNotFoundException {
        lblOrderId.setText(orderBo.getOrderId());
    }

    private void loadItemDetails(String code) throws SQLException, ClassNotFoundException {
        ItemDTO dto = itemBo.getItem(code);
        lblDescription.setText(dto.getDescription());
        lblUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        lblQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
    }

    private void loadItemsCodes() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> dtoList = itemBo.getAllItems();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (ItemDTO dto :
                dtoList) {
            obList.add(dto.getCode());
        }
        cmbOrderCode.setItems(obList);
    }

    private void loadCustomer(String id) throws SQLException, ClassNotFoundException {
        CustomerDTO dto = customerBo.getCustomer(id);
        lblCustomerName.setText(dto.getName());
        lblCustomerAddress.setText(dto.getAddress());
    }

    private void loadCustomerIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = customerBo.getCustomerIds();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String id :
                ids) {
            obList.add(id);
        }
        cmbCustomerId.setItems(obList);
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateText = sdf.format(date);
        lblOrderDate.setText(dateText);
    }

    public void btnAddItemOnAction(ActionEvent actionEvent) {
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        if (qty > Integer.parseInt(lblQtyOnHand.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Quantity");
            alert.show();
            return;
        }

        double total = qty * unitPrice;        ;
        String s = cmbOrderCode.getSelectionModel().selectedItemProperty().getValue().toString();

        int rowIndex = isAlreadyExists(s);
        if(rowIndex == -1){
            OrderTM orderTM = new OrderTM(
                    s,
                    lblDescription.getText(),
                    unitPrice,
                    qty,
                    total
            );
            ObservableList<OrderTM> items = tblOrders.getItems();
            items.add(orderTM);
            tblOrders.setItems(items);
        }else {
            int newQty = tblOrders.getItems().get(rowIndex).getQtyOnHand();
            newQty += Integer.parseInt(txtQty.getText());
            double newTotal = newQty * unitPrice;

            OrderTM orderTM = new OrderTM(
                    s,
                    lblDescription.getText(),
                    unitPrice,
                    newQty,
                    newTotal
            );

            if (newQty > Integer.parseInt(lblQtyOnHand.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Quantity");
                alert.show();
                return;
            }

            tblOrders.getItems().set(rowIndex, orderTM);
        }
        txtQty.clear();
        calculateTotal();
    }

    public void btnRemoveItemOnAction(ActionEvent actionEvent) {
        int selectedCode = tblOrders.getSelectionModel().getSelectedIndex();
        tblOrders.getItems().remove(selectedCode);
        calculateTotal();
    }

    private int isAlreadyExists(String itemCode){
        for (int i = 0; i < tblOrders.getItems().size(); i++) {
            String code = tblOrders.getItems().get(i).getCode();
            if (code.equals(itemCode)){
                return i;
            }
        }
        return -1;
    }

    private void calculateTotal(){
        double total = 0;
        for (int i = 0; i < tblOrders.getItems().size(); i++) {
            double tempTotal = tblOrders.getItems().get(i).getTotal();
            total = total + tempTotal;
        }
        lblTotalCost.setText(String.valueOf(total));
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddItemOnAction(actionEvent);
    }
}
