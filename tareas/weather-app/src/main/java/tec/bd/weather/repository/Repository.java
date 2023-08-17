package tec.bd.weather.repository;

import tec.bd.weather.entity.Weather;

import java.util.List;
import java.util.Optional;

public interface Repository <T, ID> {

    /**
     * Find name by id
     * @param id
     * @return
     */
    Optional<Weather> findByID(ID id);

    List<Weather> findAll();

    void save(T t);

    void save(Weather weather);

    void delete(ID id);

    T update(T source);

    Optional<Weather> update(Optional<Weather> source);
}
