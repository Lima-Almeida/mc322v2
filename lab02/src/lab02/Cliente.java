package lab02;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
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
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setId(String endereco) {
		this.endereco = endereco;
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
		return "Nome: "+nome+
				"\n CPF:  "+cpf+
				"\n Data de Nascimento: "+dataNascimento+
				"\n Idade: "+idade+
				"\n Endereco: "+endereco;
	}
	
}
