package entidades;

import java.sql.SQLException;

public class Usuario {
	
	private int id;
	private String cpf;
	private String nome;
	private int idade;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String cpf, String nome, int idade) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String imprimir() {
		return "ID:" + this.id + " " + "CPF:" + this.cpf + " " + "Nome:" + this.nome + " " + "Idade:" + this.idade;
	}
}