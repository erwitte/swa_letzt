package de.hsos.Kunde.gateway;

import de.hsos.Kunde.boundary.DTO.AdresseDTO;
import de.hsos.Kunde.control.KundenService;
import de.hsos.Kunde.entity.Adresse;
import de.hsos.Kunde.entity.Kunde;
import de.hsos.Kunde.gateway.DTO.AdresseJPAEntity;
import de.hsos.Kunde.gateway.DTO.KundeJPAEntity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestScoped
public class KundenRepository implements KundenService {

    @Transactional
    @Override
    public void kundeAnlegen(String vorname, String nachanme) {
        KundeJPAEntity neuerKundeJPAEntity = new KundeJPAEntity(vorname, nachanme);
        neuerKundeJPAEntity.persist();
    }

    @Override
    public Collection<Kunde> kundenAbfragen() {
        return KundeJPAEntity.listAll().stream()
                .map(k -> (KundeJPAEntity) k)
                .map(ConverterGateway::kundeJPAToKunde)
                .collect(Collectors.toList());
    }

    @Override
    public Kunde getKunde(long id) {
        KundeJPAEntity kundeJPAEntity = KundeJPAEntity.findById(id);
        if (kundeJPAEntity == null) {
            return null;
        }
        return ConverterGateway.kundeJPAToKunde(kundeJPAEntity);
    }

    @Override
    @Transactional
    public boolean deleteKunde(long id) {
        KundeJPAEntity kundeJPAEntity = KundeJPAEntity.findById(id);
        if (kundeJPAEntity != null) {
            kundeJPAEntity.delete();
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void adresseAnlegen(long kundenId, AdresseDTO adresse) {
        KundeJPAEntity kundeJPAEntity = KundeJPAEntity.findById(kundenId);
        if (kundeJPAEntity != null) {
            kundeJPAEntity.setAdresse(ConverterGateway.adresseToAdresseJPA(adresse));
        }
    }

    @Transactional
    @Override
    public void adresseAendern(long kundenId, AdresseDTO adresse) {
        KundeJPAEntity kundeJPAEntity = KundeJPAEntity.findById(kundenId);
        if (kundeJPAEntity != null) {
            AdresseJPAEntity adresseJPAEntity = kundeJPAEntity.getAdresse();
            if (!adresse.plz().equals("string")) {
                adresseJPAEntity.setPlz(adresse.plz());
            }
            if (!adresse.ort().equals("string")) {
                adresseJPAEntity.setOrt(adresse.ort());
            }
            if (!adresse.strasse().equals("string")) {
                adresseJPAEntity.setStrasse(adresse.strasse());
            }
            if (!adresse.hausnummer().equals("string")) {
                adresseJPAEntity.setHausnummer(adresse.hausnummer());
            }
            kundeJPAEntity.setAdresse(adresseJPAEntity);
        }
    }

    @Override
    public Adresse getAdresse(long id) {
        KundeJPAEntity kundeJPAEntity = KundeJPAEntity.findById(id);
        if (kundeJPAEntity == null) {
            return null;
        }
        AdresseJPAEntity adresseJPAEntity = kundeJPAEntity.getAdresse();
        return ConverterGateway.adresseJPAToAdresse(adresseJPAEntity);
    }

    @Override
    public boolean deleteAdresse(long id) {
        KundeJPAEntity kundeJPAEntity = KundeJPAEntity.findById(id);
        if (kundeJPAEntity != null) {
            kundeJPAEntity.setAdresse(null);
            return true;
        }
        return false;
    }
}
