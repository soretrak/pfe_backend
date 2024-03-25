package org.soretrak.resources;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import org.soretrak.entities.Employee;
import org.soretrak.entities.EmployeeFormData;
import org.soretrak.entities.EmployeeData;

import org.soretrak.repositories.EmployeeRepository;

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

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private Logger LOGGER = Logger.getLogger(EmployeeResource.class);
    

    @Inject
    EmployeeRepository employeeRepository;

    
    @GET
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getEmployees() {
        LOGGER.debug("get  employees " );

        List<Employee> employees = employeeRepository.getEmployees();

        return Response.ok(employees).build();

    }


    @POST
    @Path("/employee")
    @Transactional
    @Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=utf-8")
    public Response addPhoto(@MultipartForm EmployeeFormData formData)
            throws SerialException, SQLException, IOException {
        LOGGER.info("create employee data ");

        byte[] imageData = formData.file.readAllBytes();
        LOGGER.info("taille photo " + imageData.length);

        EmployeeData data = new EmployeeData();
        data.matric = formData.matric;
        data.prenom = formData.prenom;
        data.nom = formData.nom;
        data.file = imageData;

        String result = employeeRepository.createPhotov2(data);
        if (result.compareTo("Record Inserted successsfully") == 0) {
            return Response.status(Status.CREATED).build();
        }
        LOGGER.error("The employee has not been saved inside the database");
        return Response.status(Status.NOT_FOUND).build();

    }

    @GET
    @Path("/employee/{matric}")
    @Produces(MediaType.APPLICATION_JSON)
   
    public Response getEmployeeByMatric(@PathParam("matric") Integer matric) {
        LOGGER.debug("get data employee " + matric);

        Employee employee = employeeRepository.getEmployeeByMatric(matric);
        if(employee.getMatric() !=null) {
            return Response.ok(employee).build();
        }
        return Response.status(Status.NOT_FOUND).build();
     

    }

    @GET
    @Path("/employeephoto/{matric}")
  
    @Produces("image/jpeg")
    public Response getimage(@PathParam("matric") Integer matric) {
        LOGGER.debug("get photo employee " + matric);
        
        byte[] agentPhoto = employeeRepository.getAgentImage(matric);
        
        return Response.ok(agentPhoto).build();
        

    }
    
  
}
