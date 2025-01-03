package de.hsos.gateway;

import de.hsos.boundary.DTOs.AdresseDTO;
import de.hsos.entity.Bestellposten;
import de.hsos.entity.Kunde;
import de.hsos.entity.Zutat;
import de.hsos.gateway.DTO.AdresseJPAEntity;
import de.hsos.gateway.DTO.BestellpostenJPAEntity;
import de.hsos.gateway.DTO.KundeJPAEntity;
import de.hsos.gateway.DTO.ZutatJPAEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConverterGateway {
    public static Kunde kundeJPAToKunde(KundeJPAEntity kundeJPAEntity){
        return new Kunde(kundeJPAEntity.getVorname(), kundeJPAEntity.getNachname(),
                adresseJPAToAdresse(kundeJPAEntity.getAdresse()), kundeJPAEntity.id );
    }

    public static de.hsos.entity.Adresse adresseJPAToAdresse(AdresseJPAEntity adresseJPAEntity){
        return new de.hsos.entity.Adresse(adresseJPAEntity.getPlz(), adresseJPAEntity.getOrt(), adresseJPAEntity.getStrasse(), adresseJPAEntity.getHausnummer());
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
