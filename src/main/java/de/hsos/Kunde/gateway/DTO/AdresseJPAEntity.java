package de.hsos.Kunde.gateway.DTO;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Vetoed;
import jakarta.persistence.Embeddable;

@Vetoed
@Embeddable
@Dependent
public class AdresseJPAEntity {
    private String plz;
    private String strasse;
    private String hausnummer;
    private String ort;

    public AdresseJPAEntity(String plz, String ort, String strasse, String hausnummer) {
        this.plz = plz;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.ort = ort;
    }

    public AdresseJPAEntity(){}

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
