package lab05;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ClientePF extends Cliente {

	final private String cpf;
	private String genero;
	private Date dataLicenca;
	private String educacao;
	private Date dataNascimento;
	private String classeEconomica;
	private int idade;
	protected ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
	
	public ClientePF(String nome, String endereco, String cpf, String genero, Date dataLicenca,
			String educacao, Date dataNascimento, String classeEconomica) {
	super(nome, endereco);
	this.cpf = cpf;
	this.genero = genero;
	this.dataLicenca = dataLicenca;
	this.educacao = educacao;
	this.dataNascimento = dataNascimento;
	this.classeEconomica = classeEconomica;
	this.idade = calcularIdade();
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
	
	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void cadastrarVeiculo(Veiculo veiculo) {
		this.listaVeiculos.add(veiculo);
	}
	
	public boolean removerVeiculo(Veiculo veiculo) {
		return this.listaVeiculos.remove(veiculo);
	}
	
	public int calcularIdade() {
		int age;
		Calendar birthDate = Calendar.getInstance();
		Calendar currentDate = Calendar.getInstance();
		Date dateC = new Date(System.currentTimeMillis());
		currentDate.setTime(dateC);
		birthDate.setTime(dataNascimento);
		age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		
		//Checando se o mês/dia do ano atual já passou do mês/dia de aniversário
		if ((birthDate.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH)) ||
				(birthDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) &&
				(birthDate.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH)))) {
			age -= 1;
		}
		return age;
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