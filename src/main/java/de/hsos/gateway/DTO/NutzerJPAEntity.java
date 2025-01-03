package de.hsos.gateway.DTO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.Entity;

@Entity
@Vetoed
public class NutzerJPAEntity extends PanacheEntity {
    private String nutzername;
    private String passwort;

    public String getNutzername() {
        return nutzername;
    }

    public void setNutzername(String nutzername) {
        this.nutzername = nutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
