package de.hsos.boundary;

import de.hsos.boundary.DTOs.ZutatDTO;
import de.hsos.control.BestellungenService;
import de.hsos.entity.BestellpostenPizza;
import de.hsos.gateway.DTO.BestellpostenJPAEntity;
import de.hsos.gateway.DTO.BestellungJPAEntity;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@RequestScoped
@Path("/bestellungen")
public class BestellungResource
{
    List<ZutatDTO> alleZutaten = List.of(
            new ZutatDTO("Käse", 1.5, 1),
            new ZutatDTO("Pilze", 2, 2),
            new ZutatDTO("Sucuk", 2, 3),
            new ZutatDTO("Ei", 2, 4),
            new ZutatDTO("Zwiebeln", 2, 5),
            new ZutatDTO("Hollandaise", 2, 6),
            new ZutatDTO("Kebabfleisch", 2, 7)
    );
    String[] alleStandardPizzen = new String[]{"Margherita", "Funghi", "Sucuk", "Sucuk mit Ei", "Kebab"};
    @Inject
    BestellungenService bestellungenService;
    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {
        public static native TemplateInstance index(List<BestellpostenPizza> pizzas,List<ZutatDTO> zutaten);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance est(){
        List<BestellpostenPizza> pizzas = List.of(
                new BestellpostenPizza("Margherita", 6.50),
                new BestellpostenPizza("Funghi", 7.50),
                new BestellpostenPizza("Sucuk", 8.00),
                new BestellpostenPizza("Sucuk mit Ei", 9.00),
                new BestellpostenPizza("Kebab", 10.00)
        );
        return Templates.index(pizzas, alleZutaten);
    }

    @POST
    @Transactional
    public Response addBestellung(){
        int bestellungId = bestellungenService.bestellungAnlegen();
        System.out.println(bestellungId);
        return Response.ok(bestellungId).build();
    }

    @GET
    @Path("/all")
    public Response getBestellungen(){
        return Response.ok(BestellungJPAEntity.listAll()).build();
    }

    @GET
    @Path("/{bestellungId}")
    @Produces
    public Response getBestellung(@PathParam("bestellungId") long bestellungId){
        try {
            List<BestellpostenJPAEntity> posten = bestellungenService.getBestellung(bestellungId);
            return Response.ok(posten).build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{bestellungId}")
    public Response deleteBestellung(@PathParam("bestellungId") long bestellungId){
        try {
            bestellungenService.bestellungLoeschen(bestellungId);
            return Response.ok().build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{bestellungId}")
    public Response bestellpostenErzeugen(@PathParam("bestellungId") long bestellungId){
        try {
            long postenId = bestellungenService.bestellpostenErzeugen(bestellungId);
            return Response.ok(postenId).build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    @Path("/{bestellungId}")
    public Response bestellungCheckout(@PathParam("bestellungId") long bestellungId){
        // unsauber, aber sollte ausreichen da kein wirklicher geschäftsfall
        try {
            bestellungenService.bestellungLoeschen(bestellungId);
            return Response.ok().build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/zutaten")
    @GET
    public Response getZutaten(){
        return Response.ok().entity(alleZutaten).build();
    }

    @GET
    @Path("/pizzen")
    public Response getPizzen(){
        return Response.ok().entity(alleStandardPizzen).build();
    }

    @GET
    @Path("/{bestellungId}")
    public Response getBestellungById(@PathParam("bestellungId") long bestellungId){
        try {
            BestellungJPAEntity bestellung = BestellungJPAEntity.findById(bestellungId);
            return Response.ok().entity(bestellung.getBestellposten()).build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
