/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author gbarrosn
 */
public class assinatura {
    int idAssinatura;
    int idFuncionario;
    int mes;
    String dataAssinatura;
    String horaAssinatura;

    public assinatura(int idAssinatura, int idFuncionario, int mes, String dataAssinatura, String horaAssinatura) {
        this.idAssinatura = idAssinatura;
        this.idFuncionario = idFuncionario;
        this.mes = mes;
        this.dataAssinatura = dataAssinatura;
        this.horaAssinatura = horaAssinatura;
    }
    // Getters
    public int getIdAssinatura() {
        return idAssinatura;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getDataAssinatura() {
        return dataAssinatura;
    }

    public String getHoraAssinatura() {
        return horaAssinatura;
    }

    public int getMes() {
        return mes;
    }   

    // Setters
    public void setIdAssinatura(int idAssinatura) {
        this.idAssinatura = idAssinatura;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setDataAssinatura(String dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public void setHoraAssinatura(String horaAssinatura) {
        this.horaAssinatura = horaAssinatura;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
