package org.soretrak.entities;

public class EmployeeData {
    
    public Integer matric;
    public String prenom;
    public String nom;
   
    public byte[] file;


    public EmployeeData() {
    }


    public EmployeeData(Integer matric, String prenom, String nom, byte[] file) {
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


    public byte[] getFile() {
        return file;
    }


    public void setFile(byte[] file) {
        this.file = file;
    }

    
    
}
