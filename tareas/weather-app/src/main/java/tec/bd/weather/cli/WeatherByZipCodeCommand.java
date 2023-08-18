package tec.bd.weather.cli;
import picocli.CommandLine;
import tec.bd.weather.ApplicationContext;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;

@CommandLine.Command(name = "by-zip", description = "Get weather for a particular zip")
public class WeatherByZipCodeCommand implements Runnable{

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "The zip code")
    private String zipCode;

    @Override
    public void run() {

        System.out.println("By Zip code: " + zipCode);

        try {
            var appContext = new ApplicationContext();
            var weatherService = appContext.getWeatherService();
            System.out.println(weatherService.getZipCodeTemperature(zipCode));
        } catch(Exception e){
            System.err.println(zipCode + " not in registry");
        }

    }

}
