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

import model.assinaturaCoordenacao;


/**
 *
 * @author gbarrosn
 */
public class dadosAssinaturaCoordenacao {
    
    public static void registrarAssinaturaCoordenacao(int idCoordenacao, int idFuncionario, String dataAssinatura, String horaAssinatura, int mes) throws SQLException {
    // Connect to the database
        Connection connection = conectarBanco.conectar();

        // Verificar se a assinatura já existe
        String checkQuery = "SELECT * FROM assinaturaCoordenacao WHERE id_coordenacao = ? AND id_funcionario = ? AND mes = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
        checkStatement.setInt(1, idCoordenacao);
        checkStatement.setInt(2, idFuncionario);
        checkStatement.setInt(3, mes);
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next()) {
            // Assinatura já existe, então atualizamos
            String updateQuery = "UPDATE assinaturaCoordenacao SET data_assinatura = ?, hora_assinatura = ? WHERE id_coordenacao = ? AND id_funcionario = ? AND mes = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, dataAssinatura);
            updateStatement.setString(2, horaAssinatura);
            updateStatement.setInt(3, idCoordenacao);
            updateStatement.setInt(4, idFuncionario);
            updateStatement.setInt(5, mes);
            updateStatement.executeUpdate();
        } else {
            // Assinatura não existe, então inserimos
            String insertQuery = "INSERT INTO assinaturaCoordenacao (id_coordenacao, id_funcionario, data_assinatura, hora_assinatura, mes) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, idCoordenacao);
            insertStatement.setInt(2, idFuncionario);
            insertStatement.setString(3, dataAssinatura);
            insertStatement.setString(4, horaAssinatura);
            insertStatement.setInt(5, mes);
            insertStatement.executeUpdate();
        }

        // Feche os recursos
        resultSet.close();
        checkStatement.close();
        connection.close();
    }

    public static assinaturaCoordenacao buscarAssinaturaCoordenacao(int idFuncionario, int mes) throws SQLException {
        Connection connection = conectarBanco.conectar();

        String query = "SELECT * FROM assinaturaCoordenacao WHERE id_funcionario = " + idFuncionario + " AND mes = " + mes;

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                assinaturaCoordenacao assinaturaCoordenacao = new assinaturaCoordenacao();
                assinaturaCoordenacao.setIdAssinatura(resultSet.getInt("id"));
                assinaturaCoordenacao.setIdFuncionario(resultSet.getInt("id_funcionario"));
                assinaturaCoordenacao.setIdCoordenacao(resultSet.getInt("id_coordenacao"));
                assinaturaCoordenacao.setMes(resultSet.getInt("mes"));
                assinaturaCoordenacao.setDataAssinatura(resultSet.getString("data_assinatura"));
                assinaturaCoordenacao.setHoraAssinatura(resultSet.getString("hora_assinatura"));

                return assinaturaCoordenacao;
            }

            resultSet.close();
            statement.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
        
    }
}
