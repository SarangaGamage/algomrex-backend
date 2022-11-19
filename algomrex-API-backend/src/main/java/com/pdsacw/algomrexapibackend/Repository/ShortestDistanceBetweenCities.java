package com.pdsacw.algomrexapibackend.Repository;

import com.pdsacw.algomrexapibackend.Entity.ShortestDistanceBetweenCitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortestDistanceBetweenCities  extends JpaRepository<ShortestDistanceBetweenCitiesEntity, Integer> {

}
