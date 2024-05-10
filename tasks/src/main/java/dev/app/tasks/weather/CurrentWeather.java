package dev.app.tasks.weather;

public class CurrentWeather {
    private double temperature_2m;
    private int relative_humidity_2m;
    private double precipitation;
    private double wind_speed_10m;
    private int cloud_cover;
    private int sunshine_duration;

    public CurrentWeather(double temperature_2m, int relative_humidity_2m, double precipitation, double wind_speed_10m,
            int cloud_cover, int sunshine_duration) {
        this.temperature_2m = temperature_2m;
        this.relative_humidity_2m = relative_humidity_2m;
        this.precipitation = precipitation;
        this.wind_speed_10m = wind_speed_10m;
        this.cloud_cover = cloud_cover;
        this.sunshine_duration = sunshine_duration;
    }

    public double getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public int getRelative_humidity_2m() {
        return relative_humidity_2m;
    }

    public void setRelative_humidity_2m(int relative_humidity_2m) {
        this.relative_humidity_2m = relative_humidity_2m;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getWind_speed_10m() {
        return wind_speed_10m;
    }

    public void setWind_speed_10m(double wind_speed_10m) {
        this.wind_speed_10m = wind_speed_10m;
    }

    public int getCloud_cover() {
        return cloud_cover;
    }

    public void setCloud_cover(int cloud_cover) {
        this.cloud_cover = cloud_cover;
    }

    public int getSunshine_duration() {
        return sunshine_duration;
    }

    public void setSunshine_duration(int sunshine_duration) {
        this.sunshine_duration = sunshine_duration;
    }

    @Override
    public String toString() {
        return "CurrentWeather [temperature_2m=" + temperature_2m + ", relative_humidity_2m=" + relative_humidity_2m
                + ", precipitation=" + precipitation + ", wind_speed_10m=" + wind_speed_10m + ", cloud_cover="
                + cloud_cover + ", sunshine_duration=" + sunshine_duration + "]";
    }

    

}
