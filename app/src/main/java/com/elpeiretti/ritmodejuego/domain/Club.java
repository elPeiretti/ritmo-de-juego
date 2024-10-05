package com.elpeiretti.ritmodejuego.domain;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

public class Club {

    @Exclude
    private String id;
    private String name;
    private List<Hoyo> hoyos;

    public Club() {
        this.hoyos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hoyo> getHoyos() {
        return hoyos;
    }

    public void setHoyos(List<Hoyo> hoyos) {
        this.hoyos = hoyos;
    }
}
