package de.hsos.Kunde.boundary;

import de.hsos.Kunde.entity.Adresse;
import de.hsos.Bestellung.boundary.DTOs.AdresseDTO;
import de.hsos.Bestellung.boundary.DTOs.ReturnKundeDTO;
import de.hsos.Kunde.entity.Kunde;

public class ConverterBoundary {
    public static AdresseDTO adresseToAdresseDTO(Adresse adresse) {
        return new AdresseDTO(adresse.plz(), adresse.ort(), adresse.strasse(), adresse.hausnummer());
    }

    public static ReturnKundeDTO kundeToReturnKundeDTO(Kunde kunde){
        AdresseDTO adresseDTO = new AdresseDTO(kunde.adresse().plz(),
                kunde.adresse().ort(), kunde.adresse().strasse(), kunde.adresse().hausnummer());
        return new ReturnKundeDTO(kunde.vorname(), kunde.nachname(), adresseDTO, kunde.id());
    }

    public static AdresseDTO adresseDTOToAdresse(AdresseDTO adresseDTO){
        return new AdresseDTO(adresseDTO.plz(), adresseDTO.ort(), adresseDTO.strasse(), adresseDTO.hausnummer());
    }
}
