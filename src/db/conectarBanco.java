package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author gbarrosn
 */
public class conectarBanco {

    // Atributos de conexão
    private static final String SERVER = "localhost:3306";
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
                Statement stmt = connection.createStatement();
                stmt.execute("USE PontoEletronico;");
                
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

    private static boolean pingHost(String host) {
        try {
            Process process = Runtime.getRuntime().exec("ping " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Wait for 2 seconds before checking for "TTL"
            //TimeUnit.SECONDS.sleep(2);

            while ((line = reader.readLine()) != null) {
                if (line.contains("TTL")) {
                    return true; // Host is accessible
                } else if (line.contains("Esgotado")) {
                    return false;
                }
            }
        } catch (Exception e) {
            // Error executing the ping command
            System.err.println("Error pinging host: " + e.getMessage());
        }
        return false;
    }

    private static String escolherIpDb() {
        String validDatabase = null;
        String[] ips = {"192.168.1.42:3306", "192.168.200.74:3306", "192.168.200.7:3306", "10.1.1.41:3306", "192.168.2.44", "localhost:3306"};

        for (String ip : ips) {
            String[] ipParts = ip.split(":");
            String ipAddress = ipParts[0];

            // Verifica se o host é acessível antes de tentar a conexão
            System.out.println("Testando servidor " + ipAddress );
            if (pingHost(ipAddress)) {
                System.out.println("Servidor " + ipAddress + " encontrado!");
                try {
                    int port = Integer.parseInt(ipParts[1]);
                    String url = "jdbc:mysql://" + ipAddress + ":" + port;
                    System.out.println("Tentando conectar com " + url);

                    try (Connection testConnection = DriverManager.getConnection(url, USER, PASSWORD)) {
                        System.out.println("Conexão com " + url + " estabelecida!");
                    }
                    validDatabase = ip;
                    return validDatabase;
                } catch (NumberFormatException | SQLException e) {
                    System.out.println("Falha ao conectar com " + ip);
                }
            }
        }

        return validDatabase;
    }

    public static boolean verificarDatabase() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean verificarTabelas() {
        try {
            Connection conn = conectar();
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES");
            int tableCount = 0;
            while (rs.next()) {
                tableCount++;
            }
            rs.close();
            
            return (tableCount == 5);

        } catch (SQLException e) {
            return false;
        }
    }

    public static void criarBanco() throws SQLException {
        String sqlCriarBanco = "CREATE DATABASE PontoEletronico";

        Connection conn = DriverManager.getConnection("jdbc:mysql://" + SERVER, USER, PASSWORD);
        Statement stmt = conn.createStatement();

        // Execute a instrução SQL para criar o banco de dados
        stmt.execute(sqlCriarBanco);

        stmt.close();
        conn.close();
    }

    public static void criarTabelas() throws SQLException {
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
                                        "  senha TEXT NOT NULL," +
                                        "  adm bit, " +
                                        "  servidor bit," +
                                        "  vinculo TEXT," +
                                        "  contrato TEXT," +
                                        "  empresa TEXT" +
                                        ");";

        String sqlCriarRegistros = "CREATE TABLE registros (" +
                                "  id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                                "  id_funcionario INTEGER NOT NULL," +
                                "  hora_entrada text," +
                                "  saida_almoco text," +
                                "  retorno_almoco text," +
                                "  saida text," +
                                "  data text NOT NULL," +
                                "  alteracao text," +
                                "  atestado bit," +
                                "  saida_antecipada text," +
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

        String sqlCriarAssinaturaCoordenacao = "CREATE TABLE assinaturaCoordenacao (" +
                                                "id INT PRIMARY KEY AUTO_INCREMENT," +
                                                "id_funcionario INT NOT NULL," +
                                                "id_coordenacao INT NOT NULL," +
                                                "data_assinatura DATE NOT NULL," +
                                                "hora_assinatura TEXT NOT NULL," +
                                                "mes integer not null" +
                                                " );";
                                            
        String sqlCriarTabelContrato = "CREATE TABLE contrato ( id INT primary key auto_increment, contrato text);";
                                                
        Connection conn = conectarBanco.conectar(); // Get a connection
        Statement stmt = conn.createStatement();

        // Execute the table creation SQL statements
        stmt.execute(sqlCriarFuncionarios);
        stmt.execute(sqlCriarRegistros);
        stmt.execute(sqlCriarAssinatura);
        stmt.execute(sqlCriarAssinaturaCoordenacao);
        stmt.execute(sqlCriarTabelContrato);

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
