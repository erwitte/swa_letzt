package de.hsos.boundary.DTOs;

public record AdresseDTO(
        String plz,
        String ort,
        String strasse,
        String hausnummer
) {
}
