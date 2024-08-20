/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import model.assinatura;
import model.funcionario;
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

                funcionario func = new funcionario();
                func.setIdFuncionario(result.getInt("id_funcionario"));
                func.setNome(result.getString("nome"));
                func.setMatricula(result.getInt("matricula"));
                func.setSetor(result.getString("setor"));
                func.setTurno(result.getString("turno"));
                func.setFuncao(result.getString("funcao"));
                func.setDataAdmissao(result.getString("data_admissao"));
                func.setEscala(result.getString("escala"));
                func.setHorario(result.getString("horario"));
                func.setHorasSemanais(result.getString("horas_semanais"));
                func.setCodigoDeBarras(result.getString("codigo_de_barras"));
                func.setSenha(result.getString("senha"));
                func.setAdm(result.getBoolean("adm"));

                registro.setFuncionario(func);

                registros.add(registro);

            }

            String queryAssinaturas = "SELECT * FROM assinatura a inner join funcionarios f on (a.id_funcionario = f.id) WHERE a.mes = " + mes;

            PreparedStatement statementAssinaturas = connection.prepareStatement(queryAssinaturas);
            ResultSet resultAssinaturas = statementAssinaturas.executeQuery();

            while (resultAssinaturas.next()) {
                int funcionarioId = resultAssinaturas.getInt("id_funcionario");
                for (registroMensal registro : registros) {
                    if (registro.getFuncionario().getIdFuncionario() == funcionarioId) {

                        assinatura ass = new assinatura(resultAssinaturas.getInt("id"), resultAssinaturas.getInt("id_funcionario"), resultAssinaturas.getInt("mes"), resultAssinaturas.getString("hora_assinatura"), resultAssinaturas.getString("data_Assinatura") );

                        registro.setAssinatura(ass);
                        
                    }
                }
            }






        return registros;
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
}