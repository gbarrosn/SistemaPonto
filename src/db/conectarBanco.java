package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gbarrosn
 */
public class conectarBanco {

    // Atributos de conexão
    private static final String SERVER = "localhost";
    private static final String USER = "ponto"; // Replace with your MySQL username
    private static final String PASSWORD = "senha"; // Replace with your MySQL password
    private static final String DATABASE_URL = "jdbc:mysql://" + SERVER + "/PontoEletronico"; // Database name

    private static Connection connection;

    public static Connection conectar() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); // Replace with your MySQL driver class name
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conectarBanco.class.getName()).log(Level.SEVERE, null, ex);
                }
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                System.out.println("Conexão com o banco de dados MySQL estabelecida!");
                //Statement stmt = connection.createStatement();
                
            } catch (SQLException e) {
                if (e.getMessage().contains("Unknown database 'PontoEletronico'")) {
                    criarBanco();
                    criarTabelas();
                } else {
                    throw e;
                }
            }
        }
        return connection;
    }

    private static void criarBanco() throws SQLException {
        String sqlCriarBanco = "CREATE DATABASE PontoEletronico";

        Connection conn = DriverManager.getConnection("jdbc:mysql://" + SERVER, USER, PASSWORD);
        Statement stmt = conn.createStatement();

        // Execute a instrução SQL para criar o banco de dados
        stmt.execute(sqlCriarBanco);

        stmt.close();
        conn.close();
    }

    private static void criarTabelas() throws SQLException {
        // Replace with your actual table creation SQL statements
        String sqlCriarFuncionarios = "CREATE TABLE funcionarios (" +
                                        "  id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                                        "  nome TEXT NOT NULL," +
                                        "  matricula INTEGER NOT NULL," +
                                        "  setor TEXT NOT NULL," +
                                        "  turno TEXT NOT NULL," +
                                        "  funcao TEXT NOT NULL," +
                                        "  data_admissao TEXT NOT NULL," +
                                        "  escala TEXT NOT NULL," +
                                        "  horario TEXT NOT NULL," +
                                        "  horas_semanais TEXT NOT NULL," +
                                        "  codigo_de_barras TEXT NOT NULL," +
                                        "  senha TEXT NOT NULL" +
                                        ");";

        String sqlCriarRegistros = "CREATE TABLE registros (" +
                        "  id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                        "  id_funcionario INTEGER NOT NULL," +
                        "  hora_entrada text," +
                        "  saida_almoco text," +
                        "  retorno_almoco text," +
                        "  saida text," +
                        "  data text NOT NULL," +
                        "  FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)" +
                        ");";

        String sqlCriarAssinatura = "CREATE TABLE assinatura (" +
                                    "  id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                                    "  id_funcionario INTEGER NOT NULL," +
                                    "  data_assinatura TEXT NOT NULL," +
                                    "  hora_assinatura TEXT NOT NULL," +
                                    "  mes INTEGER NOT NULL," +
                                    "  FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) " +
                                    ");";

        Connection conn = conectarBanco.conectar(); // Get a connection
        Statement stmt = conn.createStatement();

        // Execute the table creation SQL statements
        stmt.execute(sqlCriarFuncionarios);
        stmt.execute(sqlCriarRegistros);
        stmt.execute(sqlCriarAssinatura);

    }
    public static void main(String[] args) {
        try {
            Connection connection = conectarBanco.conectar();
            // Your code here
            // Perform database operations using the connection object

            // For example, execute queries, insert data, update data, etc.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
