package org.soretrak.resources;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.soretrak.entities.ObjectD;
import org.soretrak.entities.ObjectDetection;
import org.soretrak.entities.ObjectFormData;
import org.soretrak.entities.Plate;
import org.soretrak.entities.PlateFormData;
import org.soretrak.repositories.ObjectDetectionRepository;
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
public class ObjectDetectionResource {
    
    private Logger LOGGER = Logger.getLogger(ObjectDetectionResource.class);

    @Inject
    ObjectDetectionRepository objectRepository;

    @GET
    @Path("object_detection")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAll() throws IOException, SQLException {

        LOGGER.info("getting all object detection data ");
        // Retrieve the image data from the database
        List<ObjectDetection> objects = objectRepository.getObjects();

        // long nber = plateRepository.find("plateNumber", plateNumber).count();

        // // If image data is null, return a 404 Not Found response
        // if (nber == 0) {
        // return Response.status(Response.Status.NOT_FOUND).build();
        // }

        // Return the image data as a response
        return Response.ok(objects).build();

    }

    @GET
    @Path("object_detection_day/{date_jour}")
    @Produces(MediaType.APPLICATION_JSON)
   
    public Response getObjectByDate(@PathParam("date_jour") String datej) throws IOException, SQLException {

        LOGGER.info("getting all object detection data ");
        // Retrieve the image data from the database
        List<ObjectD> objects = objectRepository.getObjectsByDate(datej);

        // long nber = plateRepository.find("plateNumber", plateNumber).count();

        // // If image data is null, return a 404 Not Found response
        // if (nber == 0) {
        // return Response.status(Response.Status.NOT_FOUND).build();
        // }

        // Return the image data as a response
        return Response.ok(objects).build();

    }

    @GET
    @Path("object_detection/{id}")
    @Produces("image/jpeg") // Adjust this based on the image format
   
    public Response downloadImage1(@PathParam("id") String id) throws IOException, SQLException {
        // Retrieve the image data from the database
        ObjectDetection object = objectRepository.find("id", id).firstResult();

        // long nber = objectRepository.find("plateNumber", plateNumber).count();

        // // If image data is null, return a 404 Not Found response
        // if (nber == 0) {
        //     return Response.status(Response.Status.NOT_FOUND).build();
        // }

        // Return the image data as a response
        return Response.ok(object.getImageData()).build();

    }

    @POST
    @Path("object_detection")
    // @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=utf-8")
    @Transactional
    public Response uploadData(@MultipartForm ObjectFormData form) {
        LOGGER.info("inserting object data ");
        try {
            // // Read the image data from the input stream
            // byte[] imageData = imageDataStream.readAllBytes();

            // Read the image data from the form
            InputStream imageDataStream = form.getFile();
            byte[] imageData = imageDataStream.readAllBytes();

            LOGGER.info("inserting object data " + form.toString());

            // Save the image data in the database
            objectRepository.saveObjectData(imageData, form.getCameraId());

            return Response.status(Response.Status.CREATED).build();
        } catch (IOException | SQLException e) {
            // Handle exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
