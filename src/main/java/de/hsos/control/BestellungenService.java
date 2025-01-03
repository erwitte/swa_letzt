package de.hsos.control;

import de.hsos.gateway.DTO.BestellpostenJPAEntity;

import java.util.List;

public interface BestellungenService {
    int bestellungAnlegen();
    List<BestellpostenJPAEntity> getBestellung(long bestellungId);
    void bestellungLoeschen(long bestellungId);
    long bestellpostenErzeugen(long bestellungId);
}
