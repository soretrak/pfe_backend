package org.soretrak.entities;

public class Employee {
    public Integer matric;
    public String prenom;
    public String nom;

    
    public Employee() {
    }
    public Employee(Integer matric, String prenom, String nom) {
        this.matric = matric;
        this.prenom = prenom;
        this.nom = nom;
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
    


    
}
