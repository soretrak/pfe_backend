package org.soretrak.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PRESENCE")
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presence")
    public Long idPresence;
    @Column(name = "matric")
    public Integer matric;

  

    @Column(name = "date_presence", columnDefinition = "Date DEFAULT sysdate", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date date_presence;

    @Column(name = "face_dist")
    public Float faceDist;

    

    public Presence() {
    }

   
    


    public Presence(Long idPresence, Integer matric, Date date_presence, Float faceDist) {
        this.idPresence = idPresence;
        this.matric = matric;
        this.date_presence = date_presence;
        this.faceDist = faceDist;
    }





    public Long getIdPresence() {
        return idPresence;
    }
    public void setIdPresence(Long idPresence) {
        this.idPresence = idPresence;
    }
    public Integer getMatric() {
        return matric;
    }
    public void setMatric(Integer matric) {
        this.matric = matric;
    }


    public Date getDate_presence() {
        return date_presence;
    }


    public void setDate_presence(Date date_presence) {
        this.date_presence = date_presence;
    }





    public Float getFaceDist() {
        return faceDist;
    }





    public void setFaceDist(Float faceDist) {
        this.faceDist = faceDist;
    }

    
}
