/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


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



}
