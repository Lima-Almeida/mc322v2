package lab03;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente {

	final private String cnpj;
	private Date dataFundacao;
	
	public ClientePJ(String nome, String endereco, String cnpj, 
	Date dataFundacao) {
		super(nome, endereco);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
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
	
	public boolean validarCnpj(String cnpj) {
		cnpj = cnpj.replaceAll("-", "");
		cnpj = cnpj.replaceAll("\\.", "");
		cnpj = cnpj.replaceAll("/", "");
		//Checando se o número do CNPJ possui 11 dígitos
		if (cnpj.length() < 14) {
			return false;
		}
		//Checando se todos os algarismos do número de CNPJ são iguais
		boolean valid = false;
		char previousChar = 0;
		for (int i = 0; i < 14; i++) {
			if ((cnpj.charAt(i) != previousChar) && (i > 0)) {
				valid = true;
				break;
			}
			else {
				previousChar = cnpj.charAt(i);
			}
		}
		if (valid == false) {
			return false;
		}
		//Criando lista com os algarismos do CNPJ
		List<Integer> cnpjNumbers = new ArrayList<Integer>();
		final int digitos[] = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		final int digitos2[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		for (int i = 0; i < 14; i++) {
			char algarismo = cnpj.charAt(i);
			cnpjNumbers.add(Character.getNumericValue(algarismo));
		}
		//Checando os dígitos verificadores
		int sum = 0;
		for (int i = 0; i < 12; i++) {
		sum += cnpjNumbers.get(i)*digitos[i];
		}
		if ((sum % 11) < 2) {
			sum = 0;
		}
		else {
			sum = 11 - sum % 11;
		}
		
		if (sum != cnpjNumbers.get(12)) {
			return false;
		}
		else {
			sum = 0;
			for (int i = 0; i < 13; i++) {
				sum += cnpjNumbers.get(i)*digitos2[i];
			}
			if ((sum % 11) < 2) {
				sum = 0;
			}
			else {
				sum = 11 - sum % 11;
			}
			if (sum != cnpjNumbers.get(13)) {
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
		"\n CNPJ: "+cnpj;
	}
}