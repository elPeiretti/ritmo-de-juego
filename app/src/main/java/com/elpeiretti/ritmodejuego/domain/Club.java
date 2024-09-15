package com.elpeiretti.ritmodejuego.domain;

import java.util.ArrayList;
import java.util.List;

public class Club {

    private Long id;
    private String name;
    private List<Hoyo> hoyos;

    public Club() {
        this.hoyos = new ArrayList<>();
    }

    public Club(Long id, String name, List<Hoyo> hoyos) {
        this.id = id;
        this.name = name;
        this.hoyos = hoyos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
