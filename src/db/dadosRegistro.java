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
    
    // funcao que lista todos os registros e retorna uma lista dos mesmos
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
                String horaSaida = resultSet.getString("saida");
                String data = resultSet.getString("data");
                int mes = resultSet.getInt("mes");

                // Create a new Registro object
                registro registro = new registro(idRegistro, setor, turno, funcao, idFuncionario, horaEntrada, saidaAlmoco, retornoAlmoco, horaSaida, data, mes);
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return registros;
    }

    // funcao que insere um registro no banco de dados
    public void inserirRegistro(registro registro) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to insert the registro
        String query = "INSERT INTO registros (setor, turno, funcao, id_funcionario, hora_entrada, saida_almoco, retorno_almoco, saida, data) VALUES ('" 
        + registro.getSetor() + "', '" + registro.getTurno() + "', '" + registro.getFuncao() + "', " + registro.getIdFuncionario() + ", '" 
        + registro.getHoraEntrada() + "', '" + registro.getSaidaAlmoco() + "', '" + registro.getRetornoAlmoco() + "', '" + registro.getHoraSaida() + "', '" + registro.getData() + "', '" + registro.getMes() + "')";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // funcao que busca registros pelo id do funcionario e retorna uma lista de registros
    public List<registro> buscarRegistrosPorId(int idFuncionario) throws SQLException {

        List<registro> registros = new ArrayList<>();
        
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to retrieve the registros by id_funcionario
        String query = "SELECT * FROM registros WHERE id_funcionario = " + idFuncionario;
        
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
                int idFuncionarioResult = resultSet.getInt("id_funcionario");
                String horaEntrada = resultSet.getString("hora_entrada");
                String saidaAlmoco = resultSet.getString("saida_almoco");
                String retornoAlmoco = resultSet.getString("retorno_almoco");
                String horaSaida = resultSet.getString("saida");
                String data = resultSet.getString("data");
                int mes = resultSet.getInt("mes");

                // Create a new Registro object
                registro registro = new registro(idRegistro, setor, turno, funcao, idFuncionarioResult, horaEntrada, saidaAlmoco, retornoAlmoco, horaSaida, data, mes);
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return registros;
    }

    // funcao que busca registros pelo id do funcionario e numero do mes e retorna uma lista de registros
    public List<registro> buscarRegistrosPorIdEMes(int idFuncionario, int mes) throws SQLException {

        List<registro> registros = new ArrayList<>();
        
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to retrieve the registros by id_funcionario and mes
        String query = "SELECT * FROM registros WHERE id_funcionario = " + idFuncionario + " AND mes = " + mes;
        
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
                int idFuncionarioResult = resultSet.getInt("id_funcionario");
                String horaEntrada = resultSet.getString("hora_entrada");
                String saidaAlmoco = resultSet.getString("saida_almoco");
                String retornoAlmoco = resultSet.getString("retorno_almoco");
                String horaSaida = resultSet.getString("saida");
                String data = resultSet.getString("data");
                int mesResult = resultSet.getInt("mes");

                // Create a new Registro object
                registro registro = new registro(idRegistro, setor, turno, funcao, idFuncionarioResult, horaEntrada, saidaAlmoco, retornoAlmoco, horaSaida, data, mesResult);
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return registros;
    }

    public static void criarRegistro(int id, String hora, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to insert the registro
        String query = "INSERT INTO registros (id_funcionario, hora_entrada, data) VALUES (" + id + ", '" + hora + "', '" + data + "')";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificarRegistroExistente(int id_funcionario, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to check if the registro exists
        String query = "SELECT * FROM registros WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);
            
            // Check if the result set is empty
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public static void registrarSaida(int id_funcionario, String hora, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the registro
        String query = "UPDATE registros SET saida = '" + hora + "' WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void registrarSaidaAlmoco(int id_funcionario, String hora, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the registro
        String query = "UPDATE registros SET saida_almoco = '" + hora + "' WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";
        
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
