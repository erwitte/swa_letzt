package de.hsos.Kunde.boundary.DTO;

public record AdresseDTO(
        String plz,
        String ort,
        String strasse,
        String hausnummer
) {
}
