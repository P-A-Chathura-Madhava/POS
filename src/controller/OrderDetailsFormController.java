package controller;

import bo.BoFactory;
import bo.custom.OrderDetailsBo;
import dto.OrderDetailsDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import view.tm.OrderDetailsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsFormController {

    public TableView<OrderDetailsTM> tblOrderDetails;
    public TableColumn colId;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colCost;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;

    OrderDetailsBo bo = BoFactory.getInstance().getBo(BoFactory.BoTypes.ORDER_DETAIL);

    public void initialize() throws SQLException, ClassNotFoundException {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        loadAllDetails();

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadNewWindow(newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void loadNewWindow(OrderDetailsTM dto) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/OrderReportForm.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        OrderReportFormController controller = fxmlLoader.getController();
        controller.setOrderId(dto.getId());
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void loadAllDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> allDetails = bo.getAllDetails();
        ObservableList<OrderDetailsTM> obList = FXCollections.observableArrayList();
        for (OrderDetailsDTO dto :
                allDetails) {
            obList.add(
                    new OrderDetailsTM(
                            dto.getId(), dto.getDate(), dto.getTime(), dto.getCost(), dto.getCustomerId(), dto.getCustomerName()
                    )
            );
        }
        tblOrderDetails.setItems(obList);
    }
}
