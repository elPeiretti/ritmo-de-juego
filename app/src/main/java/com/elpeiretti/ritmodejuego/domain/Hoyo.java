package com.elpeiretti.ritmodejuego.domain;

public class Hoyo {

    private Integer number;
    private Integer horas = 0;
    private Integer minutos = 15;
    private Integer par;
    private Integer handicap;

    public Hoyo() {}

    public Hoyo(int number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public boolean hasNullValues() {
        return number == null || horas == null ||
                minutos == null || par == null ||
                handicap == null;
    }
}
