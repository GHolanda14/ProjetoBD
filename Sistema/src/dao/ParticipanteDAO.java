/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojo.Participante;
import jdbc.ConnectionFactory;
/**
 *
 * @author holanda
 */
public class ParticipanteDAO {
    private Connection connection;

    public boolean addParticipante(Participante participante){
        String sql = "INSERT INTO participante (nome_participante,email_participante) VALUES (?,?)";
        
        this.connection = new ConnectionFactory().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1,participante.getNome_participante());
            stmt.setString(2,participante.getEmail());
            
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
    
    public boolean editarParticipante(int id,String novoNome,String novoEmail){
        String sql = "UPDATE participante SET nome_participante = '"+novoNome+"',email_participante = '"+novoEmail+"'WHERE participante_id = "+id;
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
    
    public boolean deletaParticipante(int id){
        String sql = "DELETE FROM participante WHERE participante_id = "+id;
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
    
    public ArrayList<Participante> listarParticipantes() throws SQLException{        
        String sql = "SELECT * FROM participante";
        ArrayList<Participante> listaParticipantes = new ArrayList<Participante>();
        
        this.connection = new ConnectionFactory().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                String nome = rs.getString("nome_participante");
                String email = rs.getString("email_participante");
                int id = rs.getInt("participante_id");
                Participante participante = new Participante(id,nome, email);
                listaParticipantes.add(participante);
            }
            stmt.close();
        }catch (SQLException e) {
			System.err.println(e.getMessage());
        } 
        return listaParticipantes;
    }
    
}
