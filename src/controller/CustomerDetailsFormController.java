package controller;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.impl.CustomerBoIMPL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerDetailsFormController {

    public TableView<CustomerTM> tblCustomerDetails;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXButton btnSaveCustomer;
    public JFXButton btnNewCustomer;
    public JFXTextField txtSearch;

    CustomerBo bo = BoFactory.getInstance().getBo(BoFactory.BoTypes.CUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllCustomers("");
        setCustomerId();

        tblCustomerDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                setCustomerData(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadAllCustomers(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setCustomerData(CustomerTM tm) {
        btnSaveCustomer.setText("Update");
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtSalary.setText(String.valueOf(tm.getSalary()));
    }

    private void setCustomerId() throws SQLException, ClassNotFoundException {
        txtId.setText(bo.getCustomerId());
    }

    private void loadAllCustomers(String text) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = bo.searchCustomers(text);
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

        for (CustomerDTO dto :
                allCustomers) {
            Button btn = new Button("Delete");
            obList.add(
                    new CustomerTM(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary(), btn));

            btn.setOnAction((e) -> {
                try {
                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION, "Are you sure?",
                            ButtonType.NO, ButtonType.YES, ButtonType.CANCEL
                    );
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.get() == ButtonType.YES){
                        if(bo.deleteCustomer(dto.getId())){
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                            loadAllCustomers("");
                            setCustomerId();
                            clear();
                        }else {
                            new Alert(Alert.AlertType.ERROR, "Try Again").show();
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            });

        }

        tblCustomerDetails.setItems(obList);
    }

    public void btnSaveCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(btnSaveCustomer.getText().equalsIgnoreCase("Save")){
            boolean isSaved = bo.saveCustomer(
                    new CustomerDTO(
                            txtId.getText(),
                            txtName.getText(),
                            txtAddress.getText(),
                            Double.parseDouble(txtSalary.getText())
                    )
            );
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                loadAllCustomers("");
                setCustomerId();
            }else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        }else {
            boolean isUpdated = bo.updateCustomer(
                    new CustomerDTO(
                            txtId.getText(),
                            txtName.getText(),
                            txtAddress.getText(),
                            Double.parseDouble(txtSalary.getText())
                    )
            );
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                loadAllCustomers("");
            }else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        }
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setCustomerId();
        btnSaveCustomer.setText("Save");
        clear();
    }

    private void clear() {
        txtName.clear();;
        txtAddress.clear();
        txtSalary.clear();
    }
}
