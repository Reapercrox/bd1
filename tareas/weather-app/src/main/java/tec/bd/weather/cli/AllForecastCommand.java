package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.entity.Forecast;

import java.util.List;

@CommandLine.Command(name = "all-forecast", aliases = "af", description = "Get all forecast")
public class AllForecastCommand implements Runnable{

    @Override
    public void run() {

        try{
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            List<Forecast> forecast = weatherService.getAllForecasts();
            System.out.println("All forecast \n====================================");
            for( Forecast f: forecast){
                System.out.println(f);
            }
        } catch (Exception e){
            System.out.println("Can't create forecast. " + e.getMessage());
        }

    }
}
