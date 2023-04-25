package lab03;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente {

	final private String cpf;
	private String genero;
	private Date dataLicenca;
	private String educacao;
	private Date dataNascimento;
	private String classeEconomica;
	
	public ClientePF(String nome, String endereco, String cpf, String genero, 
	Date dataLicenca, String educacao, Date dataNascimento,
	String classeEconomica) {
	super(nome, endereco);
	this.cpf = cpf;
	this.genero = genero;
	this.dataLicenca = dataLicenca;
	this.educacao = educacao;
	this.dataNascimento = dataNascimento;
	this.classeEconomica = classeEconomica;
	}
	
	public String getCpf() {
	return cpf;
	}
	
	public String getGenero() {
	return genero;
	}
	
	public void setGenero(String genero) {
	this.genero = genero;
	}
	
	public Date getDataLicenca() {
	return dataLicenca;
	}
	
	public void setDataLicenca(Date dataLicenca) {
	this.dataLicenca = dataLicenca;
	}
	
	public String getEducacao() {
	return educacao;
	}
	
	public void setEducacao(String educacao) {
	this.educacao = educacao;
	}
	
	public Date getDataNascimento() {
	return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
	this.dataNascimento = dataNascimento;
	}
	
	public String getClasseEconomica() {
	return classeEconomica;
	}
	
	public void setClasseEconomica(String classeEconomica) {
	this.classeEconomica = classeEconomica;
	}
	
	public boolean validarCpf(String cpf) {
		cpf = cpf.replaceAll("\\.", "");
		cpf = cpf.replaceAll("-", "");
		//Checando se o número de CPF possui 11 dígitos
		if (cpf.length() < 11) {
			return false;
		}
		//Checando se todos os algarismos do número de CPF são iguais
		boolean valid = false;
		char previousChar = 0;
		for (int i = 0; i < 11; i++) {
			if ((cpf.charAt(i) != previousChar) && (i > 0)) {
				valid = true;
				break;
			}
			else {
				previousChar = cpf.charAt(i);
			}
		}
		if (valid == false) {
			return false;
		}
		//Criando lista com os algarismos do cpf
		List<Integer> cpfNumbers = new ArrayList<Integer>();
		for (int i = 0; i < 11; i++) {
			char algarismo = cpf.charAt(i);
			cpfNumbers.add(Character.getNumericValue(algarismo));
		}
		//Checando os dígitos verificadores
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += cpfNumbers.get(8 - i)*(i + 2);
		}
		if ((sum % 11) < 2) {
			sum = 0;
		}
		else {
			sum = 11 - sum % 11;
		}
		if (sum != cpfNumbers.get(9)) {
			return false;
		}
		else {
			sum = 0;
			for (int i = 0; i < 10; i++) {
				sum += cpfNumbers.get(9 - i)*(i + 2);
			}
			if ((sum % 11) < 2) {
				sum = 0;
			}
			else {
				sum = 11 - sum % 11;
			}
			if (sum != cpfNumbers.get(10)) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	public String toString() {
		return "Nome: "+getNome()+
		"\n Endereco: "+getEndereco()+
		"\n Genero: "+genero+
		"\n CPF: "+cpf+
		"\n Data da licenca: "+dataLicenca+
		"\n Educacao: "+educacao+
		"\n Data de Nascimento: "+dataNascimento+
		"\n Classe Economica: "+classeEconomica;
	}
}