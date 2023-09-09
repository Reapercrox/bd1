package tec.bd.weather.cli;

import picocli.CommandLine;

@CommandLine.Command(
    name = "Weather App",
    subcommands = {
            ForecastByCityCommand.class,
            ForecastByZipCodeCommand.class,
            CreateForecastCommand.class,
            UpdtateForecastCommand.class,
            RemoveForecastCommand.class,
            AllForecastCommand.class,
            CommandLine.HelpCommand.class
        },
        description = "Weather App Service by city and zip code"
)
public class MainCommand implements Runnable{
        @Override
        public void run() {

        }
}
