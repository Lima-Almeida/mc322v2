package lab05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import lab05.Cliente;
import lab05.ClientePF;
import lab05.ClientePJ;
import lab05.MenuOperacoes;
import lab05.Seguradora;
import lab05.Sinistro;
import lab05.SubMenuOperacoes;
import lab05.Validacao;
import lab05.Veiculo;

public class AppMain {

	private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();
	
	public static Seguradora buscarSeguradora(String nome) {
		Seguradora s1 = null;
		for (Seguradora s: listaSeguradoras) {
			if (s.getNome().equals(nome)) {
				s1 = s;
			}
		}
		if (s1 == null) {
			System.out.println("Seguradora não encontrada! Tente novamente.");
		}
		return s1;
	}
	
	//Buscar com o cpf
	public static Cliente buscarCliente(String codigo) {
		Cliente c = null;
		if(Validacao.validaCPF(codigo)) {
			for(Seguradora s: listaSeguradoras) {
				for(Cliente c1: s.listaClientes) {
					if(c1 instanceof ClientePF) {
						ClientePF spf = (ClientePF) c1;
						if(spf.getCpf().equals(codigo)) {
							c = spf;
							return c;
						}
					}
				}
			}
		}
		else if(Validacao.validaCNPJ(codigo)) {
			for(Seguradora s: listaSeguradoras) {
				for(Cliente c1: s.listaClientes) {
					if(c1 instanceof ClientePJ) {
						ClientePJ spj = (ClientePJ) c1;
						if(spj.getCnpj().equals(codigo)) {
							c = spj;
							return c;
						}
					}
				}
			}
		}
		System.out.println(c);
		return c;
	}
	
	public static ClientePF cadastrarClientePF() {
		Scanner input = new Scanner(System.in);
		System.out.println("Cadastro de novo cliente (Pessoa fisica)");
		System.out.println("Nome:");
		String name = input.nextLine();
		if (!Validacao.validaNome(name)) {
			do {
				System.out.println("Nome inválido! Tente novamente:");
				name = input.nextLine();
			} while(!Validacao.validaNome(name));
		}
		System.out.println("CPF (XXX.XXX.XXX-XX):");
		String cpf = input.nextLine();
		if (!Validacao.validaCPF(cpf)) {
			do {
				System.out.println("Numero de CPF inválido! Digite novamente:");
				cpf = input.nextLine();
			} while (!Validacao.validaCPF(cpf));
		}
		System.out.println("Genero:");
		String gender = input.nextLine();
		System.out.println("Data da Licenca (dd/MM/yyyy):");
		String licenseDate = input.nextLine();
		if (!Validacao.validaData(licenseDate)) {
			do {
				System.out.println("Formato de data inválido! Digite novamente:");
				licenseDate = input.nextLine();
			} while (!Validacao.validaData(licenseDate)) ;
		}
		System.out.println("Escolaridade:");
		String education = input.nextLine();
		System.out.println("Data de Nascimento (dd/MM/yyyy):");
		String birthDate = input.nextLine();
		if (!Validacao.validaData(birthDate)) {
			do {
				System.out.println("Formato de data inválido! Digite novamente:");
				birthDate = input.nextLine();
			} while (!Validacao.validaData(birthDate)) ;
		}
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
		return cf;
	}
	
