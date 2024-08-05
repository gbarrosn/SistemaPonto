package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.assinatura;

public class dadosAssinatura {
    //função que registra uma assinatura no banco de dados usando conectarBanco e assinatura
    public static void registrarAssinatura(int idFuncionario, int mes, String dataAssinatura, String horaAssinatura) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to insert the assinatura
        String query = "INSERT INTO assinatura (id_funcionario, data_assinatura, hora_assinatura, mes) VALUES (" + idFuncionario +  ", '" + dataAssinatura + "', '" + horaAssinatura + "', " + mes +")";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    // buscar assinatura usando o id do funcionario e mes
    public static assinatura buscarAssinatura(int idFuncionario, int mes) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to select the assinatura
        String query = "SELECT * FROM assinatura WHERE id_funcionario = " + idFuncionario + " AND mes = " + mes;

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                assinatura assinatura = new assinatura(resultSet.getInt("id"), resultSet.getInt("id_funcionario"), resultSet.getInt("mes"), resultSet.getString("hora_assinatura"), resultSet.getString("data_assinatura"));
                return assinatura;
            } 

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


}

