package tec.bd.weather;

import picocli.CommandLine;

@CommandLine.Command(name = "by-city", description = "Get weather for a particular city")
public class WeatherByCityCommand implements Runnable{

    @CommandLine.Parameters(paramLabel = "<city name>", description = "The city name")
    private String cityName;

    @Override
    public void run() {

        System.out.println("By City: "+cityName);

        try {
            weatherService service = new weatherServiceImpl();
            System.out.println(service.getCityTemperature(cityName));
        } catch(Exception e){
            System.err.println(cityName + " not in registry");
        }
    }
}
