package de.hsos.boundary;

import de.hsos.entity.Bestellposten;
import de.hsos.entity.Bestellung;
import de.hsos.entity.Zutat;

public class SumUpPriceService {

    public static double bestellpostenPreis(Bestellposten bestellposten) {
        double preis = 0;
        for (Zutat z : bestellposten.getZutaten()){
            preis += z.preis();
        }
        preis *= bestellposten.getAnzahl();
        return preis;
    }

    public static double bestellungPreis(Bestellung bestellung){
        double preis = 0;
        for (Bestellposten b : bestellung.bestellposten()){
            preis += bestellpostenPreis(b);
        }
        return preis;
    }
}
