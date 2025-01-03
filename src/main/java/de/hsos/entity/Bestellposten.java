package de.hsos.entity;

import java.util.ArrayList;
import java.util.List;

public class Bestellposten {
    private long id;
    private int anzahl;
    private List<Zutat> zutaten = new ArrayList<>();

    public Bestellposten(List<Zutat> zutaten, int anzahl) {
        this.anzahl = anzahl;
        this.zutaten = zutaten;
    }

    public Bestellposten(int anzahl, long id) {
        this.anzahl = anzahl;
        this.id = id;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public List<Zutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }
}
