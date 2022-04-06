package com.example.fjtravel.repository;

import com.example.fjtravel.node.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends Neo4jRepository<City, Long>{
    List<City> findByName(@Param("name") String name);
}
