package tec.bd.weather;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class weatherServiceImplTest {

    @Test
    public void givenCity_validCity_returnTemperature(){
        var weatherService = new weatherServiceImpl();

        var actual = weatherService.getCityTemperature("Cartago");

        assertThat(actual).isEqualTo(19.0f);
    }
}
