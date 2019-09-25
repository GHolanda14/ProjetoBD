/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author holanda
 */
public class Participante {
    private String nome_participante;
    private String email;
    private int participante_id;
    
    public String getNome_participante() {
        return nome_participante;
    }

    public void setNome_participante(String nome_participante) {
        this.nome_participante = nome_participante;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int getParticipante_id() {
        return participante_id;
    }

    public void setParticipante_id(int participante_id) {
        this.participante_id = participante_id;
    }
    
    @Override
    public String toString(){
        return "[id = "+participante_id+"]\nNome: "+nome_participante+"\nEmail: "+email;
    }
    public Participante(int participante_id,String nome_participante,String email){
        this.participante_id = participante_id;
        this.nome_participante = nome_participante;
        this.email = email;
    }
    public Participante(String nome_participante,String email){
        this.nome_participante = nome_participante;
        this.email = email;
    }
    
    
}