	public static ClientePJ cadastrarClientePJ() {
		Scanner input = new Scanner(System.in);
		System.out.println("Cadastro de novo cliente (Pessoa juridica)");
		System.out.println("Nome:");
		String name = input.nextLine();
		System.out.println("CNPJ (XX.XXX.XXX/XXXX-XX):");
		String cnpj = input.nextLine();
		if (!Validacao.validaCNPJ(cnpj)) {
		do {
			System.out.println("Numero de CNPJ inválido! Digite novamente:");
			cnpj = input.nextLine();
		} while(!Validacao.validaCNPJ(cnpj));
		}
		System.out.println("Data de Fundacao (dd/MM/yyyy):");
		String birthDate = input.nextLine();
		if (!Validacao.validaData(birthDate)) {
			do {
				System.out.println("Formato de data inválido! Digite novamente:");
				birthDate = input.nextLine();
			} while (!Validacao.validaData(birthDate)) ;
		}
		System.out.println("Endereco:");
		String address = input.nextLine();
		System.out.println("Quantidade de funcionarios:");
		String employeeQnt = input.nextLine();
		Date birth = null;
		try {
			birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientePJ cj = new ClientePJ(name, address, cnpj, birth);
		return cj;
	}
	
	public static void cadastrarCliente() {
		Scanner scanner = new Scanner(System.in);
		String s1;
		Seguradora seguradora;
		System.out.println("Deseja cadastrar em qual seguradora?:");
		s1 = scanner.nextLine();
		seguradora = buscarSeguradora(s1);
		if(seguradora == null) {
			do {
				s1 = scanner.nextLine();
				seguradora = buscarSeguradora(s1);
			} while (seguradora == null);
		}
		System.out.println("Pessoa Física ou Jurídica? (PF/PJ)");
		String tipo = scanner.nextLine();
		if (!(tipo.equals("PJ")) && (!(tipo.equals("PF")))) {
			do {
				System.out.println("Reposta inválida, por favor responda com PF ou PJ.");
				tipo = scanner.nextLine();
			} while(!(tipo.equals("PJ")) && (!(tipo.equals("PF"))));
		}

//		if (tipo.equals("PF")) {
//			ClientePF cf = cadastrarClientePF();
//			seguradora.cadastrarCliente(cf);
//			cf.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cf.getNome()));
//			System.out.println("Cliente cadastrado com sucesso!");
//		}
//		else if (tipo.equals("PJ")) {
//			ClientePJ cj = cadastrarClientePJ();
//			seguradora.cadastrarCliente(cj);
//			cj.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cj.getNome()));
//			System.out.println("Cliente cadastrado com sucesso!");
//		}
		atualizarValorMensal();
		System.out.println("Cliente cadastrado com sucesso!");
	}
	
	public static void cadastrarSeguroPF() throws ParseException {
		Scanner scanner = new Scanner(System.in);
		String s1;
		Seguradora seguradora;
		System.out.println("Deseja cadastrar em qual seguradora?:");
		s1 = scanner.nextLine();
		seguradora = buscarSeguradora(s1);
		if(seguradora == null) {
			do {
				s1 = scanner.nextLine();
				seguradora = buscarSeguradora(s1);
			} while (seguradora == null);
		}
		System.out.println("Cliente:");
		String client = scanner.nextLine();
		Cliente c = buscarCliente(client);
		ClientePF cf = (ClientePF) c;
		if(c == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		System.out.println("Data do inicio:");
		String startDate = scanner.nextLine();
		Date start = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
		System.out.println("Data do fim:");
		String endDate = scanner.nextLine();
		Date end = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
		System.out.println("Placa do veiculo:");
		String placa = scanner.nextLine();
		Veiculo veiculo = buscarVeiculo(placa);
		SeguroPF seguro1 = new SeguroPF(start, end, seguradora, veiculo, cf);
		seguradora.gerarSeguro(seguro1);
		System.out.println(seguro1.toString());
	}
	
	public static void atualizarValorMensal() {
		for(int i = 0; i < listaSeguradoras.size(); i++) {
			for(int j = 0; j < listaSeguradoras.get(i).listaSeguros.size(); j++) {
				listaSeguradoras.get(i).listaSeguros.get(j).calcularValor();
			}
		}
	}
	
	public static void cadastrarSeguroPJ() throws ParseException {
		Scanner scanner = new Scanner(System.in);
		String s1;
		Seguradora seguradora;
		System.out.println("Deseja cadastrar em qual seguradora?:");
		s1 = scanner.nextLine();
		seguradora = buscarSeguradora(s1);
		if(seguradora == null) {
			do {
				s1 = scanner.nextLine();
				seguradora = buscarSeguradora(s1);
			} while (seguradora == null);
		}
		System.out.println("Cliente:");
		String client = scanner.nextLine();
		Cliente c = buscarCliente(client);
		ClientePJ cj = (ClientePJ) c;
		if(c == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		System.out.println("Data do inicio:");
		String startDate = scanner.nextLine();
		Date start = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
		System.out.println("Data do fim:");
		String endDate = scanner.nextLine();
		Date end = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
		System.out.println("Codigo da frota:");
		String frotanome = scanner.nextLine();
		Frota frota = buscarFrota(frotanome, cj);
		SeguroPJ seguro1 = new SeguroPJ(start, end, seguradora, frota, cj);
		seguradora.gerarSeguro(seguro1);
		System.out.println(seguro1.toString());
		atualizarValorMensal();
	}
	
	public static Veiculo buscarVeiculo(String placa) {
		Veiculo veiculo = null;
		for(Seguradora s: listaSeguradoras) {
			for(Cliente c: s.listaClientes) {
				if(c instanceof ClientePF) {
					ClientePF cf = (ClientePF) c;
					for(Veiculo v: cf.listaVeiculos) {
						if(v.getPlaca().equals(placa)) {
							veiculo = v;
						}
					}
				}
				else if(c instanceof ClientePJ) {
					ClientePJ cj = (ClientePJ) c;
					for(Frota f: cj.listaFrotas) {
						for(Veiculo v: f.listaVeiculos) {
							if(v.getPlaca().equals(placa)) {
								veiculo = v;
							}
						}
					}
				}
			}
		}
		return veiculo;
	}
	
	public static Frota buscarFrota(String codigo, ClientePJ cj) {
		Frota frota = null;
		for(Frota f: cj.listaFrotas) {
			if(f.getCode().equals(codigo)) {
				frota = f;
			}
		}
		return frota;
	}
	
	public static void cadastrarVeiculo() {
		Scanner input = new Scanner(System.in);
		System.out.println("Cliente:");
		String client = input.nextLine();
		Cliente c = buscarCliente(client);
		if(c == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		System.out.println("Quantidade de veículos:");
		String vehiclesQnt = input.nextLine();
		
		ArrayList<Veiculo> veiculos = new ArrayList<>();
				
		for (int j = 0; j < Integer.parseInt(vehiclesQnt); j++) {
			System.out.println("Placa:");
			String plate = input.nextLine();
			if (!Validacao.validaPlaca(plate)) {
				do {
					System.out.println("Formato de placa inválido! Tente novamente:");
					plate = input.nextLine();
				} while(!Validacao.validaPlaca(plate));
			}
			System.out.println("Marca:");
			String brand = input.nextLine();
			System.out.println("Modelo:");
			String model = input.nextLine();
			System.out.println("Ano:");
			String year = input.nextLine();
			if (!Validacao.validaAno(year)) {
				do {
					System.out.println("Formato de ano inválido! Tente novamente:");
					year = input.nextLine();
				} while (!Validacao.validaAno(year));
			}
			Veiculo vhc = new Veiculo(plate, brand, model, Integer.parseInt(year));
			veiculos.add(vhc);
		}
		if (c instanceof ClientePF) {
			ClientePF cf = (ClientePF) c;
			cf.setListaVeiculos(veiculos);
		}
		else if (c instanceof ClientePJ) {
			ClientePJ cj = (ClientePJ) c;
			System.out.println("Frota:");
			String fleet = input.nextLine();
			Frota f = buscarFrota(fleet, cj);
			for(Veiculo v: veiculos) {
				f.addVeiculo(v);
			}
			cj.atualizarFrota(f);
		}
		if(Integer.parseInt(vehiclesQnt) > 1) {
			System.out.println("Veiculos cadastrados com sucesso!");
		}
		else {
			System.out.println("Veiculo cadastrado com sucesso!");
		}
		Seguradora seguradora = null;
		for (Seguradora s: listaSeguradoras) {
			for (Cliente c1: s.listaClientes) {
				if (c1.getNome().equals(client)) {
					seguradora = s;
				}
			}
		}
		//Atualizar VALOR SEGURO
		atualizarValorMensal();

	}
	
	public static void cadastrarSeguradora() {
		System.out.println("Cadastro de seguradora");
		Scanner input = new Scanner(System.in);
		System.out.println("Nome:");
		String nameInsurer = input.nextLine();
		System.out.println("Telefone:");
		String phoneInsurer = input.nextLine();
		if (!Validacao.validaTelefone(phoneInsurer)) {
			do {
				System.out.println("Formato de telefone inválido! Tente o formato (xx)xxxxx-xxxx ou (xx)xxxx-xxxx:");
				phoneInsurer = input.nextLine();
			} while (!Validacao.validaTelefone(phoneInsurer));
		}
		System.out.println("Email:");
		String emailInsurer = input.nextLine();
		if (!Validacao.validaEmail(emailInsurer)) {
			do {
				System.out.println("Formato de Email inválido! Tente novamente:");
				emailInsurer = input.nextLine();
			} while(!Validacao.validaEmail(emailInsurer));
		}
		System.out.println("Endereco:");
		String addressInsurer = input.nextLine();
		
		Seguradora s1 = new Seguradora(nameInsurer, phoneInsurer, emailInsurer, addressInsurer);
		listaSeguradoras.add(s1);
		System.out.println("Seguradora cadastrada com sucesso!");
		atualizarValorMensal();
	}
	
	public static void cadastrarCondutor() {	
		Scanner input = new Scanner(System.in);
		System.out.println("Cadastro de novo condutor");
		System.out.println("Nome:");
		String name = input.nextLine();
		if (!Validacao.validaNome(name)) {
			do {
				System.out.println("Nome inválido! Tente novamente:");
				name = input.nextLine();
			} while(!Validacao.validaNome(name));
		}
		System.out.println("CPF (XXX.XXX.XXX-XX):");
		String cpf = input.nextLine();
		if (!Validacao.validaCPF(cpf)) {
			do {
				System.out.println("Numero de CPF inválido! Digite novamente:");
				cpf = input.nextLine();
			} while (!Validacao.validaCPF(cpf));
		}
		System.out.println("Telefone:");
		String phoneC = input.nextLine();
		if (!Validacao.validaTelefone(phoneC)) {
			do {
				System.out.println("Formato de telefone inválido! Tente o formato (xx)xxxxx-xxxx ou (xx)xxxx-xxxx:");
				phoneC = input.nextLine();
			} while (!Validacao.validaTelefone(phoneC));
		}
		System.out.println("Email:");
		String emailC = input.nextLine();
		if (!Validacao.validaEmail(emailC)) {
			do {
				System.out.println("Formato de Email inválido! Tente novamente:");
				emailC = input.nextLine();
			} while(!Validacao.validaEmail(emailC));
		}
		System.out.println("Data de Nascimento (dd/MM/yyyy):");
		String birthDate = input.nextLine();
		if (!Validacao.validaData(birthDate)) {
			do {
				System.out.println("Formato de data inválido! Digite novamente:");
				birthDate = input.nextLine();
			} while (!Validacao.validaData(birthDate)) ;
		}
		Date birth = null;
		try {
			birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Condutor cf = new Condutor(name, cpf, phoneC, emailC, birth);
		atualizarValorMensal();
		
		System.out.println("Deseja cadastrar em qual seguro?:");
		String id = input.nextLine();
		int codigo = Integer.parseInt(id);
		Seguro s = buscarSeguro(codigo);
		s.autorizarCondutor(cf);
		atualizarValorMensal();
	}
	
	public static Seguro buscarSeguro(int id) {
		Seguro se = null;
		for(Seguradora s: listaSeguradoras) {
			for(Seguro seg: s.listaSeguros) {
				if(seg.getId() == id) {
					se = seg;
				}
			}
		}
		return se;
	}
	
	public static void gerarSinistro() {
		Scanner input = new Scanner(System.in);
		System.out.println("Cadastro do sinistro");
		System.out.println("Data:");
		String dateSin = input.nextLine();
		if (!Validacao.validaData(dateSin)) {
			do {
				System.out.println("Formato de data inválido! Digite novamente:");
				dateSin = input.nextLine();
			} while (!Validacao.validaData(dateSin)) ;
		}
		Date occurence = null;
		try {
			occurence = new SimpleDateFormat("dd/MM/yyyy").parse(dateSin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Endereco:");
		String addressSin = input.nextLine();
		System.out.println("Nome do cliente:");
		String client = input.nextLine();
		Cliente c = buscarCliente(client);
		if(c == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		
		System.out.println("Nome da seguradora:");
		String seguradora = input.nextLine();
		Seguradora s1 = buscarSeguradora(seguradora);
		if(s1 == null) {
			System.out.println("Seguradora não encontrada!");
			return;
		}
		
		System.out.println("Placa do veiculo:");
		String placa = input.nextLine();
		if (!Validacao.validaPlaca(placa)) {
			do {
				System.out.println("Formato de placa inválido! Tente novamente:");
				placa = input.nextLine();
			} while(!Validacao.validaPlaca(placa));
		}
		Veiculo v = buscarVeiculo(placa);
		if(v == null) {
			System.out.println("Veiculo não encontrado!");
			return;
		}
		System.out.println("Deseja cadastrar em qual seguro?:");
		String id = input.nextLine();
		int codigo = Integer.parseInt(id);
		Seguro s = buscarSeguro(codigo);
		
		System.out.println("Cpf do condutor:");
		String cpf = input.nextLine();
		Condutor condutor = buscarCondutor(cpf);
		
		Sinistro sin1 = new Sinistro(occurence, addressSin, condutor, s);
		s.gerarSinistro(sin1);
		System.out.println("Sinistro gerado com sucesso!");
		atualizarValorMensal();
		//System.out.println(sin1.toString());
	}
	
	public static Condutor buscarCondutor(String cpf) {
		Condutor condutor = null;
		for(Seguradora s: listaSeguradoras) {
			for(Seguro seg: s.listaSeguros) {
				for(Condutor c: seg.listaCondutores) {
					if(c.getCpf().equals(cpf)) {
						condutor = c;
					}
				}
			}
		}
		return condutor;
	}
	
	public static void listarClientesSeguradora() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da seguradora: ");
		String seguradora = scanner.nextLine();
		Seguradora seg = buscarSeguradora(seguradora);
		if (seg == null) {
			do {
				System.out.println("Digite o nome da seguradora novamente: ");
				seguradora = scanner.nextLine();
				seg = buscarSeguradora(seguradora);
			}while(seg == null);
		}
		System.out.println("Deseja listar as pessoas fisicas ou juridicas? (PF/PJ)");
		String tipo = scanner.nextLine();
		if (!(tipo.equals("PJ")) && (!(tipo.equals("PF")))) {
			do {
				System.out.println("Resposta inválida. Digite PF ou PJ.");
				tipo = scanner.nextLine();
			} while(!(tipo.equals("PJ")) && (!(tipo.equals("PF"))));
		}
		if (tipo.equals("PF")) {
			seg.listarClientes("PF");
		}
		else if (tipo.equals("PJ")) {
			seg.listarClientes("PJ");
		}
	}
	
	public static void listarSinistrosSeguradora() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da seguradora: ");
		String seguradora = scanner.nextLine();
		Seguradora seg = buscarSeguradora(seguradora);
		for(Seguro s: seg.listaSeguros) {
			for(Sinistro sin: s.listaSinistros) {
				System.out.println(sin.toString());
				System.out.println();
			}
		}
	}
	
	public static void listarSinistrosCliente() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o cpf/cnpj do cliente: ");
		String client = scanner.nextLine();
		for(Seguradora s: listaSeguradoras) {
			for(Sinistro c: s.getSinistrosPorCliente(client)) {
				System.out.println(c.toString());
				System.out.println();
			}
		}
	}
	
	public static void listarSegurosCliente() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o cpf/cnpj do cliente: ");
		String client = scanner.nextLine();
		for(Seguradora s: listaSeguradoras) {
			for(Seguro c: s.getSegurosPorCliente(client)) {
				System.out.println(c.toString());
				System.out.println();
			}
		}
	}
	
	public static void listarVeiculoCliente() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do cliente: ");
		String client = scanner.nextLine();
		Cliente c = buscarCliente(client);
		if(c == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		for (Veiculo v: c.getListaVeiculos()) {
			System.out.println(v.toString());
		}
	}
	
	public static void listarVeiculoSeguradora() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da seguradora: ");
		String seguradora = scanner.nextLine();
		Seguradora s1 = buscarSeguradora(seguradora);
		for (Cliente c: s1.listaClientes) {
			for (Veiculo v: c.getListaVeiculos()) {
				System.out.println(v.toString());
			}
		}
	}
	
	public static void excluirCliente() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do cliente: ");
		String client = scanner.nextLine();
		Cliente c = buscarCliente(client);
		System.out.println("Digite o nome da seguradora: ");
		String seguradora = scanner.nextLine();
		Seguradora s = buscarSeguradora(seguradora);

		if (s.removerCliente(c)) {
			System.out.println("Cliente removido!");
		}
		else {
			System.out.println("Cliente não encontrado!");
		}
	}
	
	public static void excluirVeiculo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do cliente: ");
		String client = scanner.nextLine();
		Cliente c1 = buscarCliente(client);
		if(c1 == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		System.out.println("Digite a placa do veiculo: ");
		String placa = scanner.nextLine();
		if (!Validacao.validaPlaca(placa)) {
			do {
				System.out.println("Formato de placa inválido! Tente novamente:");
				placa = scanner.nextLine();
			} while(!Validacao.validaPlaca(placa));
		}
		System.out.println("Digite o tipo do cliente (PJ/PF): ");
		String tipo = scanner.nextLine();
		boolean placaExiste = false;
		if(tipo == "PF") {
			for(Seguradora s: listaSeguradoras) {
				for(Cliente c: s.listaClientes) {
					if(c instanceof ClientePF) {
						ClientePF cf = (ClientePF) c;
						for(int i = 0; i<  cf.listaVeiculos.size(); i++) {
							if(cf.listaVeiculos.get(i).getPlaca().equals(placa)) {
								placaExiste = true;
								cf.listaVeiculos.remove(i);
							}
						}
					}
				}
			}
		}
		else if(tipo == "PJ") {
			for(Seguradora s: listaSeguradoras) {
				for(Cliente c: s.listaClientes) {
					if(c instanceof ClientePF) {
						ClientePJ cj = (ClientePJ) c;
						for(int j = 0; j < cj.listaFrotas.size(); j++) {
							for(int i = 0; i < cj.listaFrotas.get(j).listaVeiculos.size(); i++) {
								if(cj.listaFrotas.get(j).listaVeiculos.get(i).getPlaca().equals(placa)) {
									placaExiste = true;
									cj.listaFrotas.get(j).listaVeiculos.remove(i);
								}
							}
						}
					}
				}
			}
		}
		if(placaExiste == true) {
			System.out.println("Veiculo excluido!");
		}
		else {
			System.out.println("Veiculo nao encontrado!");
		}
	}
	
	public static void excluirSinistro() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o ID do sinistro: ");
		String id = scanner.nextLine();
		boolean sinistroExiste = false;
		for (Seguradora s: listaSeguradoras) {
			for(Seguro c: s.listaSeguros) {
				for (int i = 0; i < c.listaSinistros.size(); i++) {
					if (c.listaSinistros.get(i).getId() == Integer.parseInt(id)){
						sinistroExiste = true;
						c.listaSinistros.remove(i);
					}
				}
			}
		}
		if(sinistroExiste == true) {
			System.out.println("Sinistro excluido!");
		}
		else {
			System.out.println("Sinistro nao encontrado!");
		}
	}
	
//	public static void transferirSeguro() {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Digite o nome do cliente que transferirá o seguro: ");
//		String client1 = scanner.nextLine();
//		Cliente ct = buscarCliente(client1);
//		if(ct == null) {
//			System.out.println("Cliente não encontrado!");
//			return;
//		}
//		System.out.println("Digite o nome do cliente que receberá o seguro: ");
//		String client2 = scanner.nextLine();
//		Cliente cr = buscarCliente(client2);
//		if(cr == null) {
//			System.out.println("Cliente não encontrado!");
//			return;
//		}
//		for (int i = 0; i < ct.listaVeiculos.size(); i++) {
//			cr.listaVeiculos.add(ct.listaVeiculos.get(i));
//		}
//		ct.listaVeiculos.clear();
//		Seguradora seguradora = null;
//		for (Seguradora s: listaSeguradoras) {
//			for (Cliente c1: s.listaClientes) {
//				if (c1.getNome().equals(client1)) {
//					seguradora = s;
//				}
//			}
//		}
//		double valor = seguradora.calcularPrecoSeguroCliente(ct.getNome());
//		ct.setValorSeguro(valor);
//		
//		seguradora = null;
//		for (Seguradora s: listaSeguradoras) {
//			for (Cliente c1: s.listaClientes) {
//				if (c1.getNome().equals(client2)) {
//					seguradora = s;
//				}
//			}
//		}
//		valor = seguradora.calcularPrecoSeguroCliente(cr.getNome());
//		cr.setValorSeguro(valor);
//		System.out.println("Seguro transferido de " + ct.getNome() + " para " + cr.getNome() + "!");
//	}
	
	public static void calcularReceita() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da seguradora: ");
		String seguradora = scanner.nextLine();
		Seguradora s = buscarSeguradora(seguradora);
		System.out.println("Receita: " + s.calcularReceita());
	}
	
