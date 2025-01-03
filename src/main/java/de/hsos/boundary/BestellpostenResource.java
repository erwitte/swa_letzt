package de.hsos.boundary;

import de.hsos.boundary.DTOs.AnzahlPizzaDTO;
import de.hsos.boundary.DTOs.StandardpizzaDTO;
import de.hsos.boundary.DTOs.ZutatenDTO;
import de.hsos.control.BestellpostenService;
import de.hsos.gateway.DTO.BestellpostenJPAEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

@Path("/bestellposten")
public class BestellpostenResource {
    @Inject
    BestellpostenService bestellpostenService;

    @GET
    @Path("/{bestellpostenId}")
    public Response getBestellposten(@PathParam("bestellpostenId") long bestellpostenId) {
        return Response.ok(BestellpostenJPAEntity.findById(bestellpostenId)).build();
    }

    @PUT
    @Transactional
    @Path("/{bestellpostenId}")
    public Response addZutatZuBestellposten(@PathParam("bestellpostenId") int bestellpostenId,
                                            ZutatenDTO zutatenDTO){
        try {
            bestellpostenService.addZutatZuBestellposten(bestellpostenId, zutatenDTO.zutaten());
            return Response.ok().build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{bestellpostenId}")
    public Response deleteZutatVonBestellposten(@PathParam("bestellpostenId") int bestellpostenId,
                                                ZutatenDTO zutatenDTO){
        bestellpostenService.deleteZutatVonBestellposten(bestellpostenId, zutatenDTO.zutaten());
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @Path("{bestellungId}/{bestellpostenId}")
    public Response deleteBestellsposten(@PathParam("bestellungId") long bestellungId,
                                         @PathParam("bestellpostenId") long bestellpostenId){
        bestellpostenService.deleteBestellposten(bestellungId, bestellpostenId);
        return Response.ok().build();
    }

    @PATCH
    @Transactional
    @Path("/{bestellpostenId}")
    public Response anzahlBestllpostenAendern(@PathParam("bestellpostenId") int bestellpostenId,
                                              AnzahlPizzaDTO anzahlPizzaDTO){
        bestellpostenService.anzahlInBestllpostenAendern(bestellpostenId, anzahlPizzaDTO.anzahl());
        return Response.ok().build();
    }

    @PUT
    @Transactional
    @Path("{bestellpostenId}/standardpizzen")
    public Response addStandardPizzaToBestellposten(@PathParam("bestellpostenId") int bestellpostenId,
                                                    StandardpizzaDTO standardpizzaDTO) {
        try {
            switch (standardpizzaDTO.pizza()) {
                case "Margharita":
                    bestellpostenService.addZutatZuBestellposten(bestellpostenId, Collections.singletonList("Käse"));
                    break;
                case "Funghi":
                    bestellpostenService.addZutatZuBestellposten(bestellpostenId, List.of(new String[]{"Käse", "Pilze"}));
                    break;
                case "Sucuk":
                    bestellpostenService.addZutatZuBestellposten(bestellpostenId, List.of(new String[]{"Käse", "Sucuk"}));
                    break;
                case "Sucuk mit Ei":
                    bestellpostenService.addZutatZuBestellposten(bestellpostenId, List.of(new String[]{"Käse", "Sucuk", "Ei"}));
                    break;
                case "Kebab":
                    bestellpostenService.addZutatZuBestellposten(bestellpostenId, List.of(new String[]{"Käse", "Hollandaise", "Zwiebeln", "Kebabfleisch"}));
                    break;
            }
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
