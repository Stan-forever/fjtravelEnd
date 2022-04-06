package com.example.fjtravel.repository;

import com.example.fjtravel.node.Sights;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SightsRepository extends Neo4jRepository<Sights, Long> {
    List<Sights> findByName(@Param("name") String name);
    @Query("match(n:City)-[:have]->(m:Sights) where n.name='厦门' return m.name")
    List<String> getSightsName();
}
