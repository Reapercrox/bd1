package tec.bd.weather;

import tec.bd.weather.entity.Weather;
import tec.bd.weather.repository.InMemoryWeatherRepository;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;

public class ApplicationContext {
    private Repository<Weather,Integer> weatherRepository;

    private WeatherService weatherService;

    private void initWeatherRepository(){
        this.weatherRepository = new InMemoryWeatherRepository();
    }

    private void initWeatherService(){
        this.weatherService = new WeatherServiceImpl(this.weatherRepository);
    }

    public ApplicationContext(){
        initWeatherRepository();
        initWeatherService();
    }

    public Repository<Weather,Integer> getWeatherRepository(){
        return this.weatherRepository;
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }
}
