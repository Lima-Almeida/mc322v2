package lab05;

import java.util.ArrayList;

abstract public class Cliente {
	private String nome;
	private String endereco;
	
	public Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setId(String endereco) {
		this.endereco = endereco;
	}
	
	abstract public ArrayList<Veiculo> getListaVeiculos();
	
	abstract public int calcularIdade();
	
	public String toString() {
		return "Nome: "+nome+
		"\n Endereco: "+endereco;
	}
}