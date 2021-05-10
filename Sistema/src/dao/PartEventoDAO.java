package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import jdbc.ConnectionFactory;
import pojo.BoaDaSemana;
import pojo.Evento;
import pojo.Local;
import pojo.PartEvento;
import pojo.Participante;

/**
 *
 * @author gabri
 */
public class PartEventoDAO {
    private Connection connection;

    public PartEventoDAO() { }
    
    public boolean addPartEvento(PartEvento partEvento){
        String sql = "INSERT INTO participaevento (evento_id,participante_id) values(?,?)";
        this.connection = new ConnectionFactory().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,partEvento.getEvento_id());
            stmt.setInt(2,partEvento.getParticipante_id());
            
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
    public boolean deletaPartEvento(PartEvento partEvento){
        String sql = "DELETE FROM participaevento WHERE evento_id="+partEvento.getEvento_id()
                +"AND participante_id ="+partEvento.getParticipante_id();
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
    
    public List<Integer> idsEvento() throws SQLException{
        List<Integer> ids = new ArrayList<Integer>();
        
        String sql = "select DISTINCT evento_id from participaevento ORDER BY evento_id";
        this.connection = new ConnectionFactory().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            // int i = 1;
            while(rs.next()){
                int id = rs.getInt("evento_id");
                ids.add(id);
                // i++;
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
        return ids;
    }
    
    public void listarPartEvento() throws SQLException{
        List<Integer> ids = idsEvento();
        String sql = "SELECT E.evento_id,E.nome_evento,E.data_evento,E.hora_evento,P.nome_participante,E.id_local,E.descricao_evento"
                + " FROM "+"(SELECT * FROM participaevento) PE, evento E, participante P where E.evento_id = "+
                "PE.evento_id and P.participante_id = PE.participante_id ORDER BY E.evento_id;";
        this.connection = new ConnectionFactory().getConnection();
        boolean primeira = true;
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int i;            
            i = 0;
            while(rs.next()){
                int evento_id = rs.getInt("evento_id");
                String nome_local = localName(rs.getInt("id_local"));
                String nome_evento = rs.getString("nome_evento");
                String descricao_evento = rs.getString("descricao_evento");
                LocalDate data_evento = rs.getObject("data_evento", LocalDate.class);
                LocalTime hora_evento = rs.getObject("hora_evento", LocalTime.class);
                String nome_participante = rs.getString("nome_participante");
                
                BoaDaSemana bds = new BoaDaSemana(evento_id,nome_evento,data_evento,hora_evento,nome_participante,nome_local,descricao_evento);
                if(primeira){
                    System.out.println("---------------------------------------------------");
                    System.out.print(bds.toStringUser());
                    System.out.println("Participantes:");
                    primeira = false;
                }else if(evento_id != ids.get(i)){
                    System.out.println();
                    System.out.println("---------------------------------------------------");
                    System.out.print(bds.toStringUser());
                    System.out.println("Participantes:");
                    i++;
                }
                System.out.println(nome_participante);
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------");
    }
    
    public void listarSemana() throws SQLException{
        String sql = "SELECT E.evento_id,E.nome_evento,E.data_evento,E.hora_evento,P.nome_participante,E.id_local FROM "+
                "(SELECT * FROM participaevento) PE, evento E, participante P where E.evento_id = "+
                "PE.evento_id and P.participante_id = PE.participante_id AND (data_evento - CURRENT_DATE) <= 7 ORDER BY E.evento_id;";
        List<Integer> ids = idsEvento();
        this.connection = new ConnectionFactory().getConnection();
        boolean primeira = true;
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int i;            
            i = 0;
            while(rs.next()){
                int evento_id = rs.getInt("evento_id");
                String nome_local = localName(rs.getInt("id_local"));
                String nome_evento = rs.getString("nome_evento");
                LocalDate data_evento = rs.getObject("data_evento", LocalDate.class);
                LocalTime hora_evento = rs.getObject("hora_evento", LocalTime.class);
                String nome_participante = rs.getString("nome_participante");
                BoaDaSemana bds = new BoaDaSemana(evento_id,nome_evento,data_evento,hora_evento,nome_participante,nome_local);
                if(primeira){
                    System.out.println("---------------------------------------------------");
                    System.out.print(bds.toString());
                    System.out.println("Participantes:");
                    primeira = false;
                }else if(evento_id != ids.get(i)){
                    System.out.println();
                    System.out.println("---------------------------------------------------");
                    System.out.print(bds.toString());
                    System.out.println("Participantes:");
                    i++;
                }
                System.out.println(nome_participante);
            }
        stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------");
    }
    
    public ArrayList<Participante> listarParticipantes(String nome) throws SQLException{
        String sql = "SELECT * FROM participante WHERE nome_participante LIKE '"+nome+"%'";
        ArrayList<Participante> listaParticipantes = new ArrayList<Participante>();
        
        this.connection = new ConnectionFactory().getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                nome = rs.getString("nome_participante");
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
    
    public void listarParticipanteEvento(int id) throws SQLException{
        String sql = "SELECT E.evento_id,E.nome_evento,E.data_evento,E.hora_evento,E.id_local,E.descricao_evento,"+
        "PE.participante_id FROM (SELECT * FROM participaevento WHERE participante_id = "+id+") PE, evento E "+
        "WHERE E.evento_id = PE.evento_id ORDER BY E.evento_id";
        
        this.connection = new ConnectionFactory().getConnection();
        // boolean primeira = true;
        // List<Integer> ids = idsEvento();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            // int i;            
            // i = 0;
            while(rs.next()){
                int evento_id = rs.getInt("evento_id");
                String nome_local = localName(rs.getInt("id_local"));
                String nome_evento = rs.getString("nome_evento");
                String descricao_evento = rs.getString("descricao_evento");
                LocalDate data_evento = rs.getObject("data_evento", LocalDate.class);
                LocalTime hora_evento = rs.getObject("hora_evento", LocalTime.class);
                int id_part = rs.getInt("participante_id");
                String nome_participante = participanteName(id_part);
                BoaDaSemana bds = new BoaDaSemana(evento_id,nome_evento,data_evento,hora_evento,nome_participante,nome_local,descricao_evento);
                System.out.println("\n---------------------------------------------------");
                System.out.print(bds.toStringUser());
                }
        stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("\n---------------------------------------------------");
    }
   
    public ArrayList<Evento> listarEventos(String nome_evento){
        String sql = "SELECT * FROM evento WHERE nome_evento LIKE '"+nome_evento+"%'";
        ArrayList<Evento> listaEventos = new ArrayList<Evento>();
        
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
                int evento_id = rs.getInt("evento_id");
                
                Evento evento = new Evento(evento_id,nome,descricao,data,hora,local,qtd);
                listaEventos.add(evento);
            }
            stmt.close();
        }catch (SQLException e) {
			System.err.println(e.getMessage());
        } 
        return listaEventos;
    }
    
    public void listarEvento(Evento evento) throws SQLException{
        String sql = "SELECT P.nome_participante FROM (SELECT PE.participante_id FROM participaevento PE WHERE"
                +" PE.evento_id = "+evento.getEvento_id()+") S, participante P WHERE S.participante_id = P.participante_id";
        
        this.connection = new ConnectionFactory().getConnection();
        // String nome_local = localName(evento.getEvento_id());
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Participantes:");
            while(rs.next()){
                String nome_participante = rs.getString("nome_participante");                
                System.out.println(nome_participante);
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public ArrayList<Local> listarLocais(String nome_local) throws SQLException{        
        String sql = "SELECT * FROM local WHERE nome_local LIKE '"+nome_local+"%'";
        ArrayList<Local> listarLocais = new ArrayList<Local>();
        
        this.connection = new ConnectionFactory().getConnection();
        try{            
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                String nome = rs.getString("nome_local");
                String cidade_local = rs.getString("cidade_local");
                String bairro = rs.getString("bairro");
                String rua = rs.getString("rua");
                int numero_local = rs.getInt("numero_local");
                int cep_local = rs.getInt("cep_local");
                int id = rs.getInt("local_id");
                
                Local local = new Local(id,nome,cidade_local,bairro,rua,numero_local,cep_local);
                listarLocais.add(local);
            }
            stmt.close();
        }catch (SQLException e) {
			System.err.println(e.getMessage());
        } 
        return listarLocais;
    }
    public void listarLocal(Local local) throws SQLException{
        String sql = "SELECT RS.evento_id,RS.nome_evento,RS.data_evento,RS.hora_evento,RS.descricao_evento,P.nome_participante FROM"
                     +"(SELECT E.evento_id,E.nome_evento,E.data_evento,E.hora_evento,PE.participante_id,E.descricao_evento FROM participaevento PE,"
                     +"(SELECT * FROM evento WHERE id_local = "+local.getLocal_id()+") E WHERE E.evento_id = PE.evento_id) RS,"
                     +"participante P where P.participante_id = RS.participante_id ORDER BY RS.evento_id";
        
        this.connection = new ConnectionFactory().getConnection();
        // boolean primeira = true;
        List<Integer> ids = idsEvento();
        System.out.println(local.getNome_local());
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int i;            
            i = 0;
            int jafoi=0;
            OUTER:
            while(rs.next()){
                int evento_id = rs.getInt("evento_id");
                String nome_evento = rs.getString("nome_evento");
                String descricao_evento = rs.getString("descricao_evento");
                LocalDate data_evento = rs.getObject("data_evento", LocalDate.class);
                LocalTime hora_evento = rs.getObject("hora_evento", LocalTime.class);
                String nome_participante = rs.getString("nome_participante");
                String nome_local = localName(local.getLocal_id());
                BoaDaSemana bds = new BoaDaSemana(evento_id,nome_evento,data_evento,hora_evento,nome_participante,nome_local,descricao_evento);
                Evento evento = new Evento(evento_id,nome_evento, descricao_evento, data_evento, hora_evento, local.getLocal_id(), 0);
                
                while(ids.get(i)!=evento_id){
                    if(ids.size() == i+1) break OUTER;
                    if(ids.get(i) > evento_id) break;
                    i++;
                }
                if(evento_id == ids.get(i) && jafoi != evento_id){
                    System.out.println("---------------------------------------------------");
                    System.out.print(bds.toStringUser());
                    System.out.println();
                    listarEvento(evento);
                    jafoi = evento_id;
                }
                
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("\n---------------------------------------------------");
    }
    public String localName(int id) throws SQLException{
        String sql = "SELECT nome_local FROM local WHERE local_id = "+id;
        this.connection = new ConnectionFactory().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return rs.getString("nome_local");
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    public String participanteName(int id) throws SQLException{
        String sql = "SELECT nome_participante FROM participante WHERE participante_id = "+id;
        this.connection = new ConnectionFactory().getConnection();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return rs.getString("nome_participante");
            }
            stmt.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}

