import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import entities.LocationBeat;
import entities.Vehicle;
import entities.VehicleWithLocationSummary;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocationBeatServices {
    private final String host;
    private final String servicePrefix = "/locationbeat";
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public LocationBeatServices(String host) {
        this.host = host;
    }

    public LocationBeat addLocationBeat(Long vehicleId, Double latitude, Double longitude, LocalDateTime timestamp) throws UnsupportedEncodingException {
        final String servicePath = "/add";

        final String requestURL = host + servicePrefix + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpPost request = new HttpPost(requestURL);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("vehicleId", vehicleId.toString()));
        params.add(new BasicNameValuePair("latitude", latitude.toString()));
        params.add(new BasicNameValuePair("longitude", longitude.toString()));
        params.add(new BasicNameValuePair("timestamp", timestamp.toString()));

        request.setEntity(new UrlEncodedFormEntity(params));

        LocationBeat locationBeat = null;
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            locationBeat = objectMapper.readValue(json, LocationBeat.class);

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return locationBeat;
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

    public LocationBeat getById(Long id) {
        final String servicePath = "/get";

        final String requestURL = host + servicePrefix + "/" + id + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpGet request = new HttpGet(requestURL);

        LocationBeat locationBeat = null;
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            locationBeat = objectMapper.readValue(json, LocationBeat.class);

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return locationBeat;
    }

    public List<VehicleWithLocationSummary> getClosest(Double latitude, Double longitude, Double maxDistance) {
        final String servicePath = "/getClosest";

        final String requestURL = host + servicePrefix + servicePath +
                "/" + latitude.toString() + "/" + longitude.toString() + "/" + maxDistance.toString();
        System.out.println("Requesting: " + requestURL);
        HttpGet request = new HttpGet(requestURL);

        List<VehicleWithLocationSummary> locationBeats = new ArrayList<>();
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            locationBeats = objectMapper.readValue(json, new TypeReference<List<VehicleWithLocationSummary>>() {});

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return locationBeats;
    }

    public List<LocationBeat> getAll() {
        final String servicePath = "/getAll";

        final String requestURL = host + servicePrefix + servicePath;
        System.out.println("Requesting: " + requestURL);
        HttpGet request = new HttpGet(requestURL);

        List<LocationBeat> locationBeats = new ArrayList<>();
        try (CloseableHttpResponse response = httpClient.execute(request)){
            System.out.println(response);

            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            locationBeats = objectMapper.readValue(json, new TypeReference<List<LocationBeat>>() {});

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return locationBeats;
    }
}
