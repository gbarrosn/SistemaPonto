/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import model.registro;
import model.registroMensal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gbarrosn
 */
public class dadosRegistroMensal {
    
    public static List<registroMensal> buscarDadosDoMes(int mes) {
        List<registroMensal> registros = new ArrayList<>();

        try {
            Connection connection = conectarBanco.conectar();
            String sql = "SELECT * FROM registros r inner join funcionarios f on (r.id_funcionario = f.id) WHERE r.data LIKE '%/0" + mes + "/%'";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                registroMensal registro = new registroMensal();
                registro reg = new registro();
                reg.setIdRegistro(result.getInt("id"));
                reg.setIdFuncionario(result.getInt("id_funcionario"));
                reg.setHoraEntrada(result.getString("hora_entrada"));
                reg.setSaidaAlmoco(result.getString("saida_almoco"));
                reg.setRetornoAlmoco(result.getString("retorno_almoco"));
                reg.setHoraSaida(result.getString("hora_saida"));
                reg.setData(result.getString("data"));
                reg.setAlteracao(result.getString("alteracao"));

                registro.setRegistro(reg);

            }







        return registros;
        
    }

}
