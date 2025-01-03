package de.hsos.Kunde.boundary;

import de.hsos.Kunde.entity.Adresse;
import de.hsos.Kunde.boundary.DTO.AddKundeDTO;
import de.hsos.Kunde.boundary.DTO.AdresseDTO;
import de.hsos.Kunde.boundary.DTO.ReturnKundeDTO;
import de.hsos.Kunde.control.KundenService;
import de.hsos.Kunde.entity.Kunde;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@RequestScoped
@Path("/kunden")
public class KundenResource {
    @Inject
    KundenService kundenService;

    @GET
    public Response getKunden() {
        Collection<Kunde> collection = kundenService.kundenAbfragen();
        Collection<ReturnKundeDTO> returnCollection = collection.stream().map(ConverterBoundary::kundeToReturnKundeDTO).toList();
        return Response.ok(returnCollection).build();
    }

    @POST
    public Response addKunden(AddKundeDTO addKundeDTO) {
        kundenService.kundeAnlegen(addKundeDTO.vorname(), addKundeDTO.nachname());
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getKundenById(@PathParam("id") long id) {
        Kunde kunde = kundenService.getKunde(id);
        return Response.ok(ConverterBoundary.kundeToReturnKundeDTO(kunde)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteKundenById(@PathParam("id") long id) {
        kundenService.deleteKunde(id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response addAdresse(@PathParam("id") long id, AdresseDTO adresseDTO) {
        kundenService.adresseAnlegen(id, ConverterBoundary.adresseDTOToAdresse(adresseDTO));
        return Response.ok().build();
    }

    @PATCH
    @Path("{id}")
    public Response updateAdresse(@PathParam("id") long id, AdresseDTO adresseDTO) {
        kundenService.adresseAendern(id, adresseDTO);
        return Response.ok().build();
    }

    @GET
    @Path("{id}/adresse")
    public Response getAdresse(@PathParam("id") long id) {
        Adresse adresse = kundenService.getAdresse(id);
        return Response.ok(ConverterBoundary.adresseToAdresseDTO(adresse)).build();
    }

    @DELETE
    @Path("{id}/adresse")
    public Response deleteAdresse(@PathParam("id") long id) {
        boolean isSuccess = kundenService.deleteAdresse(id);
        return isSuccess ? Response.ok().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }
}
