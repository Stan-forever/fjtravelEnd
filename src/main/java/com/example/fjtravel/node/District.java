package com.example.fjtravel.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class District extends com.example.fjtravel.node.BaseEntity {

    private Long did;
    private String name;

    @Relationship(type = "has")
    @JsonProperty("拥有景点")
    private List<Sights> sights;

    public District(){

    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
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
