/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author gbarrosn
 */
public class registro {
    int idRegistro;
    String setor;
    String turno;
    String funcao;
    int idFuncionario;
    String horaEntrada;
    String saidaAlmoco;
    String retornoAlmoco;
    String horaSaida;
    String data;
    int mes;
    String nomeFuncionario;
    String alteracao;
    boolean atestado;
    String saidaAntecipada;

    /*public registro(int idRegistro, int idFuncionario, String horaEntrada, String saidaAlmoco, String retornoAlmoco, String horaSaida, String data) {
        this.idRegistro = idRegistro;
        this.setor = setor;
        this.turno = turno;
        this.funcao = funcao;
        this.idFuncionario = idFuncionario;
        this.horaEntrada = horaEntrada;
        this.saidaAlmoco = saidaAlmoco;
        this.retornoAlmoco = retornoAlmoco;
        this.horaSaida = horaSaida;
        this.data = data;
        this.mes = mes;
    }*/

    // Getters

    public String getSaidaAntecipada() {
        return saidaAntecipada;
    }

    public void setSaidaAntecipada(String saidaAntecipada) {
        this.saidaAntecipada = saidaAntecipada;
    }

    public boolean isAtestado() {
        return atestado;
    }

    public String getAlteracao() {
        return alteracao;
    }

    public String getNomeFuncionario () {
        return nomeFuncionario;
    }
    public int getIdRegistro() {
        return idRegistro;
    }

    public String getSetor() {
        return setor;
    }

    public String getTurno() {
        return turno;
    }

    public String getFuncao() {
        return funcao;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getSaidaAlmoco() {
        return saidaAlmoco;
    }

    public String getRetornoAlmoco() {
        return retornoAlmoco;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public String getData() {
        return data;
    }

    public int getMes() {
        return mes;
    }

    // Setters
    public void setAtestado(boolean atestado) {
        this.atestado = atestado;
    }

    public void setAlteracao(String alteracao) {
        this.alteracao = alteracao;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setSaidaAlmoco(String saidaAlmoco) {
        this.saidaAlmoco = saidaAlmoco;
    }

    public void setRetornoAlmoco(String retornoAlmoco) {
        this.retornoAlmoco = retornoAlmoco;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String dataToDia(String data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(data, formatter);
          
        DayOfWeek diaDaSemana =  (date).getDayOfWeek();                                               
        // Configura o dia da semana conforme a data                            
        switch (diaDaSemana) 
        {                                
            case MONDAY:                             
                return "SEGUNDA";
            case TUESDAY:
                return "TERÇA";
            case WEDNESDAY:
                return "QUARTA";
            case THURSDAY:
                return "QUINTA";
            case FRIDAY:
                return "SEXTA";
            case SATURDAY:
                return "SÁBADO";
            case SUNDAY:
                return "DOMINGO";
            
        }
        return data;
    }

}
