package tec.bd.weather.service;

import tec.bd.weather.entity.Forecast;

import java.util.List;

public interface WeatherService {


     float getCityTemperature(String city);

     float getZipCodeTemperature(String zipCode);

     Forecast newForecast(Forecast forecast);

     Forecast updateForecast(Forecast forecast);

     void removeForecast(Integer forecast);

     List<Forecast> getAllForecasts();


}
