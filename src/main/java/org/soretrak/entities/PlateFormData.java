package org.soretrak.entities;

import java.io.InputStream;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class PlateFormData {
    @FormParam("plateNumber")
    public String plateNumber;


    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;



    
    public PlateFormData() {
    }


    public PlateFormData(String plateNumber, InputStream file) {
        this.plateNumber = plateNumber;
        this.file = file;
    }


    public String getPlateNumber() {
        return plateNumber;
    }


    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    public InputStream getFile() {
        return file;
    }


    public void setFile(InputStream file) {
        this.file = file;
    }


    
}
