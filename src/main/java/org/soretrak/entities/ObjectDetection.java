package org.soretrak.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "detection")
public class ObjectDetection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "camera_id")
    private String cameraId;

    @Column(name = "date_insert", columnDefinition = "Date DEFAULT sysdate", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date dateInsert;

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;



    
    public ObjectDetection() {
    }

    public ObjectDetection(Long id, String cameraId, Date dateInsert) {
        this.id = id;
        this.cameraId = cameraId;
        this.dateInsert = dateInsert;
    }

    public ObjectDetection(Long id, String cameraId, Date dateInsert, byte[] imageData) {
        this.id = id;
        this.cameraId = cameraId;
        this.dateInsert = dateInsert;
        this.imageData = imageData;
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "ObjectDetection [id=" + id + ", cameraId=" + cameraId + ", dateInsert=" + dateInsert + "]";
    }

    
    
}
