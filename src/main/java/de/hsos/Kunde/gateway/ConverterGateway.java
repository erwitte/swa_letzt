package de.hsos.Kunde.gateway;

import de.hsos.Kunde.entity.Adresse;
import de.hsos.Kunde.boundary.DTO.AdresseDTO;
import de.hsos.Bestellung.entity.Bestellposten;
import de.hsos.Kunde.entity.Kunde;
import de.hsos.Bestellung.entity.Zutat;
import de.hsos.Kunde.gateway.DTO.AdresseJPAEntity;
import de.hsos.Bestellung.gateway.DTO.BestellpostenJPAEntity;
import de.hsos.Kunde.gateway.DTO.KundeJPAEntity;
import de.hsos.Bestellung.gateway.DTO.ZutatJPAEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConverterGateway {
    public static Kunde kundeJPAToKunde(KundeJPAEntity kundeJPAEntity){
        return new Kunde(kundeJPAEntity.getVorname(), kundeJPAEntity.getNachname(),
                adresseJPAToAdresse(kundeJPAEntity.getAdresse()), kundeJPAEntity.id );
    }

    public static Adresse adresseJPAToAdresse(AdresseJPAEntity adresseJPAEntity){
        return new Adresse(adresseJPAEntity.getPlz(), adresseJPAEntity.getOrt(), adresseJPAEntity.getStrasse(), adresseJPAEntity.getHausnummer());
    }

    public static AdresseJPAEntity adresseToAdresseJPA(AdresseDTO adresse){
        return new AdresseJPAEntity(adresse.plz(), adresse.ort(), adresse.strasse(), adresse.hausnummer());
    }

    public static Zutat zutatJPAToZutat(ZutatJPAEntity zutatJPAEntity){
        return new Zutat(zutatJPAEntity.getName(), zutatJPAEntity.getPreis());
    }

    public static Bestellposten bestellpostenJPAToBestellposten(BestellpostenJPAEntity bestellpostenJPAEntity){
        Bestellposten bestellposten = new Bestellposten(bestellpostenJPAEntity.getAnzahl(), bestellpostenJPAEntity.id);
        List<Zutat> parsedZutaten = new ArrayList<>();
        for (ZutatJPAEntity z : bestellpostenJPAEntity.getPizzaZutaten()){
            parsedZutaten.add(zutatJPAToZutat(z));
        }
        bestellposten.setZutaten(parsedZutaten);
        return bestellposten;
    }
}
