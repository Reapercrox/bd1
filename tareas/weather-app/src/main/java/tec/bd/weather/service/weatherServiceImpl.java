package tec.bd.weather.service;

import java.util.HashMap;
import java.util.Map;
import static java.util.Optional.*;

public class weatherServiceImpl implements weatherService{

    private Map<String,Float> cityTemperatureData;
    private Map<String,Float> zipCodeTemperatureData;

    public weatherServiceImpl(){

        this.cityTemperatureData = new HashMap<>(){
            {put("Alajuela",24.0f);}
            {put("Cartago",18.0f);}
            {put("San Jose",21.0f);}
            {put("Heredia",25.0f);}
            {put("Guanacaste",27.0f);}
            {put("Puntarenas",29.0f);}
            {put("Limon",28.0f);}
        };

        this.zipCodeTemperatureData = new HashMap<>(){
            {put("10101",24.0f);}
            {put("30801",18.0f);}
            {put("20201",21.0f);}
            {put("40401",25.0f);}
            {put("50501",27.0f);}
            {put("60601",29.0f);}
            {put("70701",28.0f);}
        };

    }



    public float getCityTemperature(String city) {
        var temperature = ofNullable(this.cityTemperatureData.get(city));
        return temperature.orElseThrow();
    }


    public float getZipCodeTemperature(String zipCode) {
        var temperature = ofNullable(this.zipCodeTemperatureData.get(zipCode));
        return temperature.orElseThrow();
    }
}
