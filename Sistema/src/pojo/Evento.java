/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import dao.PartEventoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import jdbc.ConnectionFactory;


/**
 *
 * @author holanda
 */
public class Evento {
    private int evento_id;
    private String nome_evento;    
    private String descricao_evento;
    private int local_evento;
    private LocalDate data_evento;
    private LocalTime hora_evento;
    private int qtd_participante;
    
    public int getEvento_id() {
        return evento_id;
    }

    public void setEvento_id(int evento_id) {
        this.evento_id = evento_id;
    }
    
    public int getQtd_participante() {
        return qtd_participante;
    }

    public void setQtd_participante(int qtd_participante) {
        this.qtd_participante = qtd_participante;
    }
    
    public LocalTime getHora_evento() {
        return hora_evento;
    }

    public void setHora_evento(LocalTime hora_evento) {
        this.hora_evento = hora_evento;
    }
    public String getNome_evento() {
        return nome_evento;
    }

    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }
    public String getDescricao_evento() {
        return descricao_evento;
    }

    public void setDescricao_evento(String descricao_evento) {
        this.descricao_evento = descricao_evento;
    }

    public int getLocal_evento() {
        return local_evento;
    }

    public void setLocal_evento(int local_evento) {
        this.local_evento = local_evento;
    }
    public LocalDate getData_evento() {
        return data_evento;
    }

    public void setData_evento(LocalDate data_evento) {
        this.data_evento = data_evento;
    }
    

    public String toString2() throws SQLException{
        String[] dat;
        dat = data_evento.toString().split("-");
        
        PartEventoDAO ld = new PartEventoDAO();
        return "Id: "+evento_id+" => ["+nome_evento+"]\nLocal: "+ld.localName(local_evento)+"\nData: "+dat[2]+"/"+dat[1]+"/"+dat[0]+"\nHorario: "+hora_evento.toString();
    }
    public String toStringUnico() throws SQLException{
        String[] dat;
        dat = data_evento.toString().split("-");
        PartEventoDAO ld = new PartEventoDAO();
        return "["+nome_evento+"]\nLocal: "+ld.localName(local_evento)+"\nData: "+dat[2]+"/"+dat[1]+"/"+dat[0]+"\nHorario: "+hora_evento.toString();
    }
    public Evento(String nome,String descricao,LocalDate data,LocalTime hora,int local,int qtd, int... id){
        nome_evento = nome;
        descricao_evento = descricao;
        local_evento = local;
        data_evento = data;
        hora_evento = hora;
        qtd_participante = qtd;
        evento_id = id;
    }
}
