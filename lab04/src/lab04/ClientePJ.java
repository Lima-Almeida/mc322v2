package lab04;

import java.util.Date;

public class ClientePJ extends Cliente {

	final private String cnpj;
	private Date dataFundacao;
	private int qntFuncionarios;
	
	public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, 
			int qntFuncionarios) {
		super(nome, endereco);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qntFuncionarios = qntFuncionarios;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	
	public int getQntFuncionarios() {
		return qntFuncionarios;
	}
	
	public void setQntFuncionarios(int qntFuncionarios) {
		this.qntFuncionarios = qntFuncionarios;
	}
	
	public double calculaScore() {
		double valor;
		CalcSeguro calcseguro[] = CalcSeguro.values();
		valor = calcseguro[0].fator() * (1 + (qntFuncionarios)/100) * listaVeiculos.size();
		return valor;
	}
	
	public String toString() {
		return "Nome: "+getNome()+
		"\n Endereco: "+getEndereco()+
		"\n Valor do seguro: "+getValorSeguro()+
		"\n CNPJ: "+cnpj;
	}
}