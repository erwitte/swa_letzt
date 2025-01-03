package de.hsos.Bestellung.control;

import java.util.List;

public interface BestellpostenService {
    void addZutatZuBestellposten(int bestellpostenId, List<String> zutaten);
    void deleteZutatVonBestellposten(int bestellpostenId, List<String> zutaten);
    void anzahlInBestllpostenAendern( int bestellpostenId, int anzahl);
    void deleteBestellposten(long bestellungId, long bestellpostenId);
}
