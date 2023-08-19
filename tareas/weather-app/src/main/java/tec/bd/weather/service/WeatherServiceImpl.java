package tec.bd.weather.service;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

public class WeatherServiceImpl implements WeatherService {

    //private Map<String,Float> cityTemperatureData;
    //private Map<String,Float> zipCodeTemperatureData;

    private final Repository<Forecast,Integer> weatherRepository;

    public WeatherServiceImpl(Repository<Forecast,Integer> weatherRepository){
        this.weatherRepository = weatherRepository;
    }



    public float getCityTemperature(String city) {
        var weather = this.weatherRepository
                .findAll()
                .stream()
                .filter(e -> e.getCityName().equals(city))
                .findFirst()
                .orElseThrow(()->new RuntimeException(city + " is not supported"));
        return weather.getTemperature();
    }


    public float getZipCodeTemperature(String zipCode) {
        var weather = this.weatherRepository
                .findAll()
                .stream()
                .filter(e -> e.getCityName().equals(zipCode))
                .findFirst()
                .orElseThrow(()->new RuntimeException(zipCode + " is not supported"));
        return weather.getTemperature();
    }

    @Override
    public void newForecast(Forecast newForecast) {

        Forecast.validate(newForecast);
        //Otras validaciones

        var current = this.weatherRepository.findByID(newForecast.getId());

        if(current.isPresent()){
            throw new RuntimeException("Weather forecast ID already exist in database");
        }

        this.weatherRepository.save(newForecast);
    }

    @Override
    public Forecast updateForecast(Forecast forecast) {
        Forecast.validate(forecast);
        var current = this.weatherRepository.findByID(forecast.getId());
        if (current.isEmpty()){
            throw new RuntimeException("Weather forecast ID doesn't exist in database");
        }
        return this.weatherRepository.update(forecast);
    }

    @Override
    public void removeForecast(Integer forecastId) {
        var current = this.weatherRepository.findByID(forecastId);
        if(current.isEmpty()){
            throw new RuntimeException("No forecast ID found");
        }
        else{
            this.weatherRepository.delete(forecastId);
        }

    }
}
