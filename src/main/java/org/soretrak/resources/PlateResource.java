package org.soretrak.resources;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import org.soretrak.entities.Plate;
import org.soretrak.entities.PlateFormData;
import org.soretrak.repositories.PlateRepository;

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
import java.util.List;

@Path("/api")
public class PlateResource {
    private Logger LOGGER = Logger.getLogger(PlateResource.class);

    @Inject
    PlateRepository plateRepository;
    
  

    @GET
    @Path("plate")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAll() throws IOException, SQLException {

        LOGGER.info("getting all plates data ");
        // Retrieve the image data from the database
        List<Plate> plates = plateRepository.getPlates();

        // long nber = plateRepository.find("plateNumber", plateNumber).count();

        // // If image data is null, return a 404 Not Found response
        // if (nber == 0) {
        //     return Response.status(Response.Status.NOT_FOUND).build();
        // }

        // Return the image data as a response
        return Response.ok(plates).build();

    }

    @GET
    @Path("plate/{platenumber}")
    @Produces("image/jpeg") // Adjust this based on the image format
    @Transactional
    public Response downloadImage1(@PathParam("platenumber") String plateNumber) throws IOException, SQLException {
        // Retrieve the image data from the database
        Plate plate = plateRepository.find("plateNumber", plateNumber).firstResult();


        long nber = plateRepository.find("plateNumber", plateNumber).count();

        // If image data is null, return a 404 Not Found response
        if ( nber ==0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Return the image data as a response
        return Response.ok(plate.getImageData()).build();
        
    }

    @POST
    @Path("plate")
    // @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=utf-8")
    @Transactional
    public Response uploadData(@MultipartForm PlateFormData form) {
        LOGGER.info("inserting plate data ");
        try {
            // // Read the image data from the input stream
            // byte[] imageData = imageDataStream.readAllBytes();

            // Read the image data from the form
            InputStream imageDataStream = form.getFile();
            byte[] imageData = imageDataStream.readAllBytes();

            LOGGER.info("inserting plate data " + form.toString());

            // Save the image data in the database
            plateRepository.savePlateData(imageData, form.getPlateNumber());

            return Response.status(Response.Status.CREATED).build();
        } catch (IOException | SQLException e) {
            // Handle exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

  
}
