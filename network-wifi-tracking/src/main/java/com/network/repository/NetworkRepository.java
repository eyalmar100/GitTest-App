package com.network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.network.entity.Network;

@Repository
public interface NetworkRepository extends JpaRepository<Network, Long> {

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Network n SET n.avgThroughput = :avgThroughput WHERE n.id = :id")
    int updateNetwork(@Param("id") long networkId, @Param("avgThroughput") float avgThroughput);
}
