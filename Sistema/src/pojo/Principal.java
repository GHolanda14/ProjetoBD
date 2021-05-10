package pojo;

import java.sql.SQLException;
import java.util.Scanner;
public class Principal {
    
    public static void main(String[] args) throws SQLException{
        
        Scanner tec = new Scanner(System.in);           
        
        int tipo,comando;
        
        OUTER:
        while (true) {
            System.out.println("QUAL É A BOA DE HOJE?");
            System.out.println("1 - Administrador\n2 - Usuário\n0 - Fechar Sistema");
            tipo = tec.nextInt();
            switch (tipo) {
                case 1:
                    // System.out.println("Senha: ");
                    // int password = tec.nextInt();
                    Administrador adm = new Administrador();
                    adm.show_menu();
                    OUTER_1:
                    while (true) {
                        comando = tec.nextInt();
                        switch (comando) {
                            case 0:
                                break OUTER_1;
                            case 1:
                                adm.gerenciar_evento();
                                break;
                            case 2:
                                adm.gerenciar_participante();
                                break;
                            case 3:
                                adm.gerenciar_local();
                                break;
                            case 4:
                                adm.gerenciarPartEvento();
                                break;
                            case 9:
                                adm.show_menu();
                                break;
                            default:
                                System.out.println("Opcao invalida");
                                break;
                        }
                    }
                case 2:
                    Usuario user = new Usuario();
                    user.show_menu();
                    OUTER_1:
                        while (true) {
                            comando = tec.nextInt();
                            switch (comando) {
                                case 0:
                                    break OUTER_1;
                                case 1:
                                    user.visualizarTudo();
                                    break;
                                case 2:
                                    user.visualizarBoas();
                                    break;
                                case 3:
                                    tec.nextLine();
                                    System.out.print("Nome do local: ");
                                    String nome_local = tec.nextLine();
                                    if(user.pesquisaLocal(nome_local)){
                                        System.out.print("Digite o id: ");
                                        int id = tec.nextInt();
                                        user.listaLocal(id);
                                    }
                                    break;
                                case 4:
                                    tec.nextLine();
                                    System.out.print("Nome do evento: ");
                                    String nome_evento = tec.nextLine();
                                    if(user.pesquisaEvento(nome_evento)){
                                        System.out.print("Digite o id: ");
                                        int id = tec.nextInt();
                                        user.listaEvento(id);
                                    }
                                    break;
                                case 5:
                                    tec.nextLine();
                                    System.out.print("Nome do participante: ");
                                    String nome_participante = tec.nextLine();
                                    if(user.pesquisaParticipante(nome_participante)){
                                        System.out.print("Digite o id: ");
                                        int id = tec.nextInt();
                                        user.listaParticipanteEvento(id);
                                    }                 
                                    break;
                                case 6:
                                    tec.nextLine();
                                    System.out.print("Nome do local: ");
                                    String nome = tec.nextLine();
                                    if(user.pesquisaLocalOndeFica(nome)){
                                        System.out.print("Digite o id: ");
                                        int id = tec.nextInt();
                                        user.listaLocalOndeFica(id);
                                    }
                                    break;
                                case 9:
                                    user.show_menu();
                                    break;
                                default:
                                    System.out.println("Opcao invalida");
                                    break;
                            }
                        }
                    break;
                case 0:
                    System.out.println("Obrigado pela visita, volte sempre!");
                    tec.close();
                    break OUTER;
                default:
                    System.out.println("Opcao inválida");
                    break;
            }
        }
    }
}