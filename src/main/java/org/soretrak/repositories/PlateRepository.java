package org.soretrak.repositories;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class PlateRepository implements PanacheRepository<Plate>{

    @Inject
    EntityManager entityManager;

    @Inject
    DataSource dataSource;

    public List<Plate> getPlates() {

        List<Plate> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sqlQuery = " select id, plate_number , date_insert from palte order by date_insert asc ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        Plate row = new Plate(
                                resultSet.getLong("id"),
                                resultSet.getString("plate_number"),
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
    public void savePlateDataSingle(byte[] imageData, String plateNumber) throws SQLException {
        // Create an ImageEntity
        Plate plate = new Plate();
        plate.setImageData(imageData);
        plate.setPlateNumber(plateNumber);

        // Persist the entity
        entityManager.persist(plate);
        entityManager.flush();
    }

    @Transactional
    public void savePlateData(byte[] imageData, String plateNumber) throws SQLException {
        // Create an ImageEntity
        Plate plate = new Plate();
        plate.setImageData(imageData);
        plate.setPlateNumber(plateNumber);

        // Persist the entity
        entityManager.persist(plate);
        entityManager.flush();
    }

    @Transactional
    public InputStream getImageData(Long id) throws SQLException {
        // Retrieve the ImageEntity from the database
        Plate imageEntity = entityManager.find(Plate.class, id);

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