	public static void executarOperacaoSubMenu(SubMenuOperacoes op) throws ParseException {
		switch(op) {
		case CADASTRAR_CLIENTE:
			cadastrarCliente();
			exibirSubMenu(1);
			break;
		case CADASTRAR_VEICULO:
			cadastrarVeiculo();
			exibirSubMenu(1);
			break;
		case CADASTRAR_SEGURADORA:
			cadastrarSeguradora();
			exibirSubMenu(1);
			break;
		case CADASTRAR_CONDUTOR:
			cadastrarCondutor();
			exibirSubMenu(1);
			break;
		case CADASTRAR_SEGURO:
			Scanner scanner = new Scanner(System.in);
			System.out.println("Seguro PF ou PJ?");
			String tipo = scanner.nextLine();
			if(tipo == "PF") {
				cadastrarSeguroPF();
			}
			else if(tipo == "PJ") {
				cadastrarSeguroPJ();
			}
			exibirSubMenu(1);
			break;
		case LISTAR_CLIENTES:
			listarClientesSeguradora();
			exibirSubMenu(2);
			break;
		case LISTAR_SINISTROS_POR_SEGURADORA:
			listarSinistrosSeguradora();
			exibirSubMenu(2);
			break;
		case LISTAR_SINISTROS_POR_CLIENTE:
			listarSinistrosCliente();
			exibirSubMenu(2);
			break;
		case LISTAR_VEICULO_POR_CLIENTE:
			listarVeiculoCliente();
			exibirSubMenu(2);
			break;
		case LISTAR_VEICULO_POR_SEGURADORA:
			listarVeiculoSeguradora();
			exibirSubMenu(2);
			break;
		case EXCLUIR_CLIENTE:
			excluirCliente();
			exibirSubMenu(3);
			break;
		case EXCLUIR_VEICULO:
			excluirVeiculo();
			exibirSubMenu(3);
			break;
		case EXCLUIR_SINISTRO:
			excluirSinistro();
			exibirSubMenu(3);
			break;
		case VOLTAR:
			exibirMenuPrincipal();
			break;
		}
	}
	
