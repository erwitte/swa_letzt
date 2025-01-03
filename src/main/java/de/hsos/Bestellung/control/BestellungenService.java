package de.hsos.Bestellung.control;

import de.hsos.Bestellung.gateway.DTO.BestellpostenJPAEntity;

import java.util.List;

public interface BestellungenService {
    int bestellungAnlegen();
    List<BestellpostenJPAEntity> getBestellung(long bestellungId);
    void bestellungLoeschen(long bestellungId);
    long bestellpostenErzeugen(long bestellungId);
}
