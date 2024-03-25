package org.soretrak.repositories;

import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.soretrak.entities.Employee;
import org.soretrak.entities.EmployeeData;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeRepository {
    

    @Inject
    DataSource dataSource;

    public Integer createPhoto(EmployeeData data) throws SQLException {

        int result = 0;

        try (Connection connection = dataSource.getConnection()) {

            String sqlInsert = " insert into agent_photo(matric, photo) values (?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {

                preparedStatement.setInt(1, data.matric);
                preparedStatement.setBinaryStream(2, new ByteArrayInputStream(data.file), data.file.length);

                result = preparedStatement.executeUpdate();
                

            } catch (SQLException e) {
                // Handle exceptions
                e.printStackTrace();
            }

        }
        System.out.println(result);
        return result;
    }


    public String createPhotov2(EmployeeData data) {

        String result = "";

        try (Connection connection = dataSource.getConnection()) {
           
            String sqlQuery = "{ ? = call INSERT_IMAGE(?,?,?,?) }";
            CallableStatement statement = connection.prepareCall(sqlQuery);

            statement.registerOutParameter(1, Types.VARCHAR);

            statement.setInt(2, data.matric);
            statement.setString(3, data.prenom);
            statement.setString(4, data.nom);
            statement.setBinaryStream(5, new ByteArrayInputStream(data.file), data.file.length);

            

            statement.execute();

            result = statement.getString(1);

           

        } catch (SQLException e) {
          
            e.printStackTrace();
        }

        return result;
    }

    public Employee getEmployeeByMatric(Integer matric) {
        Employee result = new Employee();

        try (Connection connection = dataSource.getConnection()) {

            String sqlQuery = "  select matric ,prenom , nom  from employees where matric = ? ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, matric);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        result.setMatric(resultSet.getInt("matric"));
                        result.setPrenom(resultSet.getString("prenom"));
                        result.setNom(resultSet.getString("nom"));

                      

                    }
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return result;
    }

    public List<Employee> getEmployees() {

        List<Employee> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sqlQuery = " select matric ,prenom , nom  from employees  ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {

                        Employee row = new Employee(
                                resultSet.getInt("matric"),
                                resultSet.getString("prenom"),
                                resultSet.getString("nom")

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

    public byte[] getAgentImage(Integer matric) {
        byte[] result = null;

        try (Connection connection = dataSource.getConnection()) {

            String sqlQuery = " select  'image/png'  mimetype ,photo from employees where matric = ? ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setInt(1, matric);
                

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                      
                        result = resultSet.getBytes("photo");

                    }
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return result;
    }


    // public List<byte[]> getAgentsImages() {
    //     List<byte[]> result = new ArrayList<>();

    //     try (Connection connection = dataSource.getConnection()) {

    //         String sqlQuery = " select  'image/png'  mimetype ,photo from agent_photo  ";

    //         try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

    //             // preparedStatement.setInt(1, matric);

    //             try (ResultSet resultSet = preparedStatement.executeQuery()) {
    //                 while (resultSet.next()) {

    //                     result.add(resultSet.getBytes("photo"));

    //                 }
    //             }
    //         }
    //     } catch (SQLException e) {
    //         // Handle exceptions
    //         e.printStackTrace();
    //     }

    //     return result;
    // }

}
