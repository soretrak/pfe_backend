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
@Table(name = "palte")
public class Plate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "date_insert", columnDefinition = "Date DEFAULT sysdate", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date dateInsert;

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;


    public Plate() {
       
    }
    
   



    public Plate(Long id, String plateNumber, Date dateInsert) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.dateInsert = dateInsert;
    }

    public Plate(Long id, String plateNumber, Date date_presence, byte[] imageData) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.dateInsert = date_presence;
        this.imageData = imageData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Date date_presence) {
        this.dateInsert = date_presence;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }




   
    
}
