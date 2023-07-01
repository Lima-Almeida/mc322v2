package lab05;

import java.util.ArrayList;
import java.util.Date;

public class Condutor {
	
	final private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private Date dataNasc;
	private ArrayList<Sinistro> listaSinistros = new ArrayList<>();
	
	public Condutor(String nome, String cpf, String telefone, String email, Date dataNasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.dataNasc = dataNasc;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
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
	
	public Date getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public void adicionarSinistro(Sinistro sinistro) {
		listaSinistros.add(sinistro);
	}

}