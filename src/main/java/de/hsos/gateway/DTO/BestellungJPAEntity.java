package de.hsos.gateway.DTO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Vetoed
@Entity
public class BestellungJPAEntity extends PanacheEntity {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BestellpostenJPAEntity> bestellposten;
    // todo bei registration legt jeder nutzer kundenkonto an
    // id wird in token versendet

    public BestellungJPAEntity() {}

    public List<BestellpostenJPAEntity> getBestellposten() {
        return bestellposten;
    }

    public void setBestellposten(List<BestellpostenJPAEntity> bestellposten) {
        this.bestellposten = bestellposten;
    }

    public void addBestellposten(BestellpostenJPAEntity bestellposten) {
        this.bestellposten.add(bestellposten);
    }

    public void removeBestellposten(BestellpostenJPAEntity bestellposten) {
        this.bestellposten.remove(bestellposten);
    }
}
