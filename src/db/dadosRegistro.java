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
                int idFuncionario = resultSet.getInt("id_funcionario");
                String horaEntrada = resultSet.getString("hora_entrada");
                String saidaAlmoco = resultSet.getString("saida_almoco");
                String retornoAlmoco = resultSet.getString("retorno_almoco");
                String horaSaida = resultSet.getString("saida");
                String data = resultSet.getString("data");
                String alteracao = resultSet.getString("alteracao");
                boolean atestado = resultSet.getBoolean("atestado");
                String saidaAntecipada = resultSet.getString("saida_antecipada");
                

                // Create a new Registro object
                registro registro = new registro();
                registro.setIdRegistro(idRegistro);
                registro.setIdFuncionario(idFuncionario);
                registro.setHoraEntrada(horaEntrada);
                registro.setSaidaAlmoco(saidaAlmoco);
                registro.setRetornoAlmoco(retornoAlmoco);
                registro.setHoraSaida(horaSaida);
                registro.setData(data);
                registro.setAlteracao(alteracao);
                registro.setAtestado(atestado);
                registro.setSaidaAntecipada(saidaAntecipada);
                
                registros.add(registro);
            }
        } catch (SQLException e) {
            throw e;
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
            throw e;
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
                String alteracao = resultSet.getString("alteracao");
                boolean atestado = resultSet.getBoolean("atestado");
                String saidaAntecipada = resultSet.getString("saida_antecipada");


                // Create a new Registro object
                registro registro = new registro();
                registro.setIdRegistro(idRegistro);
                registro.setIdFuncionario(idFuncionarioResult);
                registro.setHoraEntrada(horaEntrada);
                registro.setSaidaAlmoco(saidaAlmoco);
                registro.setRetornoAlmoco(retornoAlmoco);
                registro.setHoraSaida(horaSaida);
                registro.setData(data);
                registro.setAlteracao(alteracao);
                registro.setAtestado(atestado);
                registro.setSaidaAntecipada(saidaAntecipada);
                

                registros.add(registro);
            }
        } catch (SQLException e) {
            throw e;
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
                String alteracao = resultSet.getString("alteracao");
                boolean atestado = resultSet.getBoolean("atestado");
                String saidaAntecipada = resultSet.getString("saida_antecipada");

                // Create a new Registro object
                registro registro = new registro();
                registro.setIdRegistro(idRegistro);
                registro.setIdFuncionario(idFuncionarioResult);
                registro.setHoraEntrada(horaEntrada);
                registro.setSaidaAlmoco(saidaAlmoco);
                registro.setRetornoAlmoco(retornoAlmoco);
                registro.setHoraSaida(horaSaida);
                registro.setData(data);
                registro.setAlteracao(alteracao);
                registro.setAtestado(atestado);
                registro.setSaidaAntecipada(saidaAntecipada);
                
                registros.add(registro);
            }
        } catch (SQLException e) {
            throw e;
        }
        
        return registros;
    }

    public static void criarRegistro(int id, String hora, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to insert the registro
        String query = "INSERT INTO registros (id_funcionario, hora_entrada, data) VALUES (" + id + ", '" + hora + "', '" + data + "')";
        String check = "SELECT * FROM registros WHERE id_funcionario = " + id + " AND data = '" + data + "'";

        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(check);
            
            if (resultSet.next()) {
                throw new SQLException("O funcionário já registrou a entrada");
            }
        } catch (SQLException e) {
            throw e;
        }
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
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
            throw e;
        }
        
        return false;
    }

    public static void registrarPonto(int id_funcionario, String hora, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the registro
        String query = "UPDATE registros SET saida = '" + hora + "' WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";
        String check = "SELECT * FROM registros WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";

        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(check);
            
            if (!resultSet.next()) {
                criarRegistro(id_funcionario, hora, data);
                throw new SQLException("Entrada registrada!");
            } else if (resultSet.getString("saida_almoco") == null) {
                registrarSaidaAlmoco(id_funcionario, hora, data);
                throw new SQLException("Saída para o almoço registrada!");
            } else if (resultSet.getString("retorno_almoco") == null) {
                registrarRetornoAlmoco(id_funcionario, hora, data);
                throw new SQLException("Retorno do almoço registrado!");
            } else if (resultSet.getString("saida") != null) {
                throw new SQLException("O funcionário já registrou a saída");
            }
        } catch (SQLException e) {
            throw e;
        }
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw e;

        }
    }

    public static void registrarSaidaAlmoco(int id_funcionario, String hora, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the registro
        String query = "UPDATE registros SET saida_almoco = '" + hora + "' WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";

        String check = "SELECT * FROM registros WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";

        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(check);
            
            if (!resultSet.next()) {
                throw new SQLException("O funcionário não registrou a entrada");
            } else if (resultSet.getString("saida_almoco") != null) {
                throw new SQLException("O funcionário já registrou a saída para o almoço");
            }
        } catch (SQLException e) {
            throw e;
        }
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw e;

        }
    }

    public static void registrarRetornoAlmoco(int id_funcionario, String hora, String data) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to update the registro
        String query = "UPDATE registros SET retorno_almoco = '" + hora + "' WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";
        String check = "SELECT * FROM registros WHERE id_funcionario = " + id_funcionario + " AND data = '" + data + "'";

        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery(check);
            
            if (!resultSet.next()) {
                throw new SQLException("O funcionário não registrou a entrada");
            } else if (resultSet.getString("saida_almoco") == null) {
                throw new SQLException("O funcionário não registrou a saída para o almoço");
            } else if (resultSet.getString("retorno_almoco") != null) {
                throw new SQLException("O funcionário já registrou o retorno do almoço");
            }

        } catch (SQLException e) {
            throw e;
        }
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw e;

        }
    }

    public static List<registro> buscarRegistrosData(String data) throws SQLException {

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
                    String alteracao = resultSet.getString("alteracao");
                    boolean atestado = resultSet.getBoolean("atestado");
                    String saidaAntecipada = resultSet.getString("saida_antecipada");

                    registro registro = new registro();
                    registro.setNomeFuncionario(nome);
                    registro.setIdRegistro(idRegistro);
                    registro.setIdFuncionario(idFuncionario);
                    registro.setHoraEntrada(horaEntrada);
                    registro.setSaidaAlmoco(saidaAlmoco);
                    registro.setRetornoAlmoco(retornoAlmoco);
                    registro.setHoraSaida(horaSaida);
                    registro.setData(dataRegistro);
                    registro.setAtestado(atestado);
                    
                    if (alteracao == null) {
                        alteracao = "";
                    }
                    registro.setAlteracao(alteracao);
                    
                    if (saidaAntecipada == null) {
                        saidaAntecipada = "";
                    }
                    registro.setSaidaAntecipada(saidaAntecipada);

                    registros.add(registro);
                }

                return registros;
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw e;
        }


        //TODO: buscar os registros do funcionario x e retornar uma lista dos registros de todo mundo no dia pedido
    }

    public static void alterarRegistro(registro registro, String alteracao) throws SQLException {
        Connection connection = conectarBanco.conectar();

        String query = "UPDATE registros SET hora_entrada = '" + registro.getHoraEntrada() + "', saida_almoco = '" + 
                        registro.getSaidaAlmoco() + "', retorno_almoco = '" + registro.getRetornoAlmoco() + "', saida = '" + 
                        registro.getHoraSaida() + "', alteracao = '" + alteracao + "', atestado = " + registro.isAtestado() +
                        " WHERE id = " + registro.getIdRegistro();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static List<registro> buscarRegistrosMes(int mes) throws SQLException {
        
        try (Connection connection = conectarBanco.conectar()) {

            String query;

            if (mes < 10) {
                query = "SELECT * FROM registros WHERE data like '%/0" + String.valueOf(mes).trim() + "/%';";
            } else {
                query = "SELECT * FROM registros WHERE data like '%/" + String.valueOf(mes).trim() + "/%';";
            }

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
                    String alteracao = resultSet.getString("alteracao");
                    boolean atestado = resultSet.getBoolean("atestado");
                    String saidaAntecipada = resultSet.getString("saida_antecipada");

                    registro registro = new registro();
                    registro.setIdRegistro(idRegistro);
                    registro.setIdFuncionario(idFuncionario);
                    registro.setHoraEntrada(horaEntrada);
                    registro.setSaidaAlmoco(saidaAlmoco);
                    registro.setRetornoAlmoco(retornoAlmoco);
                    registro.setHoraSaida(horaSaida);
                    registro.setData(dataRegistro);
                    registro.setAlteracao(alteracao);
                    registro.setAtestado(atestado);
                    registro.setSaidaAntecipada(saidaAntecipada);
                    
                    registros.add(registro);
                }

                return registros;
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw e;
    }
        
}

    public static void adicionarAtestado(registro registro) throws SQLException {
        try (Connection connection = conectarBanco.conectar()) {
            String query = "UPDATE registros SET atestado = " + registro.isAtestado() + " WHERE id = " + registro.getIdRegistro();

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void criarAtestado(registro registro) throws SQLException {
        try (Connection connection = conectarBanco.conectar()) {
            String query = "INSERT INTO registros (id_funcionario, hora_entrada, saida_almoco, retorno_almoco, saida, data, alteracao, atestado) VALUES (" + 
                            registro.getIdFuncionario() + ", '" + registro.getHoraEntrada() + "', '" + registro.getSaidaAlmoco() + "', '" + registro.getRetornoAlmoco() + "', '" + 
                            registro.getHoraSaida() + "', '" + registro.getData() + "', '" + registro.getAlteracao() + "', " + registro.isAtestado() + ")";
            
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                throw e;
            }

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void registrarSaidaAntecipada(registro registro) throws SQLException {
        try (Connection connection = conectarBanco.conectar()) {
            String query = "UPDATE registros SET saida_antecipada = '" + registro.getSaidaAntecipada() + "' WHERE id = " + registro.getIdRegistro();

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                throw e;
            }

        }
    }

public static void main(String[] args) {
    List<registro> registros = new ArrayList<>();
    try {
        registros = buscarRegistrosData("06/08/2024");
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    for (registro reg : registros) {
        System.out.println(reg.getAlteracao());
    }
}

}
