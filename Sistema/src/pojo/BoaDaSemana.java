package pojo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author gabri
 */
public class BoaDaSemana {
    private int evento_id;
    private String nome_evento;    
    private String nome_local;
    private String descricao_evento;
    private LocalDate data_evento;
    private LocalTime hora_evento;
    private String nome_participante;
    
    public int getEvento_id() {
        return evento_id;
    }
    public void setEvento_id(int evento_id) {
        this.evento_id = evento_id;
    }
    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }
    public String getNome_evento() {
        return nome_evento;
    }
    public String getNome_local() {
        return nome_local;
    }
    public String getDescricao_evento() {
        return descricao_evento;
    }

    public void setDescricao_evento(String descricao_evento) {
        this.descricao_evento = descricao_evento;
    }
    
    public void setNome_local(String nome_local) {
        this.nome_local = nome_local;
    }
    public LocalDate getData_evento() {
        return data_evento;
    }

    public LocalTime getHora_evento() {
        return hora_evento;
    }

    public String getNome_participante() {
        return nome_participante;
    }
    
    @Override
    public String toString(){
        String[] dat;
        dat = data_evento.toString().split("-");
        String rslt= "["+nome_evento+"]\nLocal: "+nome_local+"\nData: "+dat[2]+"/"+dat[1]+"/"+dat[0]+"\nHorario: "+getHora_evento().toString()+"\n";
        return rslt;
    }
    public String toStringUser(){
        String[] dat;
        dat = data_evento.toString().split("-");
        String rslt= "["+nome_evento+"]\nLocal: "+nome_local+"\nData: "+dat[2]+"/"+dat[1]+"/"+dat[0]+"\nHorario: "+getHora_evento().toString()+"\nDescricao: "+descricao_evento;
        return rslt;
    }
    public String toStringLocal(){
        String[] dat;
        dat = data_evento.toString().split("-");
        String rslt= "["+nome_evento+"]\nData: "+dat[2]+"/"+dat[1]+"/"+dat[0]+"\nHorario: "+getHora_evento().toString()+"\n";
        return rslt;
    }
    
    public BoaDaSemana(int evento_id,String nome_evento,LocalDate data_evento,LocalTime hora_evento,String nome_participante){
        this.evento_id = evento_id;
        this.nome_evento = nome_evento;
        this.data_evento = data_evento;
        this.hora_evento = hora_evento;
        this.nome_participante = nome_participante;
    }
    public BoaDaSemana(int evento_id,String nome_evento,LocalDate data_evento,LocalTime hora_evento,String nome_participante,String nome_local){
        this.evento_id = evento_id;
        this.nome_evento = nome_evento;
        this.data_evento = data_evento;
        this.hora_evento = hora_evento;
        this.nome_participante = nome_participante;
        this.nome_local = nome_local;
    }
    public BoaDaSemana(int evento_id,String nome_evento,LocalDate data_evento,LocalTime hora_evento,String nome_participante,String nome_local,String descricao_evento){
        this.evento_id = evento_id;
        this.nome_evento = nome_evento;
        this.data_evento = data_evento;
        this.hora_evento = hora_evento;
        this.nome_participante = nome_participante;
        this.nome_local = nome_local;
        this.descricao_evento = descricao_evento;
    }
}
