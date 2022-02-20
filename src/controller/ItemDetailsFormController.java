package controller;

import bo.BoFactory;
import bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CustomerTM;
import view.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ItemDetailsFormController {
    public TableView<ItemTM> tblItemDetails;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colOption;
    public JFXButton btnSaveItem;
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQuantity;
    public JFXButton btnNewItem;
    public JFXTextField txtSearch;

    ItemBo bo = BoFactory.getInstance().getBo(BoFactory.BoTypes.ITEM);

    public void initialize() throws SQLException, ClassNotFoundException {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllItems("");
        setItemId();

        tblItemDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                setItemData(newValue);
            }
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadAllItems(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void setItemData(ItemTM tm) {
        btnSaveItem.setText("Update");
        txtItemCode.setText(tm.getCode());
        txtDescription.setText(tm.getDescription());
        txtUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
        txtQuantity.setText(String.valueOf(tm.getQtyOnHand()));
    }

    private void setItemId() throws SQLException, ClassNotFoundException {
        txtItemCode.setText(bo.getItemId());
    }

    private void loadAllItems(String text) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = bo.searchItems(text);
        ObservableList<ItemTM> obList = FXCollections.observableArrayList();

        for (ItemDTO dto :
                allItems) {
            Button btn = new Button("Delete");
            obList.add(
                    new ItemTM(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), btn));

            btn.setOnAction((e) -> {
                try {
                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION, "Are you sure?",
                            ButtonType.NO, ButtonType.YES, ButtonType.CANCEL
                    );
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.get() == ButtonType.YES){
                        if(bo.deleteItem(dto.getCode())){
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                            loadAllItems("");
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

        tblItemDetails.setItems(obList);
    }

    public void btnSaveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(btnSaveItem.getText().equalsIgnoreCase("Save")){
            boolean isSaved = bo.saveItem(
                    new ItemDTO(
                            txtItemCode.getText(),
                            txtDescription.getText(),
                            Double.parseDouble(txtUnitPrice.getText()),
                            Integer.parseInt(txtQuantity.getText())
                    )
            );
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                loadAllItems("");
                setItemId();
            }else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        }else {
            boolean isUpdated = bo.updateItem(
                    new ItemDTO(
                            txtItemCode.getText(),
                            txtDescription.getText(),
                            Double.parseDouble(txtUnitPrice.getText()),
                            Integer.parseInt(txtQuantity.getText())
                    )
            );
            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                loadAllItems("");
            }else {
                new Alert(Alert.AlertType.ERROR, "Try Again").show();
            }
        }
    }

    public void btnNewItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setItemId();
        btnSaveItem.setText("Save");
        clear();
    }

    private void clear() {
        txtDescription.clear();;
        txtUnitPrice.clear();
        txtQuantity.clear();
    }
}