	private static void exibirSubMenu(int screen) throws ParseException {
		SubMenuOperacoes subMenu[] = SubMenuOperacoes.values();
		SubMenuOperacoes operacaoConst;
		int op = -1;
		Scanner scanner = new Scanner(System.in);
		if (screen == 1) {
			System.out.println("Menu de cadastro:");
			System.out.println();
			for (int i = 1; i < 6; i++) {
				System.out.println(subMenu[i].getOperacao() + " - " + subMenu[i].getDescricao());
			}
			System.out.println(subMenu[0].getOperacao() + " - " + subMenu[0].getDescricao());
			while ((op < 0) || (op > 5))
			{
				System.out.println();
				System.out.println("Digite o numero da operacao: ");
				op = scanner.nextInt();
			}
			operacaoConst = SubMenuOperacoes.values()[op];
			executarOperacaoSubMenu(operacaoConst);
		}
		else if (screen == 2) {
			System.out.println("Menu de listagem:");
			System.out.println();
			for (int i = 6; i < 11; i++) {
				System.out.println(subMenu[i].getOperacao() - 5 + " - " + subMenu[i].getDescricao());
			}
			System.out.println(subMenu[0].getOperacao() + " - " + subMenu[0].getDescricao());
			while (((op < 6) || (op > 10)) && (op != 0))
			{
				System.out.println();
				System.out.println("Digite o numero da operacao: ");
				op = scanner.nextInt();
				if (op > 0) {
					op += 5;
				}
			}
			operacaoConst = SubMenuOperacoes.values()[op];
			executarOperacaoSubMenu(operacaoConst);
		}
		else if (screen == 3) {
			System.out.println("Menu de exclusao:");
			System.out.println();
			for (int i = 11; i < 14; i++) {
				System.out.println(subMenu[i].getOperacao() - 11 + " - " + subMenu[i].getDescricao());
			}
			System.out.println(subMenu[0].getOperacao() + " - " + subMenu[0].getDescricao());
			while (((op < 11) || (op > 13)) && (op != 0))
			{
				System.out.println();
				System.out.println("Digite o numero da operacao: ");
				op = scanner.nextInt();
				if (op > 0) {
					op += 10;
				}
			}
			operacaoConst = SubMenuOperacoes.values()[op];
			executarOperacaoSubMenu(operacaoConst);
		}
	}
	
