/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gbarrosn
 */
public class dadosContrato {
    
    public static void cadastrarContrato(String contrato) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();

        // Verificar se o contrato já existe
        String checkQuery = "SELECT * FROM contrato WHERE contrato = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
        checkStatement.setString(1, contrato);
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next()) {
            // Contrato já existe, então atualizamos
            String updateQuery = "UPDATE contrato SET contrato = ? WHERE contrato = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, contrato);
            updateStatement.setString(2, contrato);
            updateStatement.executeUpdate();
        } else {
            // Contrato não existe, então inserimos
            String insertQuery = "INSERT INTO contrato (contrato) VALUES (?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, contrato);
            insertStatement.executeUpdate();
        }

        // Feche os recursos
        resultSet.close();
        checkStatement.close();
        connection.close();
    }

    public static List<String> buscarContratos() throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to select the contrato
        String query = "SELECT * FROM contrato";
        List<String> contratos = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                contratos.add(resultSet.getString("contrato"));
            }

            // Feche os recursos
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contratos;
    }

}
