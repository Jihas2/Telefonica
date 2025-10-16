import java.util.ArrayList;
import java.util.Scanner;

public class AgendaTelefonica {
    private static ArrayList<Contato> agenda = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirContatos();
            exibirMenu();
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            switch(opcao) {
                case 1:
                    adicionarContato();
                    break;
                case 2:
                    removerContato();
                    break;
                case 3:
                    buscarContato();
                    break;
                case 4:
                    System.out.println("\nEncerrando agenda telefônica...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }

            if(opcao != 4) {
                System.out.println("\nPressione ENTER para continuar...");
                sc.nextLine();
            }

        } while(opcao != 4);

        sc.close();
    }

    private static void exibirContatos() {
        System.out.println("\n========================================");
        System.out.println("         AGENDA TELEFÔNICA");
        System.out.println("========================================");

        if(agenda.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
        } else {
            System.out.println("\nContatos cadastrados (" + agenda.size() + "):");
            System.out.println("----------------------------------------");
            for(int i = 0; i < agenda.size(); i++) {
                System.out.println((i+1) + ". " + agenda.get(i));
            }
        }
        System.out.println("========================================\n");
    }

    private static void exibirMenu() {
        System.out.println("MENU:");
        System.out.println("1 - Adicionar contato");
        System.out.println("2 - Remover contato");
        System.out.println("3 - Buscar contato");
        System.out.println("4 - Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    private static void adicionarContato() {
        System.out.print("\nDigite o nome do contato: ");
        String nome = sc.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = sc.nextLine();

        Contato novoContato = new Contato(nome, telefone);
        agenda.add(novoContato);

        System.out.println("\n✓ Contato adicionado com sucesso!");
    }

    private static void removerContato() {
        if(agenda.isEmpty()) {
            System.out.println("\nNão há contatos para remover.");
            return;
        }

        System.out.print("\nDigite o número do contato a remover (1-" + agenda.size() + "): ");
        int indice = sc.nextInt();
        sc.nextLine();

        if(indice >= 1 && indice <= agenda.size()) {
            Contato removido = agenda.remove(indice - 1);
            System.out.println("\n✓ Contato '" + removido.getNome() + "' removido com sucesso!");
        } else {
            System.out.println("\nÍndice inválido!");
        }
    }

    private static void buscarContato() {
        if(agenda.isEmpty()) {
            System.out.println("\nNão há contatos para buscar.");
            return;
        }

        System.out.print("\nDigite o nome do contato a buscar: ");
        String nomeBusca = sc.nextLine().toLowerCase();

        boolean encontrado = false;
        System.out.println("\nResultados da busca:");
        System.out.println("----------------------------------------");

        for(Contato c : agenda) {
            if(c.getNome().toLowerCase().contains(nomeBusca)) {
                System.out.println(c);
                encontrado = true;
            }
        }

        if(!encontrado) {
            System.out.println("Nenhum contato encontrado.");
        }
    }
}