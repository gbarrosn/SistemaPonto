/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import model.assinatura;
import model.assinaturaCoordenacao;
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
    
    /**
     * Retrieves the monthly records for a given month.
     * 
     * @author gbarrosn
     * @param mes The month for which to retrieve the records.
     * @return A list of monthly records.
     * @throws RuntimeException if a SQL exception occurs.
     */
    public static List<registroMensal> buscarDadosDoMes(int mes) {
        List<registroMensal> registros = new ArrayList<>();

        String mesStr = mes < 10 ? "0" + mes : String.valueOf(mes);

        try {
            Connection connection = conectarBanco.conectar();
            String sql = "SELECT * FROM registros r inner join funcionarios f on (r.id_funcionario = f.id) WHERE r.data LIKE '%/" + mesStr + "/%'";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int idFuncionario = result.getInt("id_funcionario");
                boolean exists = false;
                registroMensal registroFuncionario = new registroMensal();
    
                for (registroMensal registro : registros) {
                    if (registro.getFuncionario().getIdFuncionario() == idFuncionario) {
                        exists = true;
                        registroFuncionario = registro;
                        break;
                    }
                }
    
                if (!exists) {
                    registroMensal registro = new registroMensal();

                    //carregando dados do funcionario
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
                    func.setServidor(result.getBoolean("servidor"));
                    func.setVinculo(result.getString("vinculo"));
                    func.setContrato(result.getString("contrato"));
                    func.setEmpresa(result.getString("empresa"));

                    registro.setFuncionario(func);

                    registro reg = new registro();

                    reg.setIdRegistro(result.getInt("id"));
                    reg.setIdFuncionario(result.getInt("id_funcionario"));
                    reg.setHoraEntrada(result.getString("hora_entrada"));
                    reg.setSaidaAlmoco(result.getString("saida_almoco"));
                    reg.setRetornoAlmoco(result.getString("retorno_almoco"));
                    reg.setHoraSaida(result.getString("saida"));
                    reg.setData(result.getString("data"));
                    reg.setAlteracao(result.getString("alteracao"));
                    reg.setAtestado(result.getBoolean("atestado"));
                    reg.setMes(Integer.parseInt(reg.getData().split("/")[1]));
                    reg.setSaidaAntecipada(result.getString("saida_antecipada"));

                    List<registro> registrosFuncionario = new ArrayList<>();
                    registrosFuncionario.add(reg);
                    registro.setRegistros(registrosFuncionario);

                    registros.add(registro);


                } else {
                    registro reg = new registro();

                    reg.setIdRegistro(result.getInt("id"));
                    reg.setIdFuncionario(result.getInt("id_funcionario"));
                    reg.setHoraEntrada(result.getString("hora_entrada"));
                    reg.setSaidaAlmoco(result.getString("saida_almoco"));
                    reg.setRetornoAlmoco(result.getString("retorno_almoco"));
                    reg.setHoraSaida(result.getString("saida"));
                    reg.setData(result.getString("data"));
                    reg.setAlteracao(result.getString("alteracao"));
                    reg.setAtestado(result.getBoolean("atestado"));
                    reg.setMes(Integer.parseInt(reg.getData().split("/")[1]));
                    reg.setSaidaAntecipada(result.getString("saida_antecipada"));

                    List<registro> registrosFuncionario = registroFuncionario.getRegistros();
                    registrosFuncionario.add(reg);
                    registroFuncionario.setRegistros(registrosFuncionario);
                }

               

            }

            String queryAssinaturas = "SELECT * FROM assinatura a inner join funcionarios f on (a.id_funcionario = f.id) WHERE a.mes = " + mes;

            PreparedStatement statementAssinaturas = connection.prepareStatement(queryAssinaturas);
            ResultSet resultAssinaturas = statementAssinaturas.executeQuery();

            while (resultAssinaturas.next()) {
                int funcionarioId = resultAssinaturas.getInt("id_funcionario");
                for (registroMensal registro : registros) {
                    if (registro.getFuncionario().getIdFuncionario() == funcionarioId) {

                        assinatura ass = new assinatura(resultAssinaturas.getInt("id"), resultAssinaturas.getInt("id_funcionario"), resultAssinaturas.getInt("mes"), resultAssinaturas.getString("data_Assinatura"), resultAssinaturas.getString("hora_assinatura"));

                        registro.setAssinatura(ass);
                        
                    }
                }
            }

            String queryAssinaturaCoordenacao = "SELECT * FROM assinaturaCoordenacao ac inner join funcionarios f on (ac.id_coordenacao = f.id) where ac.mes = " + mes;

            PreparedStatement statementAssinaturaCoord = connection.prepareStatement(queryAssinaturaCoordenacao);
            ResultSet resultAssCoord = statementAssinaturaCoord.executeQuery();

            while (resultAssCoord.next()) {
                int funcId = resultAssCoord.getInt("id_funcionario");
                
                for (registroMensal r : registros) {
                    if (r.getFuncionario().getIdFuncionario() == funcId) {

                        assinaturaCoordenacao assC = new assinaturaCoordenacao();

                        assC.setIdAssinatura(resultAssCoord.getInt("id"));
                        assC.setIdCoordenacao(resultAssCoord.getInt("id_coordenacao"));
                        assC.setMes(resultAssCoord.getInt("mes"));
                        assC.setHoraAssinatura(resultAssCoord.getString("hora_assinatura"));
                        assC.setDataAssinatura(resultAssCoord.getString("data_assinatura"));
                        assC.setNomeCoordenacao(resultAssCoord.getString("nome"));

                        r.setAssinaturaCoordenacao(assC);
                    }
                }
            }
            /*

            registroMensal registro = new registroMensal();
        registro = registros.get(0);

        for (registro reg : registro.getRegistros()) {
            System.out.println("Registro:");
            System.out.println("ID: " + reg.getIdRegistro());
            System.out.println("ID Funcionario: " + reg.getIdFuncionario());
            System.out.println("Hora Entrada: " + reg.getHoraEntrada());
            System.out.println("Saida Almoco: " + reg.getSaidaAlmoco());
            System.out.println("Retorno Almoco: " + reg.getRetornoAlmoco());
            System.out.println("Hora Saida: " + reg.getHoraSaida());
            System.out.println("Data: " + reg.getData());
            System.out.println("Alteracao: " + reg.getAlteracao());
            System.out.println("Atestado: " + reg.isAtestado());
        }

            System.out.println("Funcionario:");
            System.out.println("ID: " + registro.getFuncionario().getIdFuncionario());
            System.out.println("Nome: " + registro.getFuncionario().getNome());
            System.out.println("Matricula: " + registro.getFuncionario().getMatricula());
            System.out.println("Setor: " + registro.getFuncionario().getSetor());
            System.out.println("Turno: " + registro.getFuncionario().getTurno());
            System.out.println("Funcao: " + registro.getFuncionario().getFuncao());
            System.out.println("Data Admissao: " + registro.getFuncionario().getDataAdmissao());
            System.out.println("Escala: " + registro.getFuncionario().getEscala());
            System.out.println("Horario: " + registro.getFuncionario().getHorario());
            System.out.println("Horas Semanais: " + registro.getFuncionario().getHorasSemanais());
            System.out.println("Codigo de Barras: " + registro.getFuncionario().getCodigoDeBarras());
            System.out.println("Senha: " + registro.getFuncionario().getSenha());
            System.out.println("Adm: " + registro.getFuncionario().isAdm());
            System.out.println("Assinatura:");
            System.out.println("ID Funcionario: " + registro.getAssinatura().getIdFuncionario());
            System.out.println("Mes: " + registro.getAssinatura().getMes());
            System.out.println("Hora Assinatura: " + registro.getAssinatura().getHoraAssinatura());
            System.out.println("Data Assinatura: " + registro.getAssinatura().getDataAssinatura());
            System.out.println("Assinatura Coordenacao:");
            System.out.println("ID Assinatura: " + registro.getAssinaturaCoordenacao().getIdAssinatura());
            System.out.println("ID Coordenacao: " + registro.getAssinaturaCoordenacao().getIdCoordenacao());
            System.out.println("Mes: " + registro.getAssinaturaCoordenacao().getMes());
            System.out.println("Hora Assinatura: " + registro.getAssinaturaCoordenacao().getHoraAssinatura());
            System.out.println("Data Assinatura: " + registro.getAssinaturaCoordenacao().getDataAssinatura());
            System.out.println("Nome Coordenacao: " + registro.getAssinaturaCoordenacao().getNomeCoordenacao());
            System.out.println();  
            // */

        return registros;
        
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

public static void main(String[] args) {
    List<registroMensal> registros = buscarDadosDoMes(8);
    
}
}