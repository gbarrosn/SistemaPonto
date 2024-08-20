/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author gbarrosn
 */
public class registroMensal {

    /**
     * @return the funcionario
     */
    public funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the registro
     */
    public registro getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(registro registro) {
        this.registro = registro;
    }

    /**
     * @return the assinatura
     */
    public assinatura getAssinatura() {
        return assinatura;
    }

    /**
     * @param assinatura the assinatura to set
     */
    public void setAssinatura(assinatura assinatura) {
        this.assinatura = assinatura;
    }

    /**
     * @return the assinaturaCoordenacao
     */
    public assinaturaCoordenacao getAssinaturaCoordenacao() {
        return assinaturaCoordenacao;
    }

    /**
     * @param assinaturaCoordenacao the assinaturaCoordenacao to set
     */
    public void setAssinaturaCoordenacao(assinaturaCoordenacao assinaturaCoordenacao) {
        this.assinaturaCoordenacao = assinaturaCoordenacao;
    }
    private funcionario funcionario;
    private registro registro;
    private assinatura assinatura;
    private assinaturaCoordenacao assinaturaCoordenacao;

    
}
