package org.soretrak.entities;

import java.io.InputStream;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;

public class ObjectFormData {
    @FormParam("cameraId")
    public String cameraId;

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream file;

    

    public ObjectFormData(String cameraId, InputStream file) {
        this.cameraId = cameraId;
        this.file = file;
    }

    public ObjectFormData() {
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "ObjectFormData [cameraId=" + cameraId + "]";
    }


    
}
