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
			opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					cadastrarUsuario(service, scanner);
					break;
				case 2:
					System.out.println("Opção ainda não implementada.");
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
					System.out.println("Opção ainda não implementada.");
					break;
				case 7:
					System.out.println("Saindo...");
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
		int id = scanner.nextInt();
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
}