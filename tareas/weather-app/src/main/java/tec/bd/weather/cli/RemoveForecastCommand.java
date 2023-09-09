package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;

@CommandLine.Command(name = "delete-forecast", aliases = {"df"}, description = "Delete existing forecast data")
public class RemoveForecastCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The forecast id")
    private int forecastId;

    public void run() {
        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            weatherService.removeForecast(forecastId);
            System.out.println("Forecast ID: " + forecastId + " removed from database");
        } catch (Exception e) {
            System.err.println("Can't delete forecast. " + e.getMessage());
        }
    }
}
