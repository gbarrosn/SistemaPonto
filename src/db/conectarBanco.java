/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gbarrosn
 */
public class conectarBanco {
    
    // Atributos de conexão
    private static final String CONEXAO_URL = "jdbc:sqlite:banco.db"; // Substitua pelo nome do seu banco de dados
    private static Connection connection;

    public static Connection conectar() throws SQLException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(CONEXAO_URL);
                System.out.println("Conexão com o banco de dados estabelecida!");
            } catch (SQLException e) {
                if (e.getMessage().contains("no such table: funcionarios")) {
                    // Criar o banco de dados e as tabelas
                    criarBancoETabelas();
                    connection = DriverManager.getConnection(CONEXAO_URL);
                    System.out.println("Banco de dados e tabelas criados com sucesso!");
                } else {
                    throw e;
                }
            }
        }
        return connection;
    }

    private static void criarBancoETabelas() throws SQLException {
        // Crie as instruções SQL para criar as tabelas
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
                                        ");"; // Substitua pelos comandos SQL da sua tabela
        
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
                                    ");"; // Substitua pelos comandos SQL da sua tabela
        
        String sqlCriarAssinatura = "CREATE TABLE assinatura (\n" +
                                    "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                    "  id_funcionario INTEGER NOT NULL,\n" +
                                    "  data_assinatura TEXT NOT NULL,\n" +
                                    "  hora_assinatura TEXT NOT NULL,\n" +
                                    "  FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) \n" +
                                    ");"; // Substitua pelos comandos SQL da sua tabela

        Connection conn = DriverManager.getConnection("jdbc:sqlite:banco.db");
        Statement stmt = conn.createStatement();

        // Execute as instruções SQL para criar as tabelas
        stmt.execute(sqlCriarFuncionarios);
        stmt.execute(sqlCriarRegistros);
        stmt.execute(sqlCriarAssinatura);

        stmt.close();
        conn.close();
    }

    public static void desconectar() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
            System.out.println("Conexão com o banco de dados fechada!");
        }
    }


  public static void main(String[] args) throws SQLException {
    // Connect to the database
    try {
            Class.forName("org.sqlite.JDBC");
        } catch (final ClassNotFoundException e) {
            System.err.println(e);
            return;
        }
    Connection connection = conectarBanco.conectar();

    if (connection != null) {
      System.out.println("Conectado com sucesso!");
      connection.close(); // Close the connection after testing
    } else {
      System.out.println("Falha ao conectar!");
    }
  }

}