	public static void executarOperacaoMenu(MenuOperacoes op) throws ParseException {
		switch(op) {
		case CADASTRAR:
			exibirSubMenu(1);
			break;
		case LISTAR:
			exibirSubMenu(2);
			break;
		case EXCLUIR:
			exibirSubMenu(3);
			break;
		case GERAR_SINISTRO:
			gerarSinistro();
			exibirMenuPrincipal();
			break;
		case TRANSFERIR_SEGURO:
			//transferirSeguro();
			exibirMenuPrincipal();
			break;
		case CALCULAR_RECEITA:
			calcularReceita();
			exibirMenuPrincipal();
			break;
		case SAIR:
			System.exit(0);
		}
	}
	
	private static void exibirMenuPrincipal() throws ParseException {
		MenuOperacoes menuPrincipal[] = MenuOperacoes.values();
		MenuOperacoes operacaoConst;
		Scanner scanner = new Scanner(System.in);
		int op = -1;
		System.out.println("Menu Principal:");
		System.out.println();
		for (MenuOperacoes operacao: menuPrincipal) {
			System.out.println(operacao.operacao + " - " + operacao.getDescricao());
		}
		while ((op < 0) || (op > MenuOperacoes.values().length - 1))
		{
			System.out.println();
			System.out.println("Digite o numero da operacao: ");
			op = scanner.nextInt();
		}
		operacaoConst = MenuOperacoes.values()[op];
		executarOperacaoMenu(operacaoConst);
	}
	
