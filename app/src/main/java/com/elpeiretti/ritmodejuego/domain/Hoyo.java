package com.elpeiretti.ritmodejuego.domain;

import java.time.Duration;
import java.util.List;

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

    public static List<Hoyo> TEST_LIST = List.of(
            new Hoyo(1L, (short) 1, Duration.ofMinutes(15), 4, 4),
            new Hoyo(2L, (short) 2, Duration.ofMinutes(15), 4, 4),
            new Hoyo(3L, (short) 3, Duration.ofMinutes(15), 4, 4),
            new Hoyo(4L, (short) 4, Duration.ofMinutes(15), 4, 4),
            new Hoyo(5L, (short) 5, Duration.ofMinutes(15), 4, 4),
            new Hoyo(6L, (short) 6, Duration.ofMinutes(15), 4, 4),
            new Hoyo(7L, (short) 7, Duration.ofMinutes(15), 4, 4),
            new Hoyo(8L, (short) 8, Duration.ofMinutes(15), 4, 4),
            new Hoyo(9L, (short) 9, Duration.ofMinutes(15), 4, 4),
            new Hoyo(10L, (short) 10, Duration.ofMinutes(15), 4, 4),
            new Hoyo(11L, (short) 11, Duration.ofMinutes(15), 4, 4),
            new Hoyo(12L, (short) 12, Duration.ofMinutes(15), 4, 4),
            new Hoyo(13L, (short) 13, Duration.ofMinutes(15), 4, 4),
            new Hoyo(14L, (short) 14, Duration.ofMinutes(15), 4, 4),
            new Hoyo(15L, (short) 15, Duration.ofMinutes(15), 4, 4),
            new Hoyo(16L, (short) 16, Duration.ofMinutes(15), 4, 4),
            new Hoyo(17L, (short) 17, Duration.ofMinutes(15), 4, 4),
            new Hoyo(18L, (short) 18, Duration.ofMinutes(15), 4, 4)
    );

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
