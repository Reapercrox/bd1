package tec.bd.weather.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository <T, ID extends Serializable> {

    /**
     * Find name by id
     * @param id
     * @return
     */
    T findByID(ID id);

    List<T> findAll();

    void save(T t);

    void delete(ID id);

    T update(T source);
}
