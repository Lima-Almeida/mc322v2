package lab05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Validacao {
	
	public static boolean validaCPF(String cpf) {
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
	
	public static boolean validaCNPJ(String cnpj) {
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
	
	public static boolean validaNome(String nome) {
		boolean valid = true;
		for (int i = 0; i < nome.length(); i++) {
			if (!(Character.isLetter(nome.charAt(i))) && !(Character.isSpaceChar(nome.charAt(i)))) {
				valid = false;
			}
		}
		return valid;
	}
	
	//Usando expressões regulares para verificar o formato de data
	public static boolean validaData(String data) {
		Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
		boolean valid = true;
		if(pattern.matcher(data).matches()) {
			return valid;
		}
		else {
			return !valid;
		}
	}
	
	//Usando expressões regulares para verificar o formato de ano
	public static boolean validaAno(String ano) {
		Pattern pattern = Pattern.compile("^\\d{4}$");
		boolean valid = true;
		if(pattern.matcher(ano).matches()) {
			return valid;
		}
		else {
			return !valid;
		}
	}
	
	//Usando expressões regulares para verificar o formato de placa (Padrão e Mercosul)
	public static boolean validaPlaca(String placa) {
		Pattern patternPadrao = Pattern.compile("^[A-Za-z]{3}\\d{4}$");
		Pattern patternMercosul = Pattern.compile("^[A-Za-z]{3}\\d{1}[A-Za-z]{1}\\d{2}$");
		boolean valid = true;
		if((patternPadrao.matcher(placa).matches()) || (patternMercosul.matcher(placa).matches())) {
			return valid;
		}
		else {
			return !valid;
		}
	}
	
	//Usando expressões regulares para verificar o formato de email
	public static boolean validaEmail(String email) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
		boolean valid = true;
		if(pattern.matcher(email).matches()) {
			return valid;
		}
		else {
			return !valid;
		}
	}
	
	//Usando expressões regulares para verificar o formato de telefone
	public static boolean validaTelefone(String telefone) {
		Pattern pattern = Pattern.compile("^(\\(\\d{2}\\))\\d{4,5}[- .]\\d{4}$");
		boolean valid = true;
		if(pattern.matcher(telefone).matches()) {
			return valid;
		}
		else {
			return !valid;
		}
	}
	
	public static int idGenerator() {
		Random number = new Random();
		int threshold = 100000;
		int id = number.nextInt(threshold);
		return id;
	}
}