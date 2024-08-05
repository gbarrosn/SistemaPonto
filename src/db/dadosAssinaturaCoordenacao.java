/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.assinaturaCoordenacao;


/**
 *
 * @author gbarrosn
 */
public class dadosAssinaturaCoordenacao {
    
    public static void registrarAssinaturaCoordenacao(int idCoordenacao, int idFuncionario, String dataAssinatura, String horaAssinatura, int mes) {
        // Connect to the database
        Connection connection = null;
        try {
            connection = conectarBanco.conectar();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // Create a query to insert the assinatura
        String query = "INSERT INTO assinaturaCoordenacao (id_coordenacao, id_funcionario, data_assinatura, hora_assinatura, mes) VALUES (" + idCoordenacao + ", " + idFuncionario +  ", '" + dataAssinatura + "', '" + horaAssinatura + "', " + mes +")";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();

        }
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
