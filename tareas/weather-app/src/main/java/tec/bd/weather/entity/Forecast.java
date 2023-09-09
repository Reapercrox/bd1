package tec.bd.weather.entity;

import javax.xml.crypto.Data;
import java.util.Date;

public class Forecast {

    private Integer id;
    private float temperature;
    private String cityName;

    private String zipCode;

    private String countryName;

    private Date forecastDate;

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", date=" + forecastDate +
                ", cityName='" + cityName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    public static void validate(Forecast newForecast){
        if(newForecast == null){
            throw new RuntimeException("No weather forecast provided");
        }
        if(newForecast.getId() == null){
            throw new RuntimeException("No weather forecast ID provided");
        }
        if(newForecast.getCountryName().isBlank()){
            throw new RuntimeException("Weather forecast country is invalid");
        }
        if(newForecast.getCityName().isBlank()){
            throw new RuntimeException("Weather forecast city is invalid");
        }
        if(newForecast.getZipCode().isBlank()){
            throw new RuntimeException("Weather forecast zip is invalid");
        }
        if(newForecast.getTemperature() < 0){
            throw new RuntimeException("Weather forecast temperature is invalid");
        }
    }

    public Forecast(){}

    public Forecast(String countryName, String cityName, String zipCode, Date forecastDate, float temperature){
        this(0, countryName, cityName, zipCode, forecastDate, temperature);
    }

    public Forecast(Integer id, String countryName, String cityName, String zipCode, Date forecastDate, float temperature){
        this.id = id;
        this.countryName = countryName;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.forecastDate = forecastDate;
        this.temperature = temperature;
    }
}
