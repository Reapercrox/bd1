package tec.bd.weather.cli;
import picocli.CommandLine;
import tec.bd.weather.service.weatherService;
import tec.bd.weather.service.weatherServiceImpl;

@CommandLine.Command(name = "by-zip", description = "Get weather for a particular zip")
public class WeatherByZipCodeCommand implements Runnable{

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "The zip code")
    private String zipCode;

    @Override
    public void run() {

        System.out.println("By Zip code: " + zipCode);

        try {
            weatherService service = new weatherServiceImpl();
            System.out.println(service.getZipCodeTemperature(zipCode));
        } catch(Exception e){
            System.err.println(zipCode + " not in registry");
        }

    }

}
