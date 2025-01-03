package de.hsos.entity;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private List<Zutat> zutaten;

    public Pizza(String name, List<Zutat> zutaten) {
        this.name = name;
        this.zutaten = zutaten;
    }

    public Pizza(String name) {
        this.name = name;
        this.zutaten = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Zutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }

    public void addZutat(Zutat zutat) {
        this.zutaten.add(zutat);
    }

    public void deleteZutat(Zutat zutat) {
        this.zutaten.remove(zutat);
    }
}
