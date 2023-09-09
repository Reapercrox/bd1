package tec.bd.weather.repository.sql;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import javax.xml.transform.Result;

public class ForecastRepository implements Repository<Forecast,Integer> {

    private final DataSource dataSource;

    public ForecastRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Forecast> findByID(Integer forecastId) {

        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(Queries.FIND_FORECAST_BY_ID)){
            stmt.setInt(1, forecastId);

            var rs = stmt.executeQuery();

            while(rs.next()){
                var forecast = this.fromResultSet(rs);
                return Optional.of(forecast);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete forecast", e);
        }
    }

    @Override
    public List<Forecast> findAll() {

        List<Forecast> allForecast = new ArrayList<>();

        try (Connection connection = this.dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(Queries.FIND_ALL_FORECAST)){

                while(rs.next()){
                    var forecast = this.fromResultSet(rs);
                    allForecast.add(forecast);
                }

            } catch (SQLException e){
                throw new RuntimeException("Failed to retrieve forecast", e);
            }

        return allForecast;
    }

    @Override
    public Forecast save(Forecast forecast) {

        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(Queries.INSERT_NEW_FORECAST, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, forecast.getCountryName());
            stmt.setString(2, forecast.getCityName());
            stmt.setString(3, forecast.getZipCode());
            stmt.setDate(4, new java.sql.Date(forecast.getForecastDate().getTime()));
            stmt.setFloat(5, forecast.getTemperature());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if(generatedKeys.next()){
                forecast.setId(generatedKeys.getInt(1));
            }

            return forecast;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save forecast", e);
        }
    }

    @Override
    public void delete(Integer forecastId) {
        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(Queries.DELETE_FORECAST_BY_ID)){
                stmt.setInt(1, forecastId);

                stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete forecast", e);
        }
    }

    @Override
    public Forecast update(Forecast source) {
        try(Connection connection = this.dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(Queries.UPDATE_FORECAST)){

                stmt.setString(1, source.getCountryName());
                stmt.setString(2, source.getCityName());
                stmt.setString(3, source.getZipCode());
                stmt.setDate(4, new java.sql.Date(source.getForecastDate().getTime()));
                stmt.setFloat(5, source.getTemperature());
                stmt.setInt(6, source.getId());
                stmt.executeUpdate();

                return source;

        } catch (Exception e){
            throw new RuntimeException("Failed to update forecast", e);
        }

    }

    private Forecast fromResultSet(ResultSet rs) throws SQLException {
        var forecastDate = rs.getDate("forecast_date");
        return new Forecast(
                rs.getInt("forecast_id"),
                rs.getString("country_name"),
                rs.getString("city_name"),
                rs.getString("zip_code"),
                new Date(forecastDate.getTime()),
                rs.getFloat("temperature")
        );
    }
}
