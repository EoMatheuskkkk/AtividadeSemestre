package app;

import java.util.List;
import java.util.Scanner;

import entidades.Usuario;
import repository.UsuarioRepository;
import service.UsuarioService;

public class App {
   public static void main (String[]args ) {
	   UsuarioService service = new UsuarioService();
	   UsuarioRepository repository = new UsuarioRepository();
	   Usuario usuario = new Usuario();
	   @SuppressWarnings("resource")
	   Scanner scanner = new Scanner(System.in);
	   int cont = 0;
	do {
		   System.out.println("Menu");
		   System.out.println("1 - Cadastrar Usuario");
		   System.out.println("2 - Editar Usuario");
		   System.out.println("3 - Remover Usuario");
		   System.out.println("4 - Consultar todos os cadastros");
		   System.out.println("5 - Consultar pelo Cpf");
		   System.out.println("6 - Consultar todos os cadastros pelas iniciais do nome");
		   System.out.println("7 - Sair");
		   cont = scanner.nextInt();
		   
		   switch (cont) {
		case 1:
			System.out.println("Selecionou a opção Cadastrar Usuario");
			System.out.println("Digite o Id: ");
			   usuario.setId(scanner.nextInt());
				System.out.println("Digite o Cpf: ");
			   usuario.setCpf(scanner.next());
				System.out.println("Digite o Nome: ");
			   usuario.setNome(scanner.next());
			   try {
				   service.cadastrar(usuario);
			   } catch (Exception e) {
				   System.out.println("Erro: " + e.getMessage());
			   }
			   System.out.println("Usuario cadastrado com sucesso");
			break;
		case 2:
			System.out.println("");
			break;
		case 3:
			System.out.println("Selecionou a opção Remover Usuario");
			List<Usuario> listaUsuario1 = repository.retornaTodos();
			if (listaUsuario1.isEmpty()) {
			    System.out.println("Nenhum usuário cadastrado na base de dados");
			} else {
			    for (Usuario claro : listaUsuario1) {
			        System.out.println(claro.imprimir());
			    }
			}
			System.out.println("Digite o Id a ser removido: ");
			int idEscolhido = scanner.nextInt();
			usuario = repository.consultarPorId(idEscolhido);

			if (usuario != null) {
			    System.out.println("Deseja realmente remover o usuário? Digite S para Sim e N para Não ");
			    String escolha = scanner.next();
			    if (escolha.equalsIgnoreCase("S")) {
			        repository.excluir(usuario);
			        System.out.println("Usuário removido com sucesso!");
			    } else if (escolha.equalsIgnoreCase("N")) {
			        System.out.println("Remoção Cancelada!");
			    } else {
			        System.out.println("Opção Inválida");
			    }
			} else {
			    System.out.println("Usuário não encontrado para o ID: " + idEscolhido);
			}
			break;
		case 4:
			System.out.println("Selecionou a opção Consultar todos os cadastros");
			   List<Usuario> listaUsuario = repository.retornaTodos();
			   if(listaUsuario.isEmpty()) {
				   System.out.println("Nenhum usuário cadastrado na base de dados");
			   } else {
				   for (Usuario claro : listaUsuario) {
					   System.out.println(claro.imprimir());
				   }   
			   }
			break;
		case 5:
			System.out.println("Selecionou a opção Consultar pelo Cpf");
			System.out.println("Digite o Cpf para a consulta: ");
			String CPF = scanner.next();
			Usuario usuarioConsultado = repository.consultarPorCpf(CPF);
			if(usuarioConsultado != null) {
				System.out.println(usuarioConsultado.imprimir());
			} else {
				System.out.println("Usuário não encontrado para o CPF: " + CPF);
			}
			break;
		case 6:
			System.out.println("implementar");
			break;
		case 7:
			System.out.println("Saiu!");
			break;
		default:
			System.out.println("Opção Inválida");
			break;
		}
		   
	   } while(cont != 7);
	}
}
