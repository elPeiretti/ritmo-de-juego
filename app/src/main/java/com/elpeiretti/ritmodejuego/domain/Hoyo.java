package com.elpeiretti.ritmodejuego.domain;

import java.time.Duration;

public class Hoyo {

    private Long id;
    private Short number;
    private Duration tiempo;
    private Integer par;
    private Integer handicap;

    public Hoyo(Long id, Short number, Duration tiempo, Integer par, Integer handicap) {
        this.id = id;
        this.number = number;
        this.tiempo = tiempo;
        this.par = par;
        this.handicap = handicap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public Duration getTiempo() {
        return tiempo;
    }

    public void setTiempo(Duration tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getPar() {
        return par;
    }

    public void setPar(Integer par) {
        this.par = par;
    }

    public Integer getHandicap() {
        return handicap;
    }

    public void setHandicap(Integer handicap) {
        this.handicap = handicap;
    }
}
