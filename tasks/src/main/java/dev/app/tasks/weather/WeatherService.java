package dev.app.tasks.weather;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    public City getLatitudeAndLongitude(String city) {
        try {
            String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response != null) {
                List results = (List) response.getBody().get("results");
                if (!results.isEmpty()) {
                    Map result = (Map) results.get(0);
                    Double latitude = (Double) result.get("latitude");
                    Double longitude = (Double) result.get("longitude");
                    City longitudeAndLatitude = new City(latitude, longitude);
                    return longitudeAndLatitude;
                }
            }
            return new City(40.6875, -7.9375);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al obtener la latitud y longitud: " + e.getMessage());
            return new City(40.6875, -7.9375);
        }
    }

    public WeatherResponse getCurrentWeather(String latitude, String longitude) {
        String url = "https://api.open-meteo.com/v1/dwd-icon?latitude=" + latitude + "&longitude=" + longitude
                + "&timezone=auto&current=temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m,cloud_cover,sunshine_duration";
        WeatherResponse weather = restTemplate.getForObject(url, WeatherResponse.class);
        return weather;
    }
}