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
    public static List<funcionario> buscarFuncionarios() throws SQLException {
        List<funcionario> funcionarios = new ArrayList<>();
        
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to retrieve the funcionarios
        String query = "SELECT * FROM funcionarios order by nome";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);
            
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve the data from the result set
                String id = resultSet.getString("id");
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
                boolean adm = resultSet.getBoolean("adm");
                boolean servidor = resultSet.getBoolean("servidor");
                String vinculo = resultSet.getString("vinculo");
                String contrato = resultSet.getString("contrato");
                String empresa = resultSet.getString("empresa");

                // Retrieve other fields as needed
                
                // Create a new Funcionario object
                funcionario funcionario = new funcionario();
                funcionario.setIdFuncionario(Integer.parseInt(id)); // TODO : nem chega a aparecer a variavel id no o
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
                funcionario.setAdm(adm);
                funcionario.setServidor(servidor);
                funcionario.setVinculo(vinculo);
                funcionario.setContrato(contrato);
                funcionario.setEmpresa(empresa);
                // Set other fields as needed
                
                // Add the funcionario to the list
                funcionarios.add(funcionario);
            }
            
            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw e;
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
                boolean adm = resultSet.getBoolean("adm");
                boolean servidor = resultSet.getBoolean("servidor");
                String vinculo = resultSet.getString("vinculo");
                String contrato = resultSet.getString("contrato");
                String empresa = resultSet.getString("empresa");

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
                funcionario.setAdm(adm);
                funcionario.setServidor(servidor);
                funcionario.setVinculo(vinculo);
                funcionario.setContrato(contrato);
                funcionario.setEmpresa(empresa);

                // Set other fields as needed
            }
            
            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw e;
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
                        "codigo_de_barras, senha, adm, servidor, vinculo, contrato, empresa) " +
                        "VALUES ('" + funcionario.getNome() + "', '" + 
                        funcionario.getMatricula() + "', '" + funcionario.getSetor() + "', '" + 
                        funcionario.getTurno() + "', '" + funcionario.getFuncao() + "', '" + 
                        funcionario.getDataAdmissao() + "', '" + funcionario.getEscala() + "', '" + 
                        funcionario.getHorario() + "', '" + funcionario.getHorasSemanais() + "', '" + 
                        funcionario.getCodigoDeBarras() + "', '" + funcionario.getSenha() + "'," +
                        funcionario.isAdm() + ", " + funcionario.isServidor() + ", '"+ funcionario.getVinculo() + "', '" + funcionario.getContrato() + "', '" + funcionario.getEmpresa() + "')";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
            
            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    // funcao que atualiza um funcionario no banco de dados
    public static void alterarFuncionario(funcionario funcionario) throws SQLException {
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
                        "senha = '" + funcionario.getSenha() + "', " + 
                        "adm = " + funcionario.isAdm() + ", " +
                        "servidor = " + funcionario.isServidor() + ", " +
                        "vinculo = '" + funcionario.getVinculo() + "', " +
                        "contrato = '" + funcionario.getContrato() + "', " +
                        "empresa = '" + funcionario.getEmpresa() + "' " +
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
            throw e;
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
            throw e;
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
                boolean adm = resultSet.getBoolean("adm");
                boolean servidor = resultSet.getBoolean("servidor");
                String vinculo = resultSet.getString("vinculo");
                String contrato = resultSet.getString("contrato");
                String empresa = resultSet.getString("empresa");

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
                funcionario.setAdm(adm);
                funcionario.setServidor(servidor);
                funcionario.setVinculo(vinculo);
                funcionario.setContrato(contrato);
                funcionario.setEmpresa(empresa);
                // Set other fields as needed
            }
            
            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw e;
        }
        
        return funcionario;
    }

    public static void alterarSenha(int id, String senha) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the funcionario
        String query = "UPDATE funcionarios SET " + 
                        "senha = '" + senha + "' " + 
                        "WHERE id = " + id;
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
            
            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterarCodigoDeBarras(int id, String codigoDeBarras) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the funcionario
        String query = "UPDATE funcionarios SET " + 
                        "codigo_de_barras = '" + codigoDeBarras + "' " + 
                        "WHERE id = " + id;
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
            
            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws SQLException {
        try {
            List<funcionario> funcionarios = buscarFuncionarios();
            for (funcionario f : funcionarios) {
                System.out.println(f.getIdFuncionario());
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
