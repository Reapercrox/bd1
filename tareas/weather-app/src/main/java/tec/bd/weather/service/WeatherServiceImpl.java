package tec.bd.weather.service;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

import java.util.List;

public class WeatherServiceImpl implements WeatherService {

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
    public Forecast newForecast(Forecast newForecast) {

        Forecast.validate(newForecast);

        var current = this.weatherRepository.findByID(newForecast.getId());

        if(current.isPresent()){
            throw new RuntimeException("Weather forecast ID already exist in database");
        }

        return this.weatherRepository.save(newForecast);
    }

    @Override
    public Forecast updateForecast(Forecast forecast) {
        Forecast.validate(forecast);
        if (forecast.getId() < 1) {
            throw new RuntimeException("Invalid forecast Id " + forecast.getId());
        }
        var current = this.weatherRepository.findByID(forecast.getId());
        if (current.isEmpty()){
            throw new RuntimeException("Weather forecast ID doesn't exist in database");
        }
        return this.weatherRepository.update(forecast);
    }

    @Override
    public void removeForecast(Integer forecastID) {
        var current = this.weatherRepository.findByID(forecastID);
        if(current.isEmpty()){
            throw new RuntimeException("No forecast ID found");
        }
        else{
            this.weatherRepository.delete(forecastID);
        }

    }

    public List<Forecast> getAllForecasts(){
            return this.weatherRepository.findAll();
    }
}
