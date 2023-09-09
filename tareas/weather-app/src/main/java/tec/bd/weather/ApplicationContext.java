package tec.bd.weather;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.memory.InMemoryForecastRepository;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.repository.sql.ForecastRepository;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

public class ApplicationContext {

    private static final String SQLITE_DB_URL = "jdbc:sqlite::resource:sqlite/weather-service.db";
    private DataSource sqliteDataSource;
    private WeatherService weatherService;
    private Repository<Forecast, Integer> sqlForecastRepository;
    private Repository<Forecast,Integer> inMemoryForecastRepository;

    public ApplicationContext(){
        initSqliteDataSource();
        initInMemoryForecastRepository();
        initSQLForecastRepository();
        initWeatherService();
    }

    private void initSqliteDataSource(){
        var sqliteDS = new SQLiteDataSource();
        sqliteDS.setUrl(SQLITE_DB_URL);
        this.sqliteDataSource = sqliteDS;
    }

    private void initWeatherService(){
        this.weatherService = new WeatherServiceImpl(this.sqlForecastRepository);
    }

    public  void initInMemoryForecastRepository(){
        this.inMemoryForecastRepository = new InMemoryForecastRepository();
    }

    public void initSQLForecastRepository(){
        this.sqlForecastRepository = new ForecastRepository(this.sqliteDataSource);
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }

    public Repository<Forecast,Integer> getInMemoryForecastRepository(){
        return this.inMemoryForecastRepository;
    }

    public Repository<Forecast,Integer> getSqlForecastRepository(){
        return this.sqlForecastRepository;
    }
}
