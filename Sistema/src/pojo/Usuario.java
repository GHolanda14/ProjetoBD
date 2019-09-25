package pojo;
import dao.EventoDAO;
import dao.LocalDAO;
import dao.PartEventoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
public class Usuario {
    private PartEventoDAO partDAO = new PartEventoDAO();
    public void show_menu(){
        System.out.println("1 - Quero ver tudo!");
        System.out.println("2 - Qual da é a boa semana?");
        System.out.println("3 - Pesquisar local");
        System.out.println("4 - Pesquisar evento");
        System.out.println("5 - Pesquisar agenda participante");
        System.out.println("6 - Onde fica?");
        System.out.println("9 - Menu");
        System.out.println("0 - Voltar");
    }
    public void visualizarBoas() throws SQLException{
        partDAO.listarSemana();
    }
    public void visualizarTudo() throws SQLException{
        partDAO.listarPartEvento();
    }
    public boolean pesquisaParticipante(String nome_participante) throws SQLException{
        ArrayList<Participante> partLista = partDAO.listarParticipantes(nome_participante);
        if(!partLista.isEmpty()){
            for(Participante p : partLista){
                System.out.println(p.toString());
                System.out.println("---------------------------------------------------");
            }
            return true;
        }else System.out.println("Nao existem participantes com esse nome!");
        return false;
    }
    
    public void listaParticipanteEvento(int id) throws SQLException{
        partDAO.listarParticipanteEvento(id);
    }
    
    public boolean pesquisaEvento(String nome_evento) throws SQLException{
        ArrayList<Evento> eventoLista = partDAO.listarEventos(nome_evento);
        if(!eventoLista.isEmpty()){
            for(Evento e : eventoLista){
                System.out.println(e.getNome_evento()+" id: "+e.getEvento_id());
                System.out.println("---------------------------------------------------");
            }
            return true;
        }else System.out.println("Nao existem eventos com esse nome!");
        return false;
    }
    
    public void listaEvento(int id)throws SQLException{
        EventoDAO eventoDAO = new EventoDAO();
        partDAO.listarEvento(eventoDAO.listaEvento(id));
    }
    
    public boolean pesquisaLocal(String nome_local) throws SQLException{
        ArrayList<Local> localLista = partDAO.listarLocais(nome_local);
        if(!localLista.isEmpty()){
            for(Local l : localLista){
                System.out.println(l.toString());
                System.out.println("---------------------------------------------------");
            }
            return true;
        }else System.out.println("Nao existem locais com esse nome!");
        return false;
    }
    
    public void listaLocal(int id)throws SQLException{
        LocalDAO localDAO = new LocalDAO();
        partDAO.listarLocal(localDAO.listaLocal(id));
    }
    
    public boolean pesquisaLocalOndeFica(String nome_local) throws SQLException{
        ArrayList<Local> localLista = partDAO.listarLocais(nome_local);
        if(!localLista.isEmpty()){
            for(Local l : localLista){
                System.out.println("Id: "+l.getLocal_id()+" "+l.getNome_local()+","+l.getCidade_local());
                System.out.println("---------------------------------------------------");
            }
            return true;
        }else System.out.println("Nao existem locais com esse nome!");
        return false;
    }
    
    public void listaLocalOndeFica(int id)throws SQLException{
        LocalDAO localDAO = new LocalDAO();
        System.out.println(localDAO.listaLocal(id).toString()+"\n");
        
    }
}
