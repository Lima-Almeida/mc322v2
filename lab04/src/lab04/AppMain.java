package lab04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
	
	public static Cliente buscarCliente(String nome) {
		Cliente c = null;
		for (Seguradora s: listaSeguradoras) {
			for (int i = 0; i < s.listaClientes.size(); i++) {
				if (s.listaClientes.get(i).getNome().equals(nome)){
					c = s.listaClientes.get(i);
				}
			}
		}
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
		ClientePJ cj = new ClientePJ(name, address, cnpj, birth, Integer.parseInt(employeeQnt));
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

		if (tipo.equals("PF")) {
			ClientePF cf = cadastrarClientePF();
			seguradora.cadastrarCliente(cf);
			cf.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cf.getNome()));
			System.out.println("Cliente cadastrado com sucesso!");
		}
		else if (tipo.equals("PJ")) {
			ClientePJ cj = cadastrarClientePJ();
			seguradora.cadastrarCliente(cj);
			cj.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cj.getNome()));
			System.out.println("Cliente cadastrado com sucesso!");
		}
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
		c.setListaVeiculos(veiculos);
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
		double valor = seguradora.calcularPrecoSeguroCliente(c.getNome());
		c.setValorSeguro(valor);
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
		Veiculo v = null;
		for (Veiculo v1: c.listaVeiculos) {
			if(v1.getPlaca().equals(placa)) {
				v = v1;
			}
		}
		if(v == null) {
			System.out.println("Veiculo não encontrado!");
			return;
		}
		Sinistro sin1 = new Sinistro(occurence, addressSin, s1, c, v);
		s1.gerarSinistro(sin1);
		System.out.println("Sinistro gerado com sucesso!");
		//System.out.println(sin1.toString());
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
		seg.listarSinistros();
	}
	
	public static void listarSinistrosCliente() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do cliente: ");
		String client = scanner.nextLine();
		Cliente c = buscarCliente(client);
		if(c == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		for (Seguradora s: listaSeguradoras) {
			for (int i = 0; i < s.listaSinistros.size(); i++) {
				if (s.listaSinistros.get(i).getCliente() == c){
					System.out.println(s.listaSinistros.get(i).toString());
				}
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
		for (Veiculo v: c.listaVeiculos) {
			System.out.println(v.toString());
		}
	}
	
	public static void listarVeiculoSeguradora() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da seguradora: ");
		String seguradora = scanner.nextLine();
		Seguradora s1 = buscarSeguradora(seguradora);
		for (Cliente c: s1.listaClientes) {
			for (Veiculo v: c.listaVeiculos) {
				System.out.println(v.toString());
			}
		}
	}
	
	public static void excluirCliente() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do cliente: ");
		String client = scanner.nextLine();
		System.out.println("Digite o nome da seguradora: ");
		String seguradora = scanner.nextLine();
		Seguradora s = buscarSeguradora(seguradora);

		if (s.removerCliente(client)) {
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
		Cliente c = buscarCliente(client);
		if(c == null) {
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
		boolean placaExiste = false;
		for (int i = 0; i < c.listaVeiculos.size(); i++) {
			if(c.listaVeiculos.get(i).getPlaca().equals(placa)) {
				placaExiste = true;
				c.listaVeiculos.remove(i);
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
			for (int i = 0; i < s.listaSinistros.size(); i++) {
				if (s.listaSinistros.get(i).getId() == Integer.parseInt(id)){
					sinistroExiste = true;
					s.listaSinistros.remove(i);
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
	
	public static void transferirSeguro() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome do cliente que transferirá o seguro: ");
		String client1 = scanner.nextLine();
		Cliente ct = buscarCliente(client1);
		if(ct == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		System.out.println("Digite o nome do cliente que receberá o seguro: ");
		String client2 = scanner.nextLine();
		Cliente cr = buscarCliente(client2);
		if(cr == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		for (int i = 0; i < ct.listaVeiculos.size(); i++) {
			cr.listaVeiculos.add(ct.listaVeiculos.get(i));
		}
		ct.listaVeiculos.clear();
		Seguradora seguradora = null;
		for (Seguradora s: listaSeguradoras) {
			for (Cliente c1: s.listaClientes) {
				if (c1.getNome().equals(client1)) {
					seguradora = s;
				}
			}
		}
		double valor = seguradora.calcularPrecoSeguroCliente(ct.getNome());
		ct.setValorSeguro(valor);
		
		seguradora = null;
		for (Seguradora s: listaSeguradoras) {
			for (Cliente c1: s.listaClientes) {
				if (c1.getNome().equals(client2)) {
					seguradora = s;
				}
			}
		}
		valor = seguradora.calcularPrecoSeguroCliente(cr.getNome());
		cr.setValorSeguro(valor);
		System.out.println("Seguro transferido de " + ct.getNome() + " para " + cr.getNome() + "!");
	}
	
	public static void calcularReceita() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o nome da seguradora: ");
		String seguradora = scanner.nextLine();
		Seguradora s = buscarSeguradora(seguradora);
		System.out.println("Receita: " + s.calcularReceita());
	}
	
	public static void executarOperacaoSubMenu(SubMenuOperacoes op) {
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
	
	private static void exibirSubMenu(int screen) {
		SubMenuOperacoes subMenu[] = SubMenuOperacoes.values();
		SubMenuOperacoes operacaoConst;
		int op = -1;
		Scanner scanner = new Scanner(System.in);
		if (screen == 1) {
			System.out.println("Menu de cadastro:");
			System.out.println();
			for (int i = 1; i < 4; i++) {
				System.out.println(subMenu[i].getOperacao() + " - " + subMenu[i].getDescricao());
			}
			System.out.println(subMenu[0].getOperacao() + " - " + subMenu[0].getDescricao());
			while ((op < 0) || (op > 3))
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
			for (int i = 4; i < 9; i++) {
				System.out.println(subMenu[i].getOperacao() - 3 + " - " + subMenu[i].getDescricao());
			}
			System.out.println(subMenu[0].getOperacao() + " - " + subMenu[0].getDescricao());
			while (((op < 4) || (op > 8)) && (op != 0))
			{
				System.out.println();
				System.out.println("Digite o numero da operacao: ");
				op = scanner.nextInt();
				if (op > 0) {
					op += 3;
				}
			}
			operacaoConst = SubMenuOperacoes.values()[op];
			executarOperacaoSubMenu(operacaoConst);
		}
		else if (screen == 3) {
			System.out.println("Menu de exclusao:");
			System.out.println();
			for (int i = 9; i < 12; i++) {
				System.out.println(subMenu[i].getOperacao() - 8 + " - " + subMenu[i].getDescricao());
			}
			System.out.println(subMenu[0].getOperacao() + " - " + subMenu[0].getDescricao());
			while (((op < 9) || (op > 11)) && (op != 0))
			{
				System.out.println();
				System.out.println("Digite o numero da operacao: ");
				op = scanner.nextInt();
				if (op > 0) {
					op += 8;
				}
			}
			operacaoConst = SubMenuOperacoes.values()[op];
			executarOperacaoSubMenu(operacaoConst);
		}
	}
	
	public static void executarOperacaoMenu(MenuOperacoes op) {
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
			transferirSeguro();
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
	
	private static void exibirMenuPrincipal() {
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
	
	public static void main(final String[] args) {
		//Instanciando seguradora
		Seguradora alfaSeguros = new Seguradora("AlfaSeguros", "19998765432", "contato@alfa.com", "Av. das Flores");
		listaSeguradoras.add(alfaSeguros);
		System.out.println(alfaSeguros.toString());
		
		//Instanciando Pessoa Física
		String licenseDate = "01/06/2010";
		String birthDate = "25/06/2002";
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
		ClientePF mateus = new ClientePF("Mateus", "Av. dos Animais", "443.993.038-58", "Masculino", license, "EM",
			birth, "CM");
		System.out.println(mateus.toString());
		
		//Instanciando Pessoa Jurídica
		birthDate = "16/02/2000";
		birth = null;
		try {
			birth = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientePJ betaCarros = new ClientePJ("BetaCarros", "Av. dos Fungos", "87.612.546/0001-81", birth, 10);
		System.out.println(betaCarros.toString());
		
		//Instanciando veículos para PF
		ArrayList<Veiculo> veiculosMateus = new ArrayList<>();
		
		Veiculo carro1 = new Veiculo("EKZ8431", "Volkswagen", "Virtus", 2021);
		Veiculo carro2 = new Veiculo("GRZ9D20", "Audi", "R8", 2023);
		veiculosMateus.add(carro1);
		veiculosMateus.add(carro2);
		
		mateus.setListaVeiculos(veiculosMateus);
		
		//Instanciando veículos para PJ
		ArrayList<Veiculo> veiculosBetaCarros = new ArrayList<>();
		
		Veiculo carro3 = new Veiculo("RSB7354", "Chevrolet", "Onix", 2023);
		Veiculo carro4 = new Veiculo("UBD3G11", "Porsche", "Taycan", 2022);
		veiculosBetaCarros.add(carro3);
		veiculosBetaCarros.add(carro4);
		
		betaCarros.setListaVeiculos(veiculosBetaCarros);
		
		//Cadastrando clientes na seguradora
		alfaSeguros.cadastrarCliente(mateus);
		mateus.setValorSeguro(alfaSeguros.calcularPrecoSeguroCliente(mateus.getNome()));
		alfaSeguros.cadastrarCliente(betaCarros);
		betaCarros.setValorSeguro(alfaSeguros.calcularPrecoSeguroCliente(betaCarros.getNome()));
		System.out.println(mateus.toString());
		System.out.println(betaCarros.toString());
		
		//Gerando sinistros
		String occurenceDate = "11/04/2020";
		Date occurence = null;
		try {
			occurence = new SimpleDateFormat("dd/MM/yyyy").parse(occurenceDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sinistro sinistroBeta = new Sinistro(occurence, "Rua dos Protozoarios", alfaSeguros, betaCarros, betaCarros.listaVeiculos.get(1));
		alfaSeguros.gerarSinistro(sinistroBeta);
		
		occurenceDate = "11/04/2020";
		occurence = null;
		try {
			occurence = new SimpleDateFormat("dd/MM/yyyy").parse(occurenceDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sinistro sinistroMateus = new Sinistro(occurence, "Rua das Bacterias", alfaSeguros, mateus, mateus.listaVeiculos.get(0));
		alfaSeguros.gerarSinistro(sinistroMateus);
		
		alfaSeguros.listarClientes("PF");
		alfaSeguros.listarClientes("PJ");
		
		alfaSeguros.visualizarSinistro(betaCarros.getNome());
		alfaSeguros.listarSinistros();
		System.out.println(alfaSeguros.calcularReceita());
		
		//Executando o menu
		exibirMenuPrincipal();
	}
}