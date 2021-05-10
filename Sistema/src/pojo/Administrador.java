package pojo;

import dao.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrador {
    private final int senha = 1234;
    private EventoDAO eventoDAO = new EventoDAO();
    private PartEventoDAO partDAO = new PartEventoDAO();
    private ParticipanteDAO participanteDAO = new ParticipanteDAO();
    private LocalDAO localDAO = new LocalDAO();
    int opcao;
    
    Scanner tec = new Scanner(System.in);
    
    boolean verifica_senha(int senha){
        return this.senha == senha;
    }
    
    void show_menu(){
        System.out.println("1 - Evento");
        System.out.println("2 - Participante");
        System.out.println("3 - Local");
        System.out.println("4 - Administrar Participante Evento");
        System.out.println("9 - Menu");
        System.out.println("0 - Logout");
    }
    
    void show_menu_adm(){
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Editar");
        System.out.println("3 - Deletar");
        System.out.println("4 - Listar");
        System.out.println("9 - Menu");
        System.out.println("0 - Voltar");
    }
    
    void gerenciar_participante() throws SQLException{
        int id;
        String nome,email;
        ArrayList<Participante> partLista;
        show_menu_adm();
        OUTER:
        while(true){
            opcao = tec.nextInt();
            tec.nextLine();
            switch(opcao){
                case 1:
                    System.out.print("Nome do participante: ");
                    nome = tec.nextLine();
                    
                    System.out.print("Email: ");
                    email = tec.nextLine();
                    
                    Participante participante = new Participante(nome,email);
                    if(participanteDAO.addParticipante(participante))System.out.println("Sucesso!");
                    else System.out.println("Erro ao inserir participante! ");
                    break;
                
                case 2:
                    System.out.print("Digite o id: ");
                    id = tec.nextInt();
                        
                    System.out.print("Novo nome: ");
                    String novonome = tec.nextLine();

                    System.out.print("Novo email: ");
                    email = tec.nextLine();
                    
                    if(participanteDAO.editarParticipante(id,novonome,email))System.out.println("Sucesso!");
                    else System.out.println("Erro ao atualizar participante!");
                    break;
                    
                case 3:
                    tec.nextLine();
                    System.out.print("Digite o id: ");
                    id = tec.nextInt();
                    if(participanteDAO.deletaParticipante(id))System.out.println("Sucesso!");
                    else System.out.println("Erro ao deletar participante");
                    
                    break;
                    
                case 4:
                    partLista = participanteDAO.listarParticipantes(); 
                    if(partLista.isEmpty()){
                        System.out.println("Nao existem participantes cadastrados");
                    }
                    else{
                        for(Participante p : partLista){
                            System.out.println(p.toString());
                            System.out.println("---------------------------------------------------");
                        }
                    }
                    break;
                    
                case 9:
                    show_menu_adm();
                    break;
                    
                case 0:
                    show_menu();
                    break OUTER;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        }        
    }
    
    Local constroiLocal(){
        String nome_local,cidade_local,bairro,rua;
        int numero_local,cep_local;
        tec.nextLine();
        System.out.print("Nome do local: ");
        nome_local = tec.nextLine();

        System.out.print("Cidade: ");
        cidade_local = tec.nextLine();

        System.out.print("Bairro: ");
        bairro = tec.nextLine();

        System.out.print("Rua: ");
        rua = tec.nextLine();

        System.out.print("Numero: ");
        numero_local = tec.nextInt();

        System.out.print("CEP: ");
        cep_local = tec.nextInt();

        Local local = new Local(nome_local,cidade_local,bairro,rua,numero_local,cep_local);
        return local;
    }
    
    void gerenciar_local() throws SQLException{
        int id;
        ArrayList<Local> locais;
        show_menu_adm();
        OUTER:
        while(true){
            opcao = tec.nextInt();
            switch(opcao){
                case 1:
                    Local local = constroiLocal();
                    if(localDAO.addLocal(local)){
                        System.out.println("Sucesso!");
                    }
                    else System.out.println("Erro ao inserir local! ");
                    break;
                    
                case 2:
                    tec.nextLine();
                    // System.out.print("Nome do local: ");
                    // String nome_local = tec.nextLine();
                    System.out.print("Digite o id: ");
                    id = tec.nextInt();
                    if(localDAO.editarLocal(id,constroiLocal())) System.out.println("Sucesso!");
                    else System.out.println("Erro ao atualizar o local!");
                    break;
                    
                case 3:
                    tec.nextLine();
                    System.out.print("Digite o id: ");
                    id = tec.nextInt();
                    if(localDAO.deletaLocal(id)) System.out.println("Sucesso!");
                    else System.out.println("Erro ao deletar local!");
                    break;
                    
                case 4:
                    locais = localDAO.listarLocais();
                    for(Local l : locais){
                        System.out.println(l.toString());
                        System.out.println("---------------------------------------------------");
		    }
                    break;
                    
                case 9:
                    show_menu_adm();
                    break;
                    
                case 0:
                    show_menu();
                    break OUTER;
                  
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        }        
    }
    Evento constroiEvento(){
        System.out.print("Nome: ");
        String nome = tec.nextLine();

        System.out.print("Descricao: ");
        String descricao = tec.nextLine();

        System.out.print("Id local: ");
        int local = tec.nextInt();

        System.out.print("Qtd patici: ");
        int qtd = tec.nextInt();
        tec.nextLine();

        System.out.print("Data(AAAA-MM-DD): ");
        String abs  = tec.nextLine();
        LocalDate k = LocalDate.parse(abs);

        System.out.print("Data(HH:MM): ");
        abs  = tec.nextLine();
        LocalTime t = LocalTime.parse(abs);

        Evento evt = new Evento(nome,descricao,k,t,local,qtd);
        return evt;
    }
    
    
    void gerenciar_evento() throws SQLException{
        int id;
        show_menu_adm();
        ArrayList<Evento> partEvento;
        OUTER:
        while(true){
            opcao = tec.nextInt();
            tec.nextLine();
            switch(opcao){
                case 1:
                    Evento evt = constroiEvento();
                    if(eventoDAO.addEvento(evt)){
                        System.out.println("Inserido com sucesso");
                    }else{
                        System.out.println("Erro ao inserir evento!");
                    }
                    break;
                case 2:
                    System.out.print("Digite o id: ");
                    id = tec.nextInt();
                    tec.nextLine();
                    evt = constroiEvento();
                    if(eventoDAO.editarEvento(id, evt))System.out.println("Sucesso!");
                    else System.out.println("Erro ao editar evento!");
                    break;
                case 3:
                    System.out.print("Digite o id: ");
                    id = tec.nextInt();
                    if(eventoDAO.deletaEvento(id))System.out.println("Sucesso!");
                    else System.out.println("Erro ao deletar evento");
                    break;
                case 4:
                    partEvento = eventoDAO.listarEventos();
                    if(partEvento.isEmpty()){
                        System.out.println("Nao existem eventos cadastrados");
                    }
                    else{
                        for(Evento e: partEvento){
                            System.out.println(e.toString2());
                            System.out.println("---------------------------------------------------");
                        }
                    }
                    break;
                case 9:
                    show_menu_adm();
                    break;
                case 0:
                    show_menu();
                    break OUTER;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        }        
    }
    
    PartEvento geraPart(){
        int evento_id,participante_id;
        System.out.print("Id do evento: ");
        evento_id = tec.nextInt();
        System.out.print("Id do participante: ");
        participante_id = tec.nextInt();
        PartEvento pe = new PartEvento(evento_id, participante_id);
        return pe;
    }
    
    void gerenciarPartEvento() throws SQLException{
        menu();
        OUTER:
        while(true){
            opcao = tec.nextInt();
            switch(opcao){
                case 1:
                    if(partDAO.addPartEvento(geraPart()))System.out.println("Sucesso!");
                    else System.out.println("Erro ao cadastrar participante ao evento");
                    break;
                case 2:
                    if(partDAO.deletaPartEvento(geraPart()))System.out.println("Sucesso!");
                    else System.out.println("Erro ao deletar participante do evento");
                    break;
                case 3:
                    partDAO.listarPartEvento();
                    break;
                case 9:
                    menu();
                    break;
                case 0:
                    show_menu();
                    break OUTER; 
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        }
    }
    void menu(){
        System.out.println("\n1 - Inserir participante no evento");
        System.out.println("2 - Deletar participante do evento");
        System.out.println("3 - Listar Programacoes");
        System.out.println("4 - Mostar menu");
        System.out.println("0 - Voltar");
    }
}