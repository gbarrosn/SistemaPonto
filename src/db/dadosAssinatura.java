package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.assinatura;

public class dadosAssinatura {
    //função que registra uma assinatura no banco de dados usando conectarBanco e assinatura
    public static void registrarAssinatura(int idFuncionario, int mes, String dataAssinatura, String horaAssinatura) throws SQLException {
    // Connect to the database
        Connection connection = conectarBanco.conectar();

        // Verificar se a assinatura já existe
        String checkQuery = "SELECT * FROM assinatura WHERE id_funcionario = ? AND mes = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
        checkStatement.setInt(1, idFuncionario);
        checkStatement.setInt(2, mes);
        ResultSet resultSet = checkStatement.executeQuery();

        if (resultSet.next()) {
            // Assinatura já existe, então atualizamos
            String updateQuery = "UPDATE assinatura SET data_assinatura = ?, hora_assinatura = ? WHERE id_funcionario = ? AND mes = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, dataAssinatura);
            updateStatement.setString(2, horaAssinatura);
            updateStatement.setInt(3, idFuncionario);
            updateStatement.setInt(4, mes);
            updateStatement.executeUpdate();
        } else {
            // Assinatura não existe, então inserimos
            String insertQuery = "INSERT INTO assinatura (id_funcionario, data_assinatura, hora_assinatura, mes) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, idFuncionario);
            insertStatement.setString(2, dataAssinatura);
            insertStatement.setString(3, horaAssinatura);
            insertStatement.setInt(4, mes);
            insertStatement.executeUpdate();
        }

        // Feche os recursos
        resultSet.close();
        checkStatement.close();
        connection.close();
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

