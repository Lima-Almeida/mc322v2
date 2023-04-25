package lab03;

import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String endereco;
	private ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
	
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
	
	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public String toString() {
		return "Nome: "+nome+
		"\n Endereco: "+endereco;
	}

}