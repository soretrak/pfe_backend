package org.soretrak.resources;


import org.jboss.logging.Logger;
import org.soretrak.entities.Presence;
import org.soretrak.repositories.PresenceRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.net.URI;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PresenceResource {

    private Logger LOGGER = Logger.getLogger(PresenceResource.class);


    @Inject
    PresenceRepository presenceRepository; 

    @GET
    @Path("/presence")
    public Response getAllPresences() {
        LOGGER.debug("Get all banks inside the database");

        List<Presence> presences = presenceRepository.listAll();
        return Response.ok(presences).build();

    }

  
    @GET
    @Path("/presence/{matric}")
    public Response getPresenceMatric(@PathParam("matric") Long id) {
        LOGGER.debug("Get the author with id " + id);
        // return presenceRepository.findByIdOptional(id)
        //         .map(presence -> Response.ok(
        //                 presence).build())
        //         .orElse(Response.status(Status.NOT_FOUND).build());
        List<Presence> presences = presenceRepository.find("matric", id).list();

        return Response.ok(
                presences).build();

    }

    @POST
    @Transactional
    @Path("/presence")
    public Response savePresence(Presence presence) {
        LOGGER.info("saving encodings inside the database");
        presenceRepository.persist(presence);

        if (presenceRepository.isPersistent(presence)) {
            LOGGER.info("the encoding is saved  : " + presence.getMatric());
            return Response.created(URI.create("/presence/" + presence.getIdPresence())).build();

        }
        LOGGER.error("The author has not been saved inside the database");
        return Response.status(Status.NOT_FOUND).build();

    }
    
}
