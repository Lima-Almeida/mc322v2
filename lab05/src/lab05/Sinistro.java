package lab05;

import java.util.Date;

public class Sinistro {
	
	final private int id;
	private Date data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
		this.id = Validacao.idGenerator();
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
	}
	
	public int getId() {
		return id;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Condutor getCondutor() {
		return condutor;
	}
	
	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}
	
	public Seguro getSeguro() {
		return seguro;
	}
	
	public void setVeiculo(Seguro seguro) {
		this.seguro = seguro;
	}
	
	public String toString() {
		return "ID: "+id+
		"\n Data: "+data+
		"\n Endereco: "+endereco+
		"\n Condutor: "+condutor.getNome()+
		"\n Seguro: "+seguro.getId()+"   Valor mensal: "+seguro.getValorMensal();
	}
}