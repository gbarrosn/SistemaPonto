/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author gbarrosn
 */
public class registro {
     private String id;
        private String name;
        private String setor;
        private String turno;
        private String beginTime;
        private String endTime;
        private String date;
        private String funcao;
        private String dataAdmissao;
        private String escala = "";
        private String horario = "";
        private String horasSemanais = "";
        private String codigoDeBarras = "";
        private String dataAssinatura = "";
        private String horaAssinatura = "";
        private String nomeAssinaturaCoordenacao = "";
        private String horaAssinaturaCoordenacao = "";
        private String dataAssinaturaCoordenacao = "";

        public registro(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public registro(String id, String name, String setor, String turno, String funcao, String dataAdmissao, String escala, String horario, String horasSemanais, String codigoDeBarras, String dataAssinatura, String horaAssinatura) {
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
            this.dataAssinatura = dataAssinatura;
            this.horaAssinatura = horaAssinatura;
        }

        public String getNomeAssinaturaCoordenacao() {
            return nomeAssinaturaCoordenacao;
        }

        public void setNomeAssinaturaCoordenacao(String nomeAssinaturaCoordenacao) {
            this.nomeAssinaturaCoordenacao = nomeAssinaturaCoordenacao;
        }

        public String getHoraAssinaturaCoordenacao() {
            return horaAssinaturaCoordenacao;
        }

        public void setHoraAssinaturaCoordenacao(String horaAssinaturaCoordenacao) {
            this.horaAssinaturaCoordenacao = horaAssinaturaCoordenacao;
        }

        public String getDataAssinaturaCoordenacao() {
            return dataAssinaturaCoordenacao;
        }

        public void setDataAssinaturaCoordenacao(String dataAssinaturaCoordenacao) {
            this.dataAssinaturaCoordenacao = dataAssinaturaCoordenacao;
        }

        public String getDataAssinatura() {
            return dataAssinatura;
        }

        public void setDataAssinatura(String dataAssinatura) {
            this.dataAssinatura = dataAssinatura;
        }

        public String getHoraAssinatura() {
            return horaAssinatura;
        }

        public void setHoraAssinatura(String horaAssinatura) {
            this.horaAssinatura = horaAssinatura;
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

        public String getBeginTime() {
            return beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getDate(){
            return date;
        }

        public void setSetor(String setor) {
            this.setor = setor;
        }        

        public void setTurno(String turno) {
            this.turno = turno;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }       

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }        

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Registro{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", setor='" + setor + '\'' +
                    ", turno='" + turno + '\'' + 
                    ", beginTime='" + beginTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
}
