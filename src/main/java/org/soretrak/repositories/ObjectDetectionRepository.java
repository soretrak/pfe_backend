package org.soretrak.repositories;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.soretrak.entities.ObjectD;
import org.soretrak.entities.ObjectDetection;
import org.soretrak.entities.Plate;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;

@ApplicationScoped
public class ObjectDetectionRepository implements PanacheRepository<ObjectDetection>{
    

    @Inject
    EntityManager entityManager;

    @Inject
    DataSource dataSource;

    public List<ObjectDetection> getObjects() {

        List<ObjectDetection> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sqlQuery = " select id, camera_id , date_insert  from detection order by date_insert asc ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        ObjectDetection row = new ObjectDetection(
                                resultSet.getLong("id"),
                                resultSet.getString("camera_id"),
                                resultSet.getDate("date_insert")

                        );

                        result.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return result;
    }


    public List<ObjectD> getObjectsByDate(String datej) {

        List<ObjectD> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sqlQuery = " select id, camera_id , date_insert  from detection where to_char(date_insert,'yyyy-mm-dd') = ? order by date_insert asc ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1,  datej );

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        ObjectD row = new ObjectD(
                                resultSet.getLong("id"),
                                resultSet.getString("camera_id"),
                                resultSet.getDate("date_insert")

                        );

                        result.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return result;
    }

    @Transactional
    public void saveObjectDataSingle(byte[] imageData, String cameraId) throws SQLException {
        // Create an ImageEntity
        ObjectDetection object = new ObjectDetection();
        object.setImageData(imageData);
        object.setCameraId(cameraId);

        // Persist the entity
        entityManager.persist(object);
        entityManager.flush();
    }

    @Transactional
    public void saveObjectData(byte[] imageData, String cameraId) throws SQLException {
        // Create an ImageEntity
        ObjectDetection object = new ObjectDetection();
        object.setImageData(imageData);
        object.setCameraId(cameraId);

        // Persist the entity
        entityManager.persist(object);
        entityManager.flush();
    }

    @Transactional
    public InputStream getImageDataById(Long id) throws SQLException {
        // Retrieve the ImageEntity from the database
        ObjectDetection imageEntity = entityManager.find(ObjectDetection.class, id);

        if (imageEntity == null) {
            // If image not found, return null
            return null;
        }

        // Get the byte array containing the image data from the entity
        byte[] imageData = imageEntity.getImageData();

        // Convert the byte array to an InputStream
        return new ByteArrayInputStream(imageData);
    }
}
