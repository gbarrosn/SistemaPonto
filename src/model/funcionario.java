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
    private String id;
        private String name;
        private String setor;
        private String turno;
        private String funcao;
        private String dataAdmissao;
        private String escala;
        private String horario;
        private String horasSemanais;
        private String codigoDeBarras;
        private String senha;

        public funcionario(String id, String name) {
            this.id = id;
            this.name = name;
        }           

        public funcionario(String id, String name, String setor, String turno, String funcao, String dataAdmissao, String escala, String horario, String horasSemanais, String codigoDeBarras, String senha) {
            this.id = id;
            this.name = name;
            this.setor = setor;
            this.turno = turno;
            this.funcao = funcao;
            this.dataAdmissao = dataAdmissao;
            this.escala = escala;
            this.horario = horario;
            this.horasSemanais = horasSemanais;
            this.codigoDeBarras = codigoDeBarras;
            this.senha = senha;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getCodigoDeBarras() {
            return codigoDeBarras;
        }

        public void setCodigoDeBarras(String codigoDeBarras) {
            this.codigoDeBarras = codigoDeBarras;
        }

        public String getHorasSemanais() {
            return horasSemanais;
        }

        public void setHorasSemanais(String horasSemanais) {
            this.horasSemanais = horasSemanais;
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

        public String getDataAdmissao() {
            return dataAdmissao;
        }

        public void setDataAdmissao(String dataAdmissao) {
            this.dataAdmissao = dataAdmissao;
        }
        
        public String getFuncao() {
            return funcao;
        }

        public void setFuncao(String funcao) {
            this.funcao = funcao;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSetor() {
            return setor;
        }

        public String getTurno() {
            return turno;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' + 
                    ", setor='" + setor + '\'' +
                    ", turno='" + turno + '\'' +                   
                    '}';
        }
}
