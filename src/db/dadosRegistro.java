/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.registro;
/**
 *
 * @author gbarrosn
 */
public class dadosRegistro {
    
    public List<registro> buscarRegistros() throws SQLException {
        List<registro> registros = new ArrayList<>();
        
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to retrieve the registros
        String query = "SELECT * FROM registros";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);
            
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve the data from the result set
                int idRegistro = resultSet.getInt("id");
                String setor = resultSet.getString("setor");
                String turno = resultSet.getString("turno");
                String funcao = resultSet.getString("funcao");
                int idFuncionario = resultSet.getInt("id_funcionario");
                String horaEntrada = resultSet.getString("hora_entrada");
                String saidaAlmoco = resultSet.getString("saida_almoco");
                String retornoAlmoco = resultSet.getString("retorno_almoco");
                String horaSaida = resultSet.getString("hora_saida");
                String data = resultSet.getString("data");

                // Create a new Registro object
                registro registro = new registro(idRegistro, setor, turno, funcao, idFuncionario, horaEntrada, saidaAlmoco, retornoAlmoco, horaSaida, data);
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return registros;
    }
}
