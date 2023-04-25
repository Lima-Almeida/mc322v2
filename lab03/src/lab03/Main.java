package lab03;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	
	public static ClientePF cadastroPF() {
		Scanner input = new Scanner(System.in);
		System.out.println("Cadastro de novo cliente (Pessoa fisica)");
		System.out.println("Nome:");
		String name = input.nextLine();
		System.out.println("CPF:");
		String cpf = input.nextLine();
		System.out.println("Genero:");
		String gender = input.nextLine();
		System.out.println("Data da Licenca (dd/MM/yyyy):");
		String licenseDate = input.nextLine();
		System.out.println("Escolaridade:");
		String education = input.nextLine();
		System.out.println("Data de Nascimento (dd/MM/yyyy):");
		String birthDate = input.nextLine();
		System.out.println("Classe economica:");
		String economics = input.nextLine();
		System.out.println("Endereco:");
		String address = input.nextLine();
		Date birth = null;
		try {
			birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date license = null;
		try {
			license = new SimpleDateFormat("dd/MM/yyyy").parse(licenseDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientePF cf = new ClientePF(name, address, cpf, gender, license, education, birth, economics);
		//input.close();
		return cf;
	}
	
	public static ClientePJ cadastroPJ() {
		Scanner input = new Scanner(System.in);
		System.out.println("Cadastro de novo cliente (Pessoa juridica)");
		System.out.println("Nome:");
		String name = input.nextLine();
		System.out.println("CNPJ:");
		String cnpj = input.nextLine();
		System.out.println("Data de Fundacao (dd/MM/yyyy):");
		String birthDate = input.nextLine();
		System.out.println("Endereco:");
		String address = input.nextLine();	
		Date birth = null;
		try {
			birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientePJ cj = new ClientePJ(name, address, cnpj, birth);
		//input.close();
		return cj;
	}
	
	public static void validacaoCF(Seguradora seguradora, ClientePF cliente) {
		if (!cliente.validarCpf(cliente.getCpf())) {
			System.out.println("CPF invalido!");
		}
		else if (!seguradora.cadastrarCliente(cliente)) {
			System.out.println("Cliente já está cadastrado!");
		}
		else {
			System.out.println("Cliente cadastrado com sucesso!");
			System.out.println(cliente.toString());
		}
	}
	
	public static void validacaoCJ(Seguradora seguradora, ClientePJ cliente) {
		if (!cliente.validarCnpj(cliente.getCnpj())) {
			System.out.println("CNPJ invalido!");
		}
		else if (!seguradora.cadastrarCliente(cliente)) {
			System.out.println("Cliente ja esta cadastrado!");
		}
		else {
			System.out.println("Cliente cadastrado com sucesso!");
			System.out.println(cliente.toString());
		}
	}
	
	public static void main(final String[] args) {
		//Cadastro da seguradora
		System.out.println("Cadastro de seguradora");
		Scanner input = new Scanner(System.in);
		System.out.println("Nome:");
		String nameInsurer = input.nextLine();
		System.out.println("Telefone:");
		String phoneInsurer = input.nextLine();
		System.out.println("Email:");
		String emailInsurer = input.nextLine();
		System.out.println("Endereco:");
		String addressInsurer = input.nextLine();
		
		Seguradora s1 = new Seguradora(nameInsurer, phoneInsurer, emailInsurer, addressInsurer);
		System.out.println("Seguradora cadastrada com sucesso!");
		System.out.println(s1.toString());
		
		//Cadastro da primeira pessoa física
		ClientePF cf1 = cadastroPF();
		
		//Checando se o cpf é válido e se o cliente não foi cadastrado ainda
		validacaoCF(s1, cf1);
		
		//Cadastro de veículos para o cliente cadastrado anteriormente
		System.out.println("Quantidade de veículos:");
		String vehiclesQnt = input.nextLine();
		ArrayList<Veiculo> veiculos = new ArrayList<>();
				
		for (int j = 0; j < Integer.parseInt(vehiclesQnt); j++) {
			System.out.println("Placa:");
			String plate = input.nextLine();
			System.out.println("Marca:");
			String brand = input.nextLine();
			System.out.println("Modelo:");
			String model = input.nextLine();
			System.out.println("Ano:");
			String year = input.nextLine();
			Veiculo vhc = new Veiculo(plate, brand, model, Integer.parseInt(year));
			veiculos.add(vhc);
		}
		cf1.setListaVeiculos(veiculos);
		
		//Cadastro da segunda pessoa física
		ClientePF cf2 = cadastroPF();
		
		validacaoCF(s1, cf2);
		
		System.out.println("Quantidade de veículos:");
		vehiclesQnt = input.nextLine();
		veiculos = new ArrayList<>();
				
		for (int j = 0; j < Integer.parseInt(vehiclesQnt); j++) {
			System.out.println("Placa:");
			String plate = input.nextLine();
			System.out.println("Marca:");
			String brand = input.nextLine();
			System.out.println("Modelo:");
			String model = input.nextLine();
			System.out.println("Ano:");
			String year = input.nextLine();
			Veiculo vhc = new Veiculo(plate, brand, model, Integer.parseInt(year));
			veiculos.add(vhc);
		}
		cf2.setListaVeiculos(veiculos);
		
		//Cadastro da primeira pessoa jurídica
		ClientePJ cj1 = cadastroPJ();
		
		//Cadastro de veículos para o cliente cadastrado anteriormente
		validacaoCJ(s1, cj1);
		
		System.out.println("Quantidade de veículos:");
		vehiclesQnt = input.nextLine();
		veiculos = new ArrayList<>();
		for (int j = 0; j < Integer.parseInt(vehiclesQnt); j++) {
			System.out.println("Placa:");
			String plate = input.nextLine();
			System.out.println("Marca:");
			String brand = input.nextLine();
			System.out.println("Modelo:");
			String model = input.nextLine();
			System.out.println("Ano:");
			String year = input.nextLine();
			Veiculo vhc = new Veiculo(plate, brand, model, Integer.parseInt(year));
			veiculos.add(vhc);
			System.out.println(vhc.toString());
		}
		cj1.setListaVeiculos(veiculos);
		
		//Gerando sinistro
		System.out.println("Cadastro do sinistro");
		System.out.println("Data:");
		String dateSin = input.nextLine();
		System.out.println("Endereco:");
		String addressSin = input.nextLine();
		Sinistro sin1 = new Sinistro(dateSin, addressSin, s1, cf1, cf1.getListaVeiculos().get(0));
		s1.gerarSinistro(sin1);
		System.out.println("Sinistro gerado com sucesso!");
		System.out.println(sin1.toString());
		
		s1.listarClientes("PF");
		s1.listarSinistros();
		//Removendo segunda pessoa física cadastrada
		if (s1.removerCliente(cf2.getNome())) {
			System.out.println("Cliente removido com sucesso!");
		}
		else {
			System.out.println("Cliente não encontrado.");
		}
		s1.listarClientes("PF");
		s1.visualizarSinistro(cf1.getNome());
	}
}