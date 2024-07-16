package db;

import java.sql.Connection;
import java.sql.DriverManager;
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
        if (connection == null) {
            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver"); // Replace with your MySQL driver class name
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conectarBanco.class.getName()).log(Level.SEVERE, null, ex);
                }
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                System.out.println("Conexão com o banco de dados MySQL estabelecida!");
            } catch (SQLException e) {
                if (e.getMessage().contains("database 'pontoeletronico' does not exist")) {
                    criarBanco();
                    criarTabelas(); // Call the function to create tables
                    connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                    System.out.println("Banco de dados PontoEletronico criado e tabelas criadas com sucesso!");
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
        String sqlCriarFuncionarios = "CREATE TABLE funcionarios (\n" +
                                        "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                        "  nome TEXT NOT NULL,\n" +
                                        "  matricula INTEGER NOT NULL UNIQUE,\n" +
                                        "  setor TEXT NOT NULL,\n" +
                                        "  turno TEXT NOT NULL,\n" +
                                        "  funcao TEXT NOT NULL,\n" +
                                        "  data_admissao TEXT NOT NULL,\n" +
                                        "  escala TEXT NOT NULL,\n" +
                                        "  horario TEXT NOT NULL,\n" +
                                        "  horas_semanais TEXT NOT NULL,\n" +
                                        "  codigo_de_barras TEXT NOT NULL UNIQUE,\n" +
                                        "  senha TEXT NOT NULL\n" +
                                        ");";

        String sqlCriarRegistros = "CREATE TABLE registros (\n" +
                                    "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                    "  setor TEXT NOT NULL,\n" +
                                    "  turno TEXT NOT NULL,\n" +
                                    "  funcao TEXT NOT NULL,\n" +
                                    "  id_funcionario INTEGER NOT NULL,\n" +
                                    "  hora_entrada TEXT NOT NULL,\n" +
                                    "  saida_almoco TEXT NOT NULL,\n" +
                                    "  retorno_almoco TEXT NOT NULL,\n" +
                                    "  saida TEXT NOT NULL,\n" +
                                    "  FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)\n" +
                                    ");";

        String sqlCriarAssinatura = "CREATE TABLE assinatura (\n" +
                                    "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                    "  id_funcionario INTEGER NOT NULL,\n" +
                                    "  data_assinatura TEXT NOT NULL,\n" +
                                    "  hora_assinatura TEXT NOT NULL,\n" +
                                    "  FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) \n" +
                                    ");";

        Connection conn = conectarBanco.conectar(); // Get a connection
        Statement stmt = conn.createStatement();

        // Execute the table creation SQL statements
        stmt.execute(sqlCriarFuncionarios);
        stmt.execute(sqlCriarRegistros);

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
