package de.hsos.Bestellung.boundary.DTOs;

public record AdresseDTO(
        String plz,
        String ort,
        String strasse,
        String hausnummer
) {
}
