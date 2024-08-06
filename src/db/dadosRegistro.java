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

import model.funcionario;
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
                int idFuncionario = resultSet.getInt("id_funcionario");
                String horaEntrada = resultSet.getString("hora_entrada");
                String saidaAlmoco = resultSet.getString("saida_almoco");
                String retornoAlmoco = resultSet.getString("retorno_almoco");
                String horaSaida = resultSet.getString("saida");
                String data = resultSet.getString("data");

                // Create a new Registro object
                registro registro = new registro(idRegistro, idFuncionario, horaEntrada, saidaAlmoco, retornoAlmoco, horaSaida, data);
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
    public static List<registro> buscarRegistrosPorId(int idFuncionario) throws SQLException {

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
                int idFuncionarioResult = resultSet.getInt("id_funcionario");
                String horaEntrada = resultSet.getString("hora_entrada");
                String saidaAlmoco = resultSet.getString("saida_almoco");
                String retornoAlmoco = resultSet.getString("retorno_almoco");
                String horaSaida = resultSet.getString("saida");
                String data = resultSet.getString("data");


                // Create a new Registro object
                registro registro = new registro(idRegistro, idFuncionarioResult, horaEntrada, saidaAlmoco, retornoAlmoco, horaSaida, data);
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
                int idFuncionarioResult = resultSet.getInt("id_funcionario");
                String horaEntrada = resultSet.getString("hora_entrada");
                String saidaAlmoco = resultSet.getString("saida_almoco");
                String retornoAlmoco = resultSet.getString("retorno_almoco");
                String horaSaida = resultSet.getString("saida");
                String data = resultSet.getString("data");

                // Create a new Registro object
                registro registro = new registro(idRegistro, idFuncionarioResult, horaEntrada, saidaAlmoco, retornoAlmoco, horaSaida, data);
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

    public static void registrarRetornoAlmoco(int id_funcionario, String hora, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the registro
        String query = "UPDATE registros SET retorno_almoco = '" + hora + "' WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static List<registro> buscarRegistrosFuncionario(String data) {

        try (Connection connection = conectarBanco.conectar()) {
            String query = "SELECT * FROM registros inner join funcionarios on (registros.id_funcionario = funcionarios.id) WHERE registros.data = '" + data + "';";

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                List<registro> registros = new ArrayList<>();

                while (resultSet.next()) {
                    int idRegistro = resultSet.getInt("id");
                    int idFuncionario = resultSet.getInt("id_funcionario");
                    String horaEntrada = resultSet.getString("hora_entrada");
                    String saidaAlmoco = resultSet.getString("saida_almoco");
                    String retornoAlmoco = resultSet.getString("retorno_almoco");
                    String horaSaida = resultSet.getString("saida");
                    String dataRegistro = resultSet.getString("data");
                    String nome = resultSet.getString("nome");

                    registro registro = new registro(idRegistro, idFuncionario, horaEntrada, saidaAlmoco, retornoAlmoco, horaSaida, dataRegistro);
                    registro.setNomeFuncionario(nome);
                    registros.add(registro);
                }

                return registros;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
        //TODO: buscar os registros do funcionario x e retornar uma lista dos registros de todo mundo no dia pedido
    }

    //private static void alterarRegistro

}
