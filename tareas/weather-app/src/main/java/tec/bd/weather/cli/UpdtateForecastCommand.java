package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

import java.util.Date;

@CommandLine.Command(name = "update-forecast", aliases = {"uf"}, description = "Update existing forecast data")
public class UpdtateForecastCommand implements Runnable{

    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The new forecast id")
    private int newForecastId;

    @CommandLine.Parameters(paramLabel = "<country name>", description = "The country name")
    private String countryName;

    @CommandLine.Parameters(paramLabel = "<city name>", description = "The city name")
    private String cityName;

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "The zip code")
    private String zipCode;

    @CommandLine.Parameters(paramLabel = "<forecast date>", description = "The Forecast date")
    private Date forecastDate;

    @CommandLine.Parameters(paramLabel = "<temperature>", description = "The temperature")
    private float temperature;

    public void run(){
        try{
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            var newForecast = new Forecast(newForecastId,countryName,cityName,zipCode, forecastDate, temperature);
            var updatedForecast = weatherService.updateForecast(newForecast);
            System.out.println(updatedForecast);
        } catch (Exception e){
            System.err.println("Can't update forecast. " + e.getMessage());
        }
    }
}
