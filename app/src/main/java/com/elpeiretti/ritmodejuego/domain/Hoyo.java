package com.elpeiretti.ritmodejuego.domain;

import java.time.Duration;
import java.util.List;

public class Hoyo {

    private Integer number;
    private Integer horas;
    private Integer minutos;
    private Integer par;
    private Integer handicap;

    public Hoyo() {}

    public Hoyo(int number) {
        this.number = number;
    }

//    public static List<Hoyo> TEST_LIST = List.of(
//            new Hoyo(1, Duration.ofMinutes(15), 4, 4),
//            new Hoyo(2, Duration.ofMinutes(15), 4, 4),
//            new Hoyo(3, Duration.ofMinutes(15), 4, 4),
//            new Hoyo(4, Duration.ofMinutes(15), 4, 4),
//            new Hoyo(5, Duration.ofMinutes(15), 4, 4),
//            new Hoyo(6, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 7, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 8, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 9, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 10, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 11, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 12, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 13, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 14, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 15, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 16, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 17, Duration.ofMinutes(15), 4, 4),
//            new Hoyo( 18, Duration.ofMinutes(15), 4, 4)
//    );

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
}
