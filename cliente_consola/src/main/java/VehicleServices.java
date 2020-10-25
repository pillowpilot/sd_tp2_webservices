import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Vehicle;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class VehicleServices {
    private final String host;
    private final String servicePrefix = "/vehicle";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public VehicleServices(String host){
        this.host = host;
    }

    public Vehicle addVehicle(String vehicleTypeDescription) throws UnsupportedEncodingException {
        final String servicePath = "/add";

        final String requestURL = host + servicePrefix + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpPost request = new HttpPost(requestURL);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("vehicleTypeDescription", vehicleTypeDescription));
        request.setEntity(new UrlEncodedFormEntity(params));

        Vehicle vehicle = null;
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            vehicle = new ObjectMapper().readValue(json, Vehicle.class);

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicle;
    }

    public String deleteById(Long id) {
        final String servicePath = "/delete";

        final String requestURL = host + servicePrefix + "/" + id + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpGet request = new HttpGet(requestURL);

        String json = "";
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            json = EntityUtils.toString(response.getEntity());
            System.out.println(json);

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return json;
    }

    public Vehicle getById(Long id) {
        final String servicePath = "/get";

        final String requestURL = host + servicePrefix + "/" + id + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpGet request = new HttpGet(requestURL);

        Vehicle vehicle = null;
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            vehicle = new ObjectMapper().readValue(json, Vehicle.class);

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicle;
    }

    public List<Vehicle> getAll() {
        final String servicePath = "/getAll";

        final String requestURL = host + servicePrefix + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpGet request = new HttpGet(requestURL);

        List<Vehicle> vehicles = new ArrayList<>();
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            vehicles = new ObjectMapper().readValue(json, new TypeReference<List<Vehicle>>() {});

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicles;
    }
}
