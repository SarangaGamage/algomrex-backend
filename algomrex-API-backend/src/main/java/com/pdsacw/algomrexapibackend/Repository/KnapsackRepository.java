package com.pdsacw.algomrexapibackend.Repository;

import com.pdsacw.algomrexapibackend.Entity.KnapsackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnapsackRepository extends JpaRepository<KnapsackEntity, Long> {
}
