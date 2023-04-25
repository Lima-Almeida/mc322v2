package lab03;

import java.util.Random;

public class Sinistro {
	final private int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	public Sinistro(String data, String endereco, Seguradora seguradora,
	Cliente cliente, Veiculo veiculo) {
		this.id = idGenerator();
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
	public int getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public int idGenerator() {
		Random number = new Random();
		int threshold = 100000;
		int id = number.nextInt(threshold);
		return id;
	}
	
	public Seguradora getSeguradora() {
		return seguradora;
	}
	
	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String toString() {
		return "ID: "+id+
		"\n Data: "+data+
		"\n Endereco: "+endereco+
		"\n Seguradora: "+seguradora.getNome()+
		"\n Veiculo: "+veiculo.getMarca()+" "+veiculo.getModelo()+" "+veiculo.getPlaca()+
		"\n Cliente: "+cliente.getNome();
	}
}