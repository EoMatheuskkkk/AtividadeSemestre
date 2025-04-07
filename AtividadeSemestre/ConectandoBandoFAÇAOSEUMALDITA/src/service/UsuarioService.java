package service;

import entidades.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
	public void cadastrar(Usuario usuario) throws Exception {
		UsuarioRepository repository = new UsuarioRepository();

		if (usuario == null) {
			throw new Exception("Usuario invalido");
		} else if (usuario.getId() <= 0) {
			throw new Exception("Id do usuario invalido");
		} else if (usuario.getNome() == null) {
			throw new Exception("Nome do usuario invalido: nulo");
		} else if (usuario.getNome().trim().equals("")) {
			throw new Exception("Nome do usuario invalido: vazio");
		} else if (usuario.getNome().length() > 100) {
			throw new Exception("Nome do usuario invalido: tamanho excedido");
		} else if (usuario.getIdade() < 0) {
			throw new Exception("Idade do usuario invalido: não pode ser negativa");
		} else if (usuario.getIdade() > 120) {
			throw new Exception("Idade do usuario invalido: tamanho excedido");
		} else if (usuario.getCpf() == null) {
			throw new Exception("Cpf do usuario invalido: nulo");
		} else if (usuario.getCpf().trim().equals("")) {
			throw new Exception("Cpf do usuario invalido: vazio");
		} else if (!usuario.getCpf().matches("\\d{11}")) {
			throw new Exception("Cpf do usuario invalido: deve conter apenas 11 dígitos numéricos");
		}

		if (repository.consultarPorCpf(usuario.getCpf()) != null) {
			throw new Exception("Cpf do usuario já existe");
		}

		repository.inserir(usuario);
	}
	public void atualizar(Usuario usuario) throws Exception {
		UsuarioRepository repository = new UsuarioRepository();

		if (usuario == null) {
			throw new Exception("Usuario invalido");
		} else if (usuario.getId() <= 0) {
			throw new Exception("Id do usuario invalido");
		} else if (usuario.getNome() == null) {
			throw new Exception("Nome do usuario invalido: nulo");
		} else if (usuario.getNome().trim().equals("")) {
			throw new Exception("Nome do usuario invalido: vazio");
		} else if (usuario.getNome().length() > 100) {
			throw new Exception("Nome do usuario invalido: tamanho excedido");
		} else if (usuario.getIdade() < 0) {
			throw new Exception("Idade do usuario invalido: não pode ser negativa");
		} else if (usuario.getIdade() > 120) {
			throw new Exception("Idade do usuario invalido: tamanho excedido");
		} else if (usuario.getCpf() == null) {
			throw new Exception("Cpf do usuario invalido: nulo");
		} else if (usuario.getCpf().trim().equals("")) {
			throw new Exception("Cpf do usuario invalido: vazio");
		} else if (!usuario.getCpf().matches("\\d{11}")) {
			throw new Exception("Cpf do usuario invalido: deve conter apenas 11 dígitos numéricos");
		}
		Usuario existente = repository.consultarPorCpf(usuario.getCpf());
		if (existente != null && existente.getId() != usuario.getId()) {
			throw new Exception("Cpf do usuario já existe");
		}
		repository.atualizar(usuario);
	}
}
