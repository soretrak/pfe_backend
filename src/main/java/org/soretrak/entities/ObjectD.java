package org.soretrak.entities;

import java.util.Date;



public class ObjectD {
   
    private Long id;

    
    private String cameraId;

   
    public Date dateInsert;


    public ObjectD() {
    }


    public ObjectD(Long id, String cameraId, Date dateInsert) {
        this.id = id;
        this.cameraId = cameraId;
        this.dateInsert = dateInsert;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getCameraId() {
        return cameraId;
    }


    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }


    public Date getDateInsert() {
        return dateInsert;
    }


    public void setDateInsert(Date dateInsert) {
        this.dateInsert = dateInsert;
    }


    
}
