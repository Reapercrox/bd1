package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

@CommandLine.Command(name = "delete-forecast", aliases = {"df"}, description = "Delete existing forecast data")
public class DeleteForecastCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The new forecast id")
    private int newForecastId;

    @CommandLine.Parameters(paramLabel = "<country name>", description = "The country name")
    private String countryName;

    @CommandLine.Parameters(paramLabel = "<city name>", description = "The city name")
    private String cityName;

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "The zip code")
    private String zipCode;

    @CommandLine.Parameters(paramLabel = "<temperature>", description = "The temperature")
    private float temperature;

    public void run() {
        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            var newForecast = new Forecast(newForecastId, countryName, cityName, zipCode, temperature);
            weatherService.removeForecast(newForecastId);
            System.out.println(newForecast + " deleted");
        } catch (Exception e) {
            System.err.println("Can't delete forecast. " + e.getMessage());
        }
    }
}
