package tec.bd.weather;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class weatherServiceImplTest {

    @Test
    public void givenCity_validCity_returnTemperature(){
        var weatherService = new weatherServiceImpl();

        var actual = weatherService.getTemperature("Alajuela");

        assertThat(actual).isEqualTo(22.5);
    }
}
