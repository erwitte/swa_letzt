package de.hsos.Bestellung.gateway;

import de.hsos.Bestellung.control.BestellungenService;
import de.hsos.Bestellung.gateway.DTO.BestellpostenJPAEntity;
import de.hsos.Bestellung.gateway.DTO.BestellungJPAEntity;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class BestellungenRepository implements BestellungenService {

    @Override
    public int bestellungAnlegen() {
        BestellungJPAEntity bestellung = new BestellungJPAEntity();
        bestellung.persist();
        long idLong = bestellung.id;
        return (int) idLong;
    }

    @Override
    public List<BestellpostenJPAEntity> getBestellung(long bestellungId) {
        BestellungJPAEntity bestellung = BestellungJPAEntity.findById(bestellungId);
        return bestellung.getBestellposten();
    }

    @Override
    public void bestellungLoeschen(long bestellungId) {
        BestellungJPAEntity.deleteById( bestellungId);
    }

    @Override
    public long bestellpostenErzeugen(long bestellungId) {
        BestellungJPAEntity bestellung = BestellungJPAEntity.findById(bestellungId);
        BestellpostenJPAEntity post = new BestellpostenJPAEntity();
        bestellung.addBestellposten(post);
        bestellung.persist();
        bestellung.getEntityManager().flush();
        return post.id;
    }
}
