package de.hsos.Kunde.control;

import de.hsos.Kunde.boundary.DTO.AdresseDTO;
import de.hsos.Kunde.entity.Adresse;
import de.hsos.Kunde.entity.Kunde;

import java.util.Collection;

public interface KundenService {
    void kundeAnlegen(String vorname, String nachname);
    Collection<Kunde> kundenAbfragen();
    Kunde getKunde(long id);
    boolean deleteKunde(long id);
    void adresseAnlegen(long kundenId, AdresseDTO adresse);
    void adresseAendern(long kundenId, AdresseDTO adresseDTO);
    Adresse getAdresse(long id);
    boolean deleteAdresse(long id);
}
