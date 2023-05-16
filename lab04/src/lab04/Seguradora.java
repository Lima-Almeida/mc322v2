package lab04;

import java.util.ArrayList;

public class Seguradora {
	
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	protected ArrayList<Sinistro> listaSinistros = new ArrayList<>();
	protected ArrayList<Cliente> listaClientes = new ArrayList<>();
		
	public Seguradora(String nome, String telefone, String email, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}
		
	public String getNome() {
		return nome;
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public String getTelefone() {
		return telefone;
	}
		
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		if (listaClientes.contains(cliente)) {
			return false;
		}
		else {
			listaClientes.add(cliente);
			return true;
		}
	}
	
	public boolean removerCliente(String cliente) {
		//Cliente deve ser removido utilizando o nome
		boolean removido = false;
		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i).getNome().equals(cliente)) {
				listaClientes.remove(i);
				removido = true;
			}
		}
		if (removido) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void listarClientes(String tipoCliente) {
		int contador = 1;
		if (tipoCliente == "PF") {
			System.out.println("Clientes do tipo Pessoa Fisica:");
			for (int i = 0; i < listaClientes.size(); i++) {
				if(listaClientes.get(i) instanceof ClientePF) {
					System.out.println((contador) + " " + listaClientes.get(i));
					contador += 1;
				}
			}
		}
		else if (tipoCliente == "PJ") {
			System.out.println("Clientes do tipo Pessoa Juridica:");
			for (int i = 0; i < listaClientes.size(); i++) {
				if(listaClientes.get(i) instanceof ClientePJ) {
					System.out.println((contador) + " " + listaClientes.get(i));
					contador += 1;
				}
			}
		}
		System.out.println();
	}
	
	public void listarSinistros() {
		for (Sinistro sinistro: listaSinistros) {
			System.out.println(sinistro.toString());
		}
	}
	
	public void visualizarSinistro(String cliente) {
		for(Sinistro sinistro: listaSinistros) {
			if (sinistro.getCliente().getNome() == cliente) {
				System.out.println(sinistro.toString());
			}
		}
	}
	
	public void gerarSinistro(Sinistro sinistro) {
		listaSinistros.add(sinistro);
	}
	
	private int contaSinistros (Cliente cliente) {
		int contador = 0;
		for (Sinistro sinistro: listaSinistros) {
			if (sinistro.getCliente() == cliente) {
				contador += 1;
			}
		}
		return contador;
	}
	
	public double calcularPrecoSeguroCliente(String cliente) {
		double valor = 0;
		for (Cliente c: listaClientes) {
			if (c.getNome() == cliente) {
				valor = c.calculaScore() * (1 + this.contaSinistros(c));
			}
		}
		return valor;
	}
	
	public double calcularReceita() {
		double valor = 0;
		for (Cliente c: listaClientes) {
			valor += calcularPrecoSeguroCliente(c.getNome());
		}
		return valor/2;
	}
	
	public String toString() {
		return "Nome: "+nome+
		"\n Telefone: "+telefone+
		"\n Email: "+email+
		"\n Endereco: "+endereco;
	}
}