package de.hsos.Kunde.boundary.DTO;

public record ReturnKundeDTO(
        String vorname, String nachname, AdresseDTO adresseDTO, long id
) {
}
