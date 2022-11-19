package com.pdsacw.algomrexapibackend.Repository;

import com.pdsacw.algomrexapibackend.Entity.ShortestPathEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortestPathRepository extends JpaRepository<ShortestPathEntity, Integer> {

}
