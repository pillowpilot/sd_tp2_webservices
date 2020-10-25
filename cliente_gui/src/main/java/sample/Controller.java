package sample;

import com.lynden.gmapsfx.GoogleMapView;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import jfxtras.scene.control.LocalDateTimePicker;
import sample.entity.VehicleType;

public class Controller {
    @FXML
    TextField vehicleTypeDescriptionTextField;
    @FXML
    Button vehicleTypeCreateButton;
    @FXML
    ChoiceBox vehicleVehicleTypeChoiceBox;
    @FXML
    LocalDateTimePicker vehicleTimestampPicker;
    @FXML
    Button vehicleCreateButton;
    @FXML
    ListView vehiclesListView;
    @FXML
    GoogleMapView mapView;
    @FXML
    Label leftStatusLabel;

    CreateVehicleTypeService vehicleTypeSubmissionService;
    RetrieveVehicleTypesService retrieveVehicleTypesService;

    @FXML
    public void initialize() {
        vehicleTypeSubmissionService = new CreateVehicleTypeService();
        vehicleTypeSubmissionService.vehicleTypeDescription.bind(vehicleTypeDescriptionTextField.textProperty());
        vehicleTypeSubmissionService.leftStatusText.addListener((observableValue, s, t1) ->
                Platform.runLater(() -> leftStatusLabel.setText(t1)));

        retrieveVehicleTypesService = new RetrieveVehicleTypesService();
        retrieveVehicleTypesService.vehicleTypes.addListener((ListChangeListener<? super VehicleType>) change ->
                Platform.runLater(() -> vehicleVehicleTypeChoiceBox.setItems(change.getList())));
        retrieveVehicleTypesService.setDelay(Duration.seconds(2));  // initial delay
        retrieveVehicleTypesService.setPeriod(Duration.seconds(5)); // seconds between calls
        retrieveVehicleTypesService.start();
    }

    @FXML
    void vehicleTypeCreateOnAction() {
        if(!vehicleTypeSubmissionService.isRunning()) {
            vehicleTypeSubmissionService.reset();
            vehicleTypeSubmissionService.start();
        }
    }
}
