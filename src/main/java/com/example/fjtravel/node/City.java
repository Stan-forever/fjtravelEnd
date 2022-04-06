package com.example.fjtravel.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class City extends com.example.fjtravel.node.BaseEntity {

    private Long cid;
    private String name;

    @Relationship(type = "have")
    @JsonProperty("拥有景点")
    private List<Sights> sights;

    public City(){

    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sights> getSights() {
        return sights;
    }

    public void setSights(List<Sights> sights) {
        this.sights = sights;
    }
}
