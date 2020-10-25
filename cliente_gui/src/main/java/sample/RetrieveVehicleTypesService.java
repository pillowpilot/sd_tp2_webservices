package sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sample.entity.VehicleType;

import java.util.List;

public class RetrieveVehicleTypesService extends ScheduledService<Void> {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    public ObservableList<VehicleType> vehicleTypes = FXCollections.observableArrayList();

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call(){
                System.out.println("En servicio... 22222");

                HttpGet request = new HttpGet("http://127.0.0.1:8080/testA-0.0.1-SNAPSHOT/vehicleType/getAll");

                try (CloseableHttpResponse response = httpClient.execute(request)){
                    System.out.println(response);
                    System.out.println(response.getStatusLine());
                    String json = EntityUtils.toString(response.getEntity());
                    System.out.println(json);

                    List<VehicleType> lvt = new ObjectMapper().readValue(json, new TypeReference<List<VehicleType>>() {});

                    System.out.println(lvt);

                    vehicleTypes.clear();
                    for(VehicleType v: lvt)
                        vehicleTypes.add(v);

                }catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println("En servicio...  22222");
                return null;
            }
        };
    }
}
