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
                funcionario funcionario = new funcionario();
                funcionario.setIdFuncionario(id);
                funcionario.setNome(nome);
                funcionario.setMatricula(Integer.parseInt(matricula));
                funcionario.setSetor(setor);
                funcionario.setTurno(turno);
                funcionario.setFuncao(funcao);
                funcionario.setDataAdmissao(dataAdmissao);
                funcionario.setEscala(escala);
                funcionario.setHorario(horario);
                funcionario.setHorasSemanais(horasSemanais);
                funcionario.setCodigoDeBarras(codigoDeBarras);
                funcionario.setSenha(senha);
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

    // funcao que busca um funcionario pelo id
    public funcionario buscarFuncionarioPorId(int id) throws SQLException {
        funcionario funcionario = null;
        
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to retrieve the funcionario by id
        String query = "SELECT * FROM funcionarios WHERE id = " + id;
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);
            
            // Check if the result set has any data
            if (resultSet.next()) {
                // Retrieve the data from the result set
                String nome = resultSet.getString("nome");
                int matricula = resultSet.getInt("matricula");
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
                funcionario = new funcionario();
                funcionario.setIdFuncionario(id);
                funcionario.setNome(nome);
                funcionario.setMatricula(matricula);
                funcionario.setSetor(setor);
                funcionario.setTurno(turno);
                funcionario.setFuncao(funcao);
                funcionario.setDataAdmissao(dataAdmissao);
                funcionario.setEscala(escala);
                funcionario.setHorario(horario);
                funcionario.setHorasSemanais(horasSemanais);
                funcionario.setCodigoDeBarras(codigoDeBarras);
                funcionario.setSenha(senha);

                // Set other fields as needed
            }
            
            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return funcionario;
    }

    // funcao que insere um funcionario no banco de dados
    public static void cadastrarFuncionario(funcionario funcionario) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to insert the funcionario
        String query = "INSERT INTO funcionarios " + 
                        "(nome, matricula, setor, turno, funcao," +
                        "data_admissao, escala, horario, horas_semanais, " +
                        "codigo_de_barras, senha) " +
                        "VALUES ('" + funcionario.getNome() + "', '" + 
                        funcionario.getMatricula() + "', '" + funcionario.getSetor() + "', '" + 
                        funcionario.getTurno() + "', '" + funcionario.getFuncao() + "', '" + 
                        funcionario.getDataAdmissao() + "', '" + funcionario.getEscala() + "', '" + 
                        funcionario.getHorario() + "', '" + funcionario.getHorasSemanais() + "', '" + 
                        funcionario.getCodigoDeBarras() + "', '" + funcionario.getSenha() + "');";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
            
            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // funcao que atualiza um funcionario no banco de dados
    public void atualizarFuncionario(funcionario funcionario) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the funcionario
        String query = "UPDATE funcionarios SET " + 
                        "nome = '" + funcionario.getNome() + "', " + 
                        "matricula = '" + funcionario.getMatricula() + "', " + 
                        "setor = '" + funcionario.getSetor() + "', " + 
                        "turno = '" + funcionario.getTurno() + "', " + 
                        "funcao = '" + funcionario.getFuncao() + "', " + 
                        "data_admissao = '" + funcionario.getDataAdmissao() + "', " + 
                        "escala = '" + funcionario.getEscala() + "', " + 
                        "horario = '" + funcionario.getHorario() + "', " + 
                        "horas_semanais = '" + funcionario.getHorasSemanais() + "', " + 
                        "codigo_de_barras = '" + funcionario.getCodigoDeBarras() + "', " + 
                        "senha = '" + funcionario.getSenha() + "' " + 
                        "WHERE id = " + funcionario.getIdFuncionario();
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
            
            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // funcao que deleta um funcionario do banco de dados
    public void deletarFuncionario(int id) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to delete the funcionario
        String query = "DELETE FROM funcionarios WHERE id = " + id;
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
            
            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // funcao que busca o funcionario pela matricula
    public static funcionario buscarFuncionarioPorMatricula(int matricula) throws SQLException {
        funcionario funcionario = null;
        
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to retrieve the funcionario by matricula
        String query = "SELECT * FROM funcionarios WHERE matricula = '" + matricula + "'";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);
            
            // Check if the result set has any data
            if (resultSet.next()) {
                // Retrieve the data from the result set
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
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
                funcionario = new funcionario();
                funcionario.setIdFuncionario(id);
                funcionario.setNome(nome);
                funcionario.setMatricula(matricula);
                funcionario.setSetor(setor);
                funcionario.setTurno(turno);
                funcionario.setFuncao(funcao);
                funcionario.setDataAdmissao(dataAdmissao);
                funcionario.setEscala(escala);
                funcionario.setHorario(horario);
                funcionario.setHorasSemanais(horasSemanais);
                funcionario.setCodigoDeBarras(codigoDeBarras);
                funcionario.setSenha(senha);
                // Set other fields as needed
            }
            
            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return funcionario;
    }

}
