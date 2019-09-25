package dao;

/**
 *
 * @author gabri
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import pojo.Local;
import jdbc.ConnectionFactory;
import pojo.BoaDaSemana;
import pojo.Evento;
public class LocalDAO {
    private Connection connection;
    
   public boolean addLocal(Local local){
        String sql = "INSERT INTO local (nome_local,cidade_local,bairro,rua,numero_local,cep_local) VALUES (?,?,?,?,?,?)";
        
        this.connection = new ConnectionFactory().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, local.getNome_local());
            stmt.setString(2, local.getCidade_local());            
            stmt.setString(3,local.getBairro());
            stmt.setString(4,local.getRua());
            stmt.setInt(5,local.getNumero_local());
            stmt.setInt(6, local.getCep_Local());
            
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
    
    public boolean editarLocal(int id,Local local){
        String sql = "UPDATE local set nome_local = '"+local.getNome_local()+"', cidade_local = '"
                +local.getCidade_local()+"',bairro = '"+local.getBairro()+"', rua = '"
                +local.getRua()+"', numero_local = "+local.getNumero_local()
                +", cep = "+local.getCep_Local()+"WHERE local_id = "+id;
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
    
    public boolean deletaLocal(int local_id){
        String sql = "DELETE FROM local WHERE local_id="+local_id;
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
    
    public ArrayList<Local> listarLocais() throws SQLException{        
        String sql = "SELECT * FROM local";
        ArrayList<Local> listarLocais = new ArrayList<Local>();
        
        this.connection = new ConnectionFactory().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                String nome_local = rs.getString("nome_local");
                String cidade_local = rs.getString("cep_local");
                String bairro = rs.getString("bairro");
                String rua = rs.getString("rua");
                int numero_local = rs.getInt("numero_local");
                int cep_local = rs.getInt("cep_local");
                int id = rs.getInt("local_id");
                
                Local local = new Local(id,nome_local,cidade_local,bairro,rua,numero_local,cep_local);
                listarLocais.add(local);
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
        return listarLocais;
    }
    public Local listaLocal(int id) throws SQLException{
        String sql = "SELECT * FROM local WHERE local_id ="+id;
        this.connection = new ConnectionFactory().getConnection();
        Local lis = null;
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int local_id = rs.getInt("local_id");
                String nome_evento = rs.getString("nome_local");
                String cidade_local = rs.getString("cidade_local");
                String bairro = rs.getString("bairro");
                String rua = rs.getString("rua");
                int numero = rs.getInt("numero_local");
                int cep = rs.getInt("cep_local");
                lis = new Local(local_id,nome_evento,cidade_local,bairro,rua,numero,cep);
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    return lis;
    }
}
