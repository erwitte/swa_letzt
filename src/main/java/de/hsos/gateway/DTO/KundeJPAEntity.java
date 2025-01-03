package de.hsos.gateway.DTO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.*;

@Vetoed
@Entity
public class KundeJPAEntity extends PanacheEntity {

    @Column(nullable = false)
    private String vorname;

    @Column(nullable = false)
    private String nachname;

    @Embedded
    private AdresseJPAEntity adresseJPAEntity;

    public KundeJPAEntity() {}

    public KundeJPAEntity(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public AdresseJPAEntity getAdresse() {
        return adresseJPAEntity != null ? adresseJPAEntity : new AdresseJPAEntity();
    }

    public void setAdresse(AdresseJPAEntity adresseJPAEntity) {
        this.adresseJPAEntity = adresseJPAEntity;
    }
}
