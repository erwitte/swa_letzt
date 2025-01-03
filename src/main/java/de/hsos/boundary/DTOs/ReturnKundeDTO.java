package de.hsos.boundary.DTOs;

public record ReturnKundeDTO(
        String vorname, String nachname, AdresseDTO adresseDTO, long id
) {
}
