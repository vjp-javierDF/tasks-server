package dev.app.tasks.weather;

public class WeatherResponse {
    private double latitude;
    private double longitude;
    private String timezone;
    private CurrentWeather current;

    public WeatherResponse(double latitude, double longitude, CurrentWeather current, String timezone) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.current = current;
        this.timezone = timezone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "WeatherResponse [latitude=" + latitude + ", longitude=" + longitude + ", timezone=" + timezone
                + ", current=" + current + "]";
    }

    

}