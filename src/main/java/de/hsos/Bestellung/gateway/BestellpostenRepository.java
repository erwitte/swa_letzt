package de.hsos.Bestellung.gateway;

import de.hsos.Bestellung.gateway.DTO.BestellpostenJPAEntity;
import de.hsos.Bestellung.gateway.DTO.BestellungJPAEntity;
import de.hsos.Bestellung.gateway.DTO.ZutatJPAEntity;
import de.hsos.Bestellung.control.BestellpostenService;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class BestellpostenRepository implements BestellpostenService {

    @Override
    public void addZutatZuBestellposten(int bestellpostenIndex, List<String> zutaten) {
        BestellpostenJPAEntity bestellposten = BestellpostenJPAEntity.findById(bestellpostenIndex);
        for (String zutat : zutaten) {
            ZutatJPAEntity newZutat = parseStringToZutat(zutat);
            newZutat.persist();
            bestellposten.addToPizzaZutaten(newZutat);
        }
        bestellposten.persist();
    }

    @Override
    public void deleteZutatVonBestellposten(int bestellpostenId, List<String> zutaten) {
        BestellpostenJPAEntity bestellposten = BestellpostenJPAEntity.findById(bestellpostenId);
        List<ZutatJPAEntity> zutatenListe = bestellposten.getPizzaZutaten();
        for (String zutat : zutaten) {
            zutatenListe.remove(parseStringToZutat(zutat));
            System.out.println(zutat);
        }
        bestellposten.setPizzaZutaten(zutatenListe);
        bestellposten.persist();
    }

    @Override
    public void anzahlInBestllpostenAendern(int bestellpostenIndex, int anzahl) {
        BestellpostenJPAEntity bestellposten = BestellpostenJPAEntity.findById(bestellpostenIndex);
        bestellposten.setAnzahl(anzahl);
        bestellposten.persist();
    }

    private ZutatJPAEntity parseStringToZutat(String zutat){
        return switch (zutat) {
            case "Käse" -> new ZutatJPAEntity("Käse", 1.5);
            case "Pilze" -> new ZutatJPAEntity("Pilze", 2);
            case "Hollandaise" -> new ZutatJPAEntity("Hollandaise", 2);
            case "Sucuk" -> new ZutatJPAEntity("Sucuk", 2);
            case "Ei" -> new ZutatJPAEntity("Ei", 2);
            case "Zwiebeln" -> new ZutatJPAEntity("Zwiebeln", 2);
            case "Kebabfleisch" -> new ZutatJPAEntity("Kebabfleisch", 2);
            default -> null;
        };
    }

    @Override
    public void deleteBestellposten(long bestellungId, long bestellpostenId){
        BestellungJPAEntity bestellungJPAEntity = BestellungJPAEntity.findById(bestellungId);
        BestellpostenJPAEntity bestellpostenJPAEntity = BestellpostenJPAEntity.findById(bestellpostenId);
        bestellungJPAEntity.removeBestellposten(bestellpostenJPAEntity);
        bestellpostenJPAEntity.delete();
        bestellpostenJPAEntity.persist();
        bestellungJPAEntity.persist();
    }
}
