package lab05;

import java.util.ArrayList;

public class Seguradora {
	
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	protected ArrayList<Seguro> listaSeguros = new ArrayList<>();
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
	
	public boolean gerarSeguro(Seguro seguro) {
		if(listaSeguros.contains(seguro)) {
			return false;
		}
		else {
			this.listaSeguros.add(seguro);
			return true;
		}
	}
	
	public boolean cancelarSeguro(Seguro seguro) {
		return this.listaSeguros.remove(seguro);
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
	
	public boolean removerCliente(Cliente cliente) {
		return this.listaClientes.remove(cliente);
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
	
	//Parametro Cliente ou Nome/CPF/CNPJ?? <<<<<<<<<<<<<<<<<<<<<<<<<<< Testar
	public ArrayList<Seguro> getSegurosPorCliente(String cliente) {
		ArrayList<Seguro> listaSegurosCliente = new ArrayList<>();
		//listaSegurosCliente.clear();
		if(Validacao.validaCPF(cliente)) {
			for(Seguro s: listaSeguros) {
				if(s instanceof SeguroPF) {
					SeguroPF spf = (SeguroPF) s;
					if(spf.getCliente().getCpf().equals(cliente)) {
						listaSegurosCliente.add(spf);
					}
				}
			}
		}
		else if(Validacao.validaCNPJ(cliente)) {
			for(Seguro s: listaSeguros) {
				if(s instanceof SeguroPJ) {
					SeguroPJ spj = (SeguroPJ) s;
					if(spj.getCliente().getCnpj().equals(cliente)) {
						listaSegurosCliente.add(spj);
					}
				}
			}
		}
		return listaSegurosCliente;
	}
	
	public ArrayList<Sinistro> getSinistrosPorCliente(String cliente) {
		ArrayList<Sinistro> listaSinistrosCliente = new ArrayList<>();
		listaSinistrosCliente.clear();
		if(Validacao.validaCPF(cliente)) {
			for(Seguro s: listaSeguros) {
				if(s instanceof SeguroPF) {
					SeguroPF spf = (SeguroPF) s;
					if(spf.getCliente().getCpf().equals(cliente)) {
						for (int i = 0; i < spf.listaSinistros.size(); i++) {
							listaSinistrosCliente.add(spf.listaSinistros.get(i));
						}
					}
				}
			}
		}
		else if(Validacao.validaCNPJ(cliente)) {
			for(Seguro s: listaSeguros) {
				if(s instanceof SeguroPJ) {
					SeguroPJ spj = (SeguroPJ) s;
					if(spj.getCliente().getCnpj().equals(cliente)) {
						for (int i = 0; i < spj.listaSinistros.size(); i++) {
							listaSinistrosCliente.add(spj.listaSinistros.get(i));
						}
					}
				}
			}
		}
		return listaSinistrosCliente;
	}
		
	public double calcularReceita() {
		double valor = 0;
		for(Seguro s: listaSeguros) {
			valor += s.getValorMensal();
		}
		return valor;
	}
	
	public String toString() {
		return "Nome: "+nome+
		"\n Telefone: "+telefone+
		"\n Email: "+email+
		"\n Endereco: "+endereco;
	}
}