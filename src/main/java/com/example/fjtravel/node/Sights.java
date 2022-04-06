package com.example.fjtravel.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Sights extends com.example.fjtravel.node.BaseEntity {

    private Long sid;
    private String name;
    private Double star;
    private String addr;
    private String phone;
    private String time;
    private String desc;
    private String service;
    private String policy;
    private String nearviews;
    private String nearfood;
    private String nearshop;

    @Relationship(type = "is")
    @JsonProperty("景点类型")
    private List<Genre> genres;


    public Sights() {

    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
    public String getNearviews() {
        return nearviews;
    }

    public void setNearviews(String nearviews) {
        this.nearviews = nearviews;
    }
    public String getNearfood() {
        return nearfood;
    }

    public void setNearfood(String nearfood) {
        this.nearfood = nearfood;
    }
    public String getNearshop() {
        return nearshop;
    }

    public void setNearshop(String nearshop) {
        this.nearshop = nearshop;
    }
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
