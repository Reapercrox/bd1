package tec.bd.weather.repository;

import tec.bd.weather.entity.Forecast;

import java.util.List;
import java.util.Optional;

public interface Repository <T, ID> {

    /**
     * Find name by id
     * @param id
     * @return
     */
    Optional<Forecast> findByID(ID id);

    List<Forecast> findAll();

    T save(Forecast forecast);

    void delete(ID id);

    T update(T source);

}