	public static void main(final String[] args) throws Exception {
		//Instanciando seguradoras
		Seguradora alfaSeguros = new Seguradora("AlfaSeguros", "(19)99876-5432", "contato@alfa.com", "Av. das Flores");
		listaSeguradoras.add(alfaSeguros);
		System.out.println(alfaSeguros.toString());
		
		Seguradora deltaSeguros = new Seguradora("DeltaSeguros", "(19)98765-5432", "contato@delta.com", "Av. das Folhas");
		listaSeguradoras.add(deltaSeguros);
		System.out.println(deltaSeguros.toString());
		
		//Instanciando primeira pessoa física
		String licenseDate = "01/06/2010";
		String birthDate = "25/06/2002";
		Date birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		Date license = new SimpleDateFormat("dd/MM/yyyy").parse(licenseDate);
		ClientePF mateus = new ClientePF("Mateus", "Av. dos Animais", "433.993.038-58", "Masculino", license, "EM",
			birth, "CM");
		System.out.println(mateus.toString());
		
		//Instanciando segunda pessoa física
		licenseDate = "02/02/2002";
		birthDate = "25/06/1980";
		birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		license = new SimpleDateFormat("dd/MM/yyyy").parse(licenseDate);
		ClientePF adiano = new ClientePF("Adiano", "Av. dos Piratas", "021.335.880-86", "Masculino", license, "EM",
				birth, "CB");
		System.out.println(adiano.toString());
		
		//Instanciando primeira pessoa jurídica
		birthDate = "16/02/2000";
		birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		ClientePJ betaCarros = new ClientePJ("BetaCarros", "Av. dos Fungos", "87.612.546/0001-81", birth);
		System.out.println(betaCarros.toString());
		
		//Instanciando segunda pessoa jurídica
		birthDate = "16/02/2000";
		birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		ClientePJ sigmaCarros = new ClientePJ("SigmaCarros", "Av. Oppenheimer", "81.213.500/0001-03", birth);
		System.out.println(sigmaCarros.toString());
		
		//Instanciando veículos para PF
		ArrayList<Veiculo> veiculosPF = new ArrayList<>();
		
		Veiculo carro1 = new Veiculo("EKZ8431", "Volkswagen", "Virtus", 2021);
		Veiculo carro2 = new Veiculo("GRZ9D20", "Audi", "R8", 2023);
		veiculosPF.add(carro1);
		veiculosPF.add(carro2);
		
		mateus.setListaVeiculos(veiculosPF);
		
		veiculosPF.clear();
		
		//Instanciando veículos para segunda PF
		Veiculo carro3 = new Veiculo("EFG9090", "McLaren", "P1", 2021);
		Veiculo carro4 = new Veiculo("HGE8E19", "Audi", "A4", 2017);
		veiculosPF.add(carro3);
		veiculosPF.add(carro4);
		
		adiano.setListaVeiculos(veiculosPF);
		
		//Instanciando veículos para PJ	
		Veiculo carro5 = new Veiculo("RSB7354", "Chevrolet", "Onix", 2023);
		Veiculo carro6 = new Veiculo("UBD3G11", "Porsche", "Taycan", 2022);
		
		Veiculo carro7 = new Veiculo("ADD1234", "Renault", "Kwid", 2019);
		Veiculo carro8 = new Veiculo("ABV7T45", "Chevrolet", "Cruze", 2018);

		
		//Instanciando e cadastrando frotas
		Frota frotaBeta = new Frota("Beta2022");
		Frota frotaSigma = new Frota("Sigma2023");
		frotaBeta.addVeiculo(carro5);
		frotaBeta.addVeiculo(carro6);
		frotaSigma.addVeiculo(carro7);
		frotaSigma.addVeiculo(carro8);
		
		betaCarros.cadastrarFrota(frotaBeta);
		sigmaCarros.cadastrarFrota(frotaSigma);
		
		//Cadastrando clientes na seguradora Alfa
		alfaSeguros.cadastrarCliente(mateus);
		alfaSeguros.cadastrarCliente(betaCarros);
		System.out.println(mateus.toString());
		System.out.println(betaCarros.toString());
		
		//Cadastrando clientes na seguradora Delta
		deltaSeguros.cadastrarCliente(adiano);
		deltaSeguros.cadastrarCliente(sigmaCarros);
		System.out.println(adiano.toString());
		System.out.println(sigmaCarros.toString());
		
		//Instanciando e cadastrando Seguros
		String startDate = "10/10/2010";
		Date start = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
		String endDate = "10/10/2030";
		Date end = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
		SeguroPF seguroMateus = new SeguroPF(start, end, alfaSeguros, carro1, mateus);
		alfaSeguros.gerarSeguro(seguroMateus);
		
		startDate = "09/09/2015";
		start = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
		endDate = "09/09/2035";
		end = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
		SeguroPJ seguroSigma = new SeguroPJ(start, end, deltaSeguros, frotaSigma, sigmaCarros);
		deltaSeguros.gerarSeguro(seguroSigma);
		
		System.out.println(seguroMateus.toString());
		System.out.println(seguroSigma.toString());
		
		//Instanciando e cadastrando condutores
		birthDate = "25/06/2002";
		birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		Condutor mateusCondutor = new Condutor("Mateus", "433.993.038-58", "(19)99146-5135",
				                                "mateus@gmail.com", birth);
		seguroMateus.autorizarCondutor(mateusCondutor);
		
		birthDate = "10/06/1990";
		birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		Condutor julianoCondutor = new Condutor("Juliano", "583.523.790-11", "(19)99778-3646",
				                                "juliano@gmail.com", birth);
		seguroSigma.autorizarCondutor(julianoCondutor);
		
		//Gerando primeiro sinistro
		String occurenceDate = "11/04/2020";
		Date occurence = new SimpleDateFormat("dd/MM/yyyy").parse(occurenceDate);
		Sinistro sinistroSigma = new Sinistro(occurence, "Rua dos Protozoarios", julianoCondutor, seguroSigma);
		//Adicionando sinistro no condutor
		julianoCondutor.adicionarSinistro(sinistroSigma);
		sinistroSigma.getSeguro().gerarSinistro(sinistroSigma);
		
		//Gerando segundo sinistro
		occurenceDate = "11/04/2020";
		occurence = new SimpleDateFormat("dd/MM/yyyy").parse(occurenceDate);
		Sinistro sinistroMateus = new Sinistro(occurence, "Rua dos Protozoarios", mateusCondutor, seguroMateus);
		//Adicionando sinistro no condutor
		mateusCondutor.adicionarSinistro(sinistroMateus);
		sinistroMateus.getSeguro().gerarSinistro(sinistroMateus);
		
		System.out.println();
		System.out.println("CLIENTES ALFA");
		System.out.println();
		
		alfaSeguros.listarClientes("PF");
		alfaSeguros.listarClientes("PJ");
		
		System.out.println();
		System.out.println("CLIENTES DELTA");
		System.out.println();
		
		deltaSeguros.listarClientes("PF");
		deltaSeguros.listarClientes("PJ");
		
		System.out.println();
		System.out.println("SEGUROS POR CLIENTE");
		System.out.println();
		
		//Printando seguros por cliente
		ArrayList<Seguro> listaSegurosPorCliente = new ArrayList<>();
		listaSegurosPorCliente = alfaSeguros.getSegurosPorCliente("433.993.038-58");
		for(Seguro s: listaSegurosPorCliente) {
			System.out.println(s.toString());
			System.out.println();
		}
		
		//Atualizando valores
		seguroMateus.calcularValor();
		seguroSigma.calcularValor();
		
		System.out.println();
		System.out.println("RECEITAS");
		System.out.println();
		
		//alfaSeguros.visualizarSinistro(betaCarros.getNome());
		//alfaSeguros.listarSinistros();
		System.out.println(alfaSeguros.calcularReceita());
		System.out.println(deltaSeguros.calcularReceita());
		
		//Executando o menu
		exibirMenuPrincipal();
	}
}