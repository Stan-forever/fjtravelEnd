package com.example.fjtravel.repository;
import com.example.fjtravel.node.District;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface DistrictRepository extends Neo4jRepository<District, Long>{
    List<District> findByName(@Param("name") String name);
}
