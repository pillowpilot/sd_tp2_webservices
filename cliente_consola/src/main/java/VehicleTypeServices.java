import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.VehicleType;
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

public class VehicleTypeServices {
    private final String host;
    private final String servicePrefix = "/vehicleType";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public VehicleTypeServices(String host){
        this.host = host;
    }

    public VehicleType addVehicleType(String description) throws UnsupportedEncodingException {
        final String servicePath = "/add";

        final String requestURL = host + servicePrefix + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpPost request = new HttpPost(requestURL);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("description", description));
        request.setEntity(new UrlEncodedFormEntity(params));

        VehicleType vehicleType = null;
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            vehicleType = new ObjectMapper().readValue(json, VehicleType.class);

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicleType;
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

    public VehicleType getById(Long id) {
        final String servicePath = "/get";

        final String requestURL = host + servicePrefix + "/" + id + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpGet request = new HttpGet(requestURL);

        VehicleType vehicleType = null;
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            vehicleType = new ObjectMapper().readValue(json, VehicleType.class);

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicleType;
    }

    public List<VehicleType> getAll() {
        final String servicePath = "/getAll";

        final String requestURL = host + servicePrefix + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpGet request = new HttpGet(requestURL);

        List<VehicleType> vehicleTypes = new ArrayList<>();
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            vehicleTypes = new ObjectMapper().readValue(json, new TypeReference<List<VehicleType>>() {});

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return vehicleTypes;
    }
}
