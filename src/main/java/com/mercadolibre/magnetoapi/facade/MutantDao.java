package com.mercadolibre.magnetoapi.facade;

import com.mercadolibre.magnetoapi.models.Human;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Data Access Object for scanned humans.
 */
public interface MutantDao extends CrudRepository<Human, Long> {

    List<Human> findAll();

    @Query("SELECT h FROM Human h WHERE h.isMutant=:isMutant")
    List<Human> fetchHumans(@Param("isMutant") Boolean isMutant);
}
