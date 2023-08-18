package tec.bd.weather.entity;

public class Weather {

    private Integer id;
    private float temperature;
    private String cityName;
    private String zipCode;

    private String countryName;

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

    public static void validate(Weather newWeather){
        if(newWeather == null){
            throw new RuntimeException("No weather forecast provided");
        }
        if(newWeather.getId() == null){
            throw new RuntimeException("No weather forecast ID provided");
        }
        if(newWeather.getId() > 0){
            throw new RuntimeException("Weather forecast ID invalid");
        }
    }

    public Weather(Integer id, String countryName, String cityName, String zipCode, float temperature){
        this.id = id;
        this.countryName = countryName;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.temperature = temperature;
    }
}
