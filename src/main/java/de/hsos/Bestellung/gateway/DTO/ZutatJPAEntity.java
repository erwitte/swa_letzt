package de.hsos.Bestellung.gateway.DTO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.Entity;

@Vetoed
@Entity
public class ZutatJPAEntity extends PanacheEntity {
    private String name;
    private double preis;

    public ZutatJPAEntity() {}

    public ZutatJPAEntity(String name, double preis) {
        this.name = name;
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZutatJPAEntity that = (ZutatJPAEntity) o;
        if (preis != that.preis) return false;
        return name.equals(that.name);
    }
}
