package tec.bd.weather;

public interface weatherService {

    static float getTemperature(String city) {
        return 0;
    }


    default float getTemperature() {
        return getTemperature(null);
    }

}
