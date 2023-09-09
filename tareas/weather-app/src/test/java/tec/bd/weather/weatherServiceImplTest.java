package tec.bd.weather;

import org.junit.jupiter.api.Test;
import tec.bd.weather.repository.memory.InMemoryForecastRepository;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.service.WeatherServiceImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class weatherServiceImplTest {

    @Test
    public void GivenACity_WhenCityIsSupported_ThenReturnTemperature(){

        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = mock(Forecast.class);

        given(forecast.getCityName()).willReturn("Alajuela");
        given(forecast.getTemperature()).willReturn(23.0f);
        given(forecastRepository.findAll()).willReturn(List.of(forecast));

        var actual = weatherService.getCityTemperature("Alajuela");

        verify(forecastRepository, times(1)).findAll();
        verify(forecast, times(1)).getCityName();
        verify(forecast, times(1)).getTemperature();

        assertThat(actual).isEqualTo(23.0f);

    }

    @Test
    public void GivenACity_WhenCityIsNotSupported_ThenException(){

        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);

        given(forecastRepository.findAll()).willReturn(Collections.emptyList());

        try{
            weatherService.getCityTemperature("Alajuela");
            fail("We shouldn't reach this line");
        } catch (Exception e) {

        }

        verify(forecastRepository, times(1)).findAll();

    }

    @Test
    public void GivenAValidForecast_WhenCreateNewForecast_ThenForecastIsCreated(){

        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findByID(anyInt())).willReturn(Optional.empty());

        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = new Forecast(5,"Costa Rica","Limon","33122", new Date(), 23.0f);

        weatherService.newForecast(forecast);

        verify(forecastRepository, times(1)).findByID(5);
        verify(forecastRepository, times(1)).save(forecast);

    }

    @Test
    public void GivenExisitingForecast_WhenCreateNewForecast_ThenServiceException(){

        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findByID(anyInt())).willReturn(Optional.of(new Forecast()));

        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 23.0f);

        try{
            weatherService.newForecast(forecast);
            fail("We shouldn't reach this line!");
        } catch (Exception e){

        }

        verify(forecastRepository, times(1)).findByID(5);
        verify(forecastRepository, never()).save(forecast);
    }

    @Test
    public void GivenAInvalidForecast_WhenCreateNewForecast_ThenServiceException(){

        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findByID(anyInt())).willReturn(Optional.of(new Forecast()));

        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = new Forecast(6,"Costa Rica","Limon","33122", new Date(), 23.0f);

        try {
            weatherService.newForecast(forecast);
            fail("We shouldn't reach this line!, invalid forecast");
        } catch (Exception e){

        }

        verify(forecastRepository, times(1)).findByID(anyInt());
        verify(forecastRepository, never()).save(forecast);

    }

    @Test
    public void GivenAValidForecast_WhenUpdatingTemperature_ThenNewTemperature(){

        var currentForecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 23.0f);
        var forecastToBeUpdated = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 19.0f);
        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findByID(anyInt())).willReturn(Optional.of(currentForecast));
        given(forecastRepository.update(forecastToBeUpdated)).willReturn(forecastToBeUpdated);
        given(forecastRepository.findAll()).willReturn(List.of(currentForecast));

        var weatherService = new WeatherServiceImpl(forecastRepository);

        var oldForecast = weatherService.getCityTemperature("Limon");

        var actual = weatherService.updateForecast(forecastToBeUpdated);

        verify(forecastRepository, times(1)).findByID(5);
        verify(forecastRepository, times(1)).update(forecastToBeUpdated);

        assertThat(actual).isNotSameAs(oldForecast);
        assertThat(actual.getId()).isEqualTo(5);
        assertThat(actual.getCountryName()).isEqualTo("Costa Rica");
        assertThat(actual.getCityName()).isEqualTo("Limon");
        assertThat(actual.getZipCode()).isEqualTo("33122");
        assertThat(actual.getTemperature()).isEqualTo(19.0f);

    }

    @Test
    public void GivenAnInvalidForecast_WhenUpdatingTemperature_ThenServiceException(){

        var invalidForecast = new Forecast(7,"Panama","Bocas del Toro","50040", new Date(), 25.0f);
        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findByID(anyInt())).willThrow(new RuntimeException());

        var weatherService = new WeatherServiceImpl(forecastRepository);

        try{
            weatherService.updateForecast(invalidForecast);
            fail("We shouldn't reach this line!, invalid forecast");
        } catch (Exception e){

        }

        verify(forecastRepository, times(1)).findByID(7);
        verify(forecastRepository, never()).update(invalidForecast);

    }

    @Test // Este test es igual al anterior
    public void GivenAnInvalidForecast_WhenUpdatingForecast_ThenServiceException(){

        var invalidForecast = new Forecast(7,"Panama","Bocas del Toro","50040", new Date(), 25.0f);

        var forecastRepository = mock(InMemoryForecastRepository.class);

        var weatherService = new WeatherServiceImpl(forecastRepository);

        given(forecastRepository.findByID(anyInt())).willReturn(Optional.empty());


        try{
            weatherService.updateForecast(invalidForecast);
            fail("We shouldn't reach this line!, invalid forecast");
        } catch (Exception e) {

        }

        verify(forecastRepository, times(1)).findByID(invalidForecast.getId());

    }

    @Test
    public void GivenAValidForecast_WhenDeletingForecast_ThenDeletesForecast(){

        var forecastToDelete = new Forecast(4, "Costa Rica", "Limon", "40401", new Date(), 25.0f);

        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findByID(anyInt())).willReturn(Optional.of(new Forecast()));

        var weatherService = new WeatherServiceImpl(forecastRepository);

//        doNothing().when(forecastRepository).delete(anyInt());
//        doCallRealMethod().when(weatherService).removeForecast(anyInt());

        weatherService.removeForecast(4);

        verify(forecastRepository, times(1)).delete(4); //delete(forecastToDelete.getId())
        verify(forecastRepository, times(1)).findByID(4);

    }

    @Test
    public void GivenAnInvalidForecast_WhenDeletingForecast_ThenServiceException(){
        // var forecastToDelete = new Forecast(7, "Costa Rica", "Limon", "40401", new Date(), 25.0f);

        var forecastRepository = mock(InMemoryForecastRepository.class);

        given(forecastRepository.findByID(anyInt())).willReturn(Optional.empty());

        var weatherService = new WeatherServiceImpl(forecastRepository);

        try {
            weatherService.removeForecast(7);
            fail("We shouldn't reach this line!, invalid forecast");
        } catch (Exception e){

        }

        verify(forecastRepository, times(1)).findByID(7);
        verify(forecastRepository, never()).delete(anyInt());
    }
}
