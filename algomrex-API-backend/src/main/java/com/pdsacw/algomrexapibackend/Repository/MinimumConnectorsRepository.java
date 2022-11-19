package com.pdsacw.algomrexapibackend.Repository;

import com.pdsacw.algomrexapibackend.Entity.MinimumConnectorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinimumConnectorsRepository extends JpaRepository<MinimumConnectorsEntity, Long> {
}
