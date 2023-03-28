package lab02;
import java.util.Scanner;

public class Main {
	public static void main(final String[] args) {
		System.out.println("Cadastro de novo cliente");
		Scanner input = new Scanner(System.in);
		System.out.println("Nome:");
		String name = input.nextLine();
		System.out.println("CPF:");
		String cpf = input.nextLine();
		System.out.println("Data de Nascimento:");
		String birthDate = input.nextLine();
		System.out.println("Idade:");
		String age = input.nextLine();
		System.out.println("Endereco:");
		String address = input.nextLine();
		
		Cliente c1 = new Cliente(name, cpf, birthDate, Integer.parseInt(age), address);
		if (c1.validarCpf(cpf) == false) {
			System.out.println("O CPF inserido não é válido!");
		}
		System.out.println(c1.toString());
		
		System.out.println("Cadastro de veículo");
		System.out.println("Placa:");
		String plate = input.nextLine();
		System.out.println("Marca:");
		String brand = input.nextLine();
		System.out.println("Modelo:");
		String model = input.nextLine();

		Veiculo v1 = new Veiculo(plate, brand, model);
		System.out.println(v1.toString());
		
		System.out.println("Cadastro de seguradora");
		System.out.println("Nome:");
		String nameInsurer = input.nextLine();
		System.out.println("Telefone:");
		String phoneInsurer = input.nextLine();
		System.out.println("Email:");
		String emailInsurer = input.nextLine();
		System.out.println("Endereco:");
		String addressInsurer = input.nextLine();
		
		Seguradora s1 = new Seguradora(nameInsurer, phoneInsurer, emailInsurer, addressInsurer);
		System.out.println(s1.toString());
		
		System.out.println("Cadastro do sinistro");
		System.out.println("Data:");
		String dateSin = input.nextLine();
		System.out.println("Endereco:");
		String addressSin = input.nextLine();
		
		Sinistro sin1 = new Sinistro(dateSin, addressSin);
		System.out.println(sin1.toString());

	}
}
