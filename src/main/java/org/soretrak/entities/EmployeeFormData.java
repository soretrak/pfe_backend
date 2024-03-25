package org.soretrak.entities;

import java.io.InputStream;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class EmployeeFormData {
    @FormParam("matric")
    public Integer matric;


    @FormParam("prenom")
    public String prenom;
    @FormParam("nom")
    public String nom;

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

    
    
    public EmployeeFormData() {
    }

    public EmployeeFormData(Integer matric, String prenom, String nom, InputStream file) {
        this.matric = matric;
        this.prenom = prenom;
        this.nom = nom;
        this.file = file;
    }

    public Integer getMatric() {
        return matric;
    }

    public void setMatric(Integer matric) {
        this.matric = matric;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    

    
}
