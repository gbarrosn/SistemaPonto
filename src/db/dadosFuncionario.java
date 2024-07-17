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

//import com.mysql.cj.xdevapi.Statement;

import model.funcionario;
/**
 *
 * @author gbarrosn
 */
public class dadosFuncionario {

    // funcao que lista todos os funcionarios e retorna uma lista dos mesmos
    public List<funcionario> buscarFuncionarios() throws SQLException {
        List<funcionario> funcionarios = new ArrayList<>();
        
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to retrieve the funcionarios
        String query = "SELECT * FROM funcionarios";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);
            
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve the data from the result set
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String matricula = resultSet.getString("matricula");
                String setor = resultSet.getString("setor");
                String turno = resultSet.getString("turno");
                String funcao = resultSet.getString("funcao");
                String dataAdmissao = resultSet.getString("data_admissao");
                String escala = resultSet.getString("escala");
                String horario = resultSet.getString("horario");
                String horasSemanais = resultSet.getString("horas_semanais");
                String codigoDeBarras = resultSet.getString("codigo_de_barras");
                String senha = resultSet.getString("senha");

                // Retrieve other fields as needed
                
                // Create a new Funcionario object
                funcionario funcionario = new funcionario(id, nome, matricula, setor, turno, funcao, dataAdmissao, escala, horario, horasSemanais, codigoDeBarras, senha);
                // Set other fields as needed
                
                // Add the funcionario to the list
                funcionarios.add(funcionario);
            }
            
            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return funcionarios;
    }

}
