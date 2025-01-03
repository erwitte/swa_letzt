package de.hsos.Bestellung.gateway.DTO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Vetoed
@Entity
@Dependent
public class BestellpostenJPAEntity extends PanacheEntity {
    private int anzahl;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ZutatJPAEntity> pizzaZutaten;

    public BestellpostenJPAEntity() {
    }

    public BestellpostenJPAEntity(List<ZutatJPAEntity> pizzaZutaten, int anzahl) {
        this.pizzaZutaten = pizzaZutaten;
        this.anzahl = anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void addToPizzaZutaten(ZutatJPAEntity zutat) {
        this.pizzaZutaten.add(zutat);
    }

    public void setPizzaZutaten(List<ZutatJPAEntity> pizza) {
        this.pizzaZutaten = pizza;
    }

    public List<ZutatJPAEntity> getPizzaZutaten() {
        return pizzaZutaten;
    }
}
