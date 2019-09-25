/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author holanda
 */
import pojo.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import jdbc.ConnectionFactory;

public class EventoDAO {
    private Connection connection;

    public EventoDAO() { }
    
    public boolean addEvento(Evento evt){
        String sql = "INSERT INTO evento (nome_evento,descricao_evento,data_evento,hora_evento,id_local,"
                + "qtd_participante) values(?,?,?,?,?,?)";
        this.connection = new ConnectionFactory().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, evt.getNome_evento());
            stmt.setString(2, evt.getDescricao_evento());
            stmt.setObject(3, evt.getData_evento());
            stmt.setObject(4, evt.getHora_evento());
            stmt.setInt(5,evt.getLocal_evento());
            stmt.setInt(6,evt.getQtd_participante());
            
            
            int qtdRowsAffected = stmt.executeUpdate();
            stmt.close();
            return qtdRowsAffected > 0;
        }catch (SQLException e) {
            System.err.println(e.getMessage());
	}finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	return false;
    }
    public boolean editarEvento(int id,Evento evento){
        String sql = "UPDATE evento set nome_evento = '"+evento.getNome_evento()+"', descricao_evento = '"
                +evento.getDescricao_evento()+"',data_evento = '"+evento.getData_evento()+"', hora_evento = '"
                +evento.getHora_evento()+"', id_local= "+evento.getLocal_evento()+",qtd_participante ="
                +evento.getQtd_participante()+"WHERE evento_id = "+id;
        this.connection = new ConnectionFactory().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            int qtdRowsAffected = stmt.executeUpdate();
            stmt.close();
            return qtdRowsAffected > 0;
        }catch (SQLException e) {
            System.err.println(e.getMessage());
	}finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	return false;
    }
    public boolean deletaEvento(int evento_id){
        String sql = "DELETE FROM evento WHERE evento_id="+evento_id;
        this.connection = new ConnectionFactory().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            int qtdRowsAffected = stmt.executeUpdate();
            stmt.close();
            return qtdRowsAffected > 0;
        }catch (SQLException e) {
            System.err.println(e.getMessage());
	}finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	return false;
    }
    
    public ArrayList<Evento> listarEventos() throws SQLException{ 
        String sql = "SELECT * FROM evento";
        ArrayList<Evento> listarEventos = new ArrayList<Evento>();
        
        this.connection = new ConnectionFactory().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                String nome = rs.getString("nome_evento");
                String descricao = rs.getString("descricao_evento");
                LocalDate data = rs.getObject("data_evento", LocalDate.class);
                LocalTime hora = rs.getObject("hora_evento", LocalTime.class);
                int local = rs.getInt("id_local");
                int qtd = rs.getInt("qtd_participante");
                int id = rs.getInt("evento_id");
                
                Evento evento = new Evento(id,nome,descricao,data,hora,local,qtd);
                listarEventos.add(evento);
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
        return listarEventos;
    }
    public Evento listaEvento(int id) throws SQLException{
        String sql = "SELECT * FROM evento WHERE evento_id ="+id;
        this.connection = new ConnectionFactory().getConnection();
        Evento eve = null;
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome_evento");
                String descricao = rs.getString("descricao_evento");
                LocalDate data = rs.getObject("data_evento", LocalDate.class);
                LocalTime hora = rs.getObject("hora_evento", LocalTime.class);
                int local = rs.getInt("id_local");
                int qtd = rs.getInt("qtd_participante");
                int evento_id = rs.getInt("evento_id");

                eve = new Evento(evento_id,nome,descricao,data,hora,local,qtd);
                System.out.println(eve.toStringUnico());
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return eve;
    } 
}