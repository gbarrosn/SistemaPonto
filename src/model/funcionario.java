/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author gbarrosn
 */
public class funcionario {
    static int idFuncionario;
    String nome;
    int matricula;
    String setor;
    String turno;
    String funcao;
    String dataAdmissao;
    String escala;
    String horario;
    String horasSemanais;
    String codigoDeBarras;
    String senha;

    /*public funcionario(int idFuncionario, String nome, String matricula, String setor, String turno, String funcao, String dataAdmissao, String escala, String horario, String horasSemanais, String codigoDeBarras, String senha) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.matricula = matricula;
        this.setor = setor;
        this.turno = turno;
        this.funcao = funcao;
        this.dataAdmissao = dataAdmissao;
        this.escala = escala;
        this.horario = horario;
        this.horasSemanais = horasSemanais;
        this.codigoDeBarras = codigoDeBarras;
        this.senha = senha;
    }*/

    public static int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int i) {
        this.matricula = i;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(String horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
