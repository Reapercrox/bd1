package tec.bd.weather.entity;

public class Weather {

    private Integer id;
    private float temperature;
    private String cityName;
    private String zipCode;

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

    public Weather(){

    }

    public Weather(Integer id, String cityName, String zipCode, float temperature){
        this.id = id;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.temperature = temperature;
    }
}
