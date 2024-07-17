package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dadosAssinatura {
    //função que registra uma assinatura no banco de dados usando conectarBanco e assinatura
    public void registrarAssinatura(int idFuncionario, int mes, String dataAssinatura, String horaAssinatura) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to insert the assinatura
        String query = "INSERT INTO assinaturas (id_funcionario, mes, data_assinatura, hora_assinatura) VALUES (" + idFuncionario + ", " + mes + ", '" + dataAssinatura + "', '" + horaAssinatura + "')";
        
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
