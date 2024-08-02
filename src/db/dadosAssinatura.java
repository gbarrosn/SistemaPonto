package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class dadosAssinatura {
    //função que registra uma assinatura no banco de dados usando conectarBanco e assinatura
    public static void registrarAssinatura(int idFuncionario, int mes, String dataAssinatura, String horaAssinatura) throws SQLException {
        // Connect to the database
        Connection connection = conectarBanco.conectar();
        
        // Create a query to insert the assinatura
        String query = "INSERT INTO assinaturas (id_funcionario, data_assinatura, hora_assinatura, mes) VALUES (" + idFuncionario +  ", '" + dataAssinatura + "', '" + horaAssinatura + "', " + mes +")";
        
        try {
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Execute the query
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    // buscar assinatura usando o id do funcionario
    


}

