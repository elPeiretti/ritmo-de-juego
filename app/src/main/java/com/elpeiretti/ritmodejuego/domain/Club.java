package com.elpeiretti.ritmodejuego.domain;

import androidx.annotation.NonNull;

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
        for (int i=1; i<=18; i++) {
            Hoyo h = new Hoyo(i);
            this.hoyos.add(h);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
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
