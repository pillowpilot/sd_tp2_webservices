package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CreateVehicleTypeService extends Service<Void> {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    public StringProperty vehicleTypeDescription = new SimpleStringProperty();
    public StringProperty leftStatusText = new SimpleStringProperty();

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call(){
                System.out.println("En servicio...");
                final String description = vehicleTypeDescription.getValue();

                HttpGet request = new HttpGet("http://127.0.0.1:8080/testA-0.0.1-SNAPSHOT/vehicleType/getAll");

                try (CloseableHttpResponse response = httpClient.execute(request)){
                    System.out.println(response);
                    System.out.println(response.getStatusLine());
                    String json = EntityUtils.toString(response.getEntity());
                    System.out.println(json);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println("En servicio...");
                leftStatusText.setValue("Creating vehicle type: " + description);
                return null;
            }
        };
    }
}
