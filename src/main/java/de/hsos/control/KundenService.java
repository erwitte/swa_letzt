package de.hsos.control;

import de.hsos.boundary.DTOs.AdresseDTO;
import de.hsos.entity.Adresse;
import de.hsos.entity.Kunde;

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
