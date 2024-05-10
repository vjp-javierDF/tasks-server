package dev.app.tasks.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currentWeather")
public class WeatherController {
    @Autowired
    private WeatherService service;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> getCurrentWeather(@PathVariable String city) {
        // get latitude and longitude by city name
        City longitudeAndLatitude = service.getLatitudeAndLongitude(city);
        // get current weather by latitude and longitude
        WeatherResponse weather = service.getCurrentWeather(String.valueOf(longitudeAndLatitude.getLatitude()), String.valueOf(longitudeAndLatitude.getLongitude()));
        if (weather != null) {
            return new ResponseEntity<WeatherResponse>(weather, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}