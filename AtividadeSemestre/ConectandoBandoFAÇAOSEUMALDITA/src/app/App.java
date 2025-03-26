package app;

import java.util.List;
import java.util.Scanner;
import entidades.Usuario;
import repository.UsuarioRepository;
import service.UsuarioService;

public class App {
	public static void main(String[] args) {
		UsuarioService service = new UsuarioService();
		UsuarioRepository repository = new UsuarioRepository();
		Scanner scanner = new Scanner(System.in);
		int opcao;
		do {
			exibirMenu();
			try {
				opcao = scanner.nextInt();		
			} catch (Exception e) {
				opcao = -1;
			}
			scanner.nextLine();

			switch (opcao) {
				case 1:
					cadastrarUsuario(service, scanner);
					break;
				case 2:
					atualizarUsuario(repository, scanner, service);
					break;
				case 3:
					removerUsuario(repository, scanner);
					break;
				case 4:
					consultarTodos(repository);
					break;
				case 5:
					consultarPorCpf(repository, scanner);
					break;
				case 6:
					consultarPorInicial(repository, scanner);
					break;
				case 7:
					System.out.println("Saiu...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 7);
		scanner.close();
	}

	private static void exibirMenu() {
		System.out.println("\n==== MENU ====");
		System.out.println("1 - Cadastrar Usuário");
		System.out.println("2 - Editar Usuário");
		System.out.println("3 - Remover Usuário");
		System.out.println("4 - Consultar todos os cadastros");
		System.out.println("5 - Consultar pelo CPF");
		System.out.println("6 - Consultar todos os cadastros pelas iniciais do nome");
		System.out.println("7 - Sair");
		System.out.print("Escolha uma opção: ");
	}

	private static void cadastrarUsuario(UsuarioService service, Scanner scanner) {
		System.out.println("\nCadastro de Usuário");
		System.out.print("Digite o ID: ");
		
		int id;
	    try {
	    	id = scanner.nextInt();	
		} catch (Exception e) {
			id = -1;
		}		
	    scanner.nextLine();

		System.out.print("Digite o CPF: ");
		String cpf = scanner.nextLine();

		System.out.print("Digite o Nome: ");
		String nome = scanner.nextLine();

		Usuario usuario = new Usuario(id, cpf, nome);

		try {
			service.cadastrar(usuario);
			System.out.println("Usuário cadastrado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
		}
	}

	private static void removerUsuario(UsuarioRepository repository, Scanner scanner) {
		System.out.println("\nRemover Usuário");
		List<Usuario> listaUsuarios = repository.retornaTodos();

		if (listaUsuarios.isEmpty()) {
			System.out.println("Nenhum usuário cadastrado.");
			return;
		}

		for (Usuario usuario : listaUsuarios) {
			System.out.println(usuario.imprimir());
		}

		System.out.print("Digite o ID do usuário a ser removido: ");
		int idEscolhido = scanner.nextInt();
		scanner.nextLine();

		Usuario usuario = repository.consultarPorId(idEscolhido);
		if (usuario == null) {
			System.out.println("Usuário não encontrado.");
			return;
		}

		System.out.print("Deseja realmente remover o usuário? (S/N): ");
		String escolha = scanner.nextLine();

		if (escolha.equalsIgnoreCase("S")) {
			repository.excluir(usuario);
			System.out.println("Usuário removido com sucesso!");
		} else {
			System.out.println("Remoção cancelada.");
		}
	}

	private static void consultarTodos(UsuarioRepository repository) {
		System.out.println("\nConsulta de Todos os Usuários");
		List<Usuario> listaUsuarios = repository.retornaTodos();

		if (listaUsuarios.isEmpty()) {
			System.out.println("Nenhum usuário cadastrado.");
		} else {
			for (Usuario usuario : listaUsuarios) {
				System.out.println(usuario.imprimir());
			}
		}
	}

	private static void consultarPorCpf(UsuarioRepository repository, Scanner scanner) {
		System.out.println("\nConsulta por CPF");
		System.out.print("Digite o CPF: ");
		String cpf = scanner.nextLine();

		Usuario usuario = repository.consultarPorCpf(cpf);
		if (usuario != null) {
			System.out.println(usuario.imprimir());
		} else {
			System.out.println("Usuário não encontrado.");
		}
	}
	@SuppressWarnings("unused")
	private static void atualizarUsuario(UsuarioRepository repository, Scanner scanner, UsuarioService service) {
	    System.out.println("\nAtualizar Usuario");
	    System.out.print("Digite o ID do Usuario que deseja atualizar: ");
	    int id;
	    try {
	    	id = scanner.nextInt();	
		} catch (Exception e) {
			id = -1;
		}
	    scanner.nextLine();

	    if (repository.consultarPorId(id) != null) {
	        System.out.print("Digite o CPF novo: ");
	        String cpf = scanner.nextLine().trim();

	        System.out.print("Digite o Nome novo: ");
	        String nome = scanner.nextLine().trim();

	        Usuario usuario = new Usuario(id, cpf, nome);

	        try {
	            service.atualizar(usuario);
	            System.out.println("Usuário atualizado com sucesso!");
	        } catch (Exception e) {
	            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
	        }
	    } else {
	        System.out.println("Esse ID não existe no sistema!");
	    }
	}
	private static void consultarPorInicial(UsuarioRepository repository, Scanner scanner) {
	    System.out.println("\nConsultar pelas Iniciais");
	    System.out.println("Digite as iniciais");
	    String iniciais = scanner.nextLine().trim();

	    if (iniciais.isEmpty()) {
	        System.out.println("Nenhuma inicial foi digitada!");
	        return;
	    }

	    List<Usuario> usuarios = repository.consultarPorIniciais(iniciais);

	    if (usuarios.isEmpty()) {
	        System.out.println("Não existe usuário com essas iniciais!");
	    } else {
	        for (Usuario usuario : usuarios) {
	            System.out.println(usuario.imprimir());
	        }
	    }
	}
}