package lab05;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ClientePJ extends Cliente {

	final private String cnpj;
	private Date dataFundacao;
	private int idade;
	protected ArrayList<Frota> listaFrotas = new ArrayList<>();
	
	public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao) {
		super(nome, endereco);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.idade = calcularIdade();
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
	
	public int getIdade() {
		return idade;
	}
	
	public void cadastrarFrota(Frota frota) {
		this.listaFrotas.add(frota);
	}
	
	public void atualizarFrota(Frota f) {
		
	}
	
	public ArrayList<Veiculo> getListaVeiculos() {
		ArrayList<Veiculo> lista = new ArrayList<>();
		for(Frota f: this.listaFrotas) {
			for(Veiculo v: f.listaVeiculos) {
				lista.add(v);
			}
		}
		return lista;
	}
	
	public int calcularIdade() {
		int age;
		Calendar birthDate = Calendar.getInstance();
		Calendar currentDate = Calendar.getInstance();
		Date dateC = new Date(System.currentTimeMillis());
		currentDate.setTime(dateC);
		birthDate.setTime(dataFundacao);
		age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		
		//Checando se o mês/dia do ano atual já passou do mês/dia de aniversário
		if ((birthDate.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH)) ||
				(birthDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) &&
				(birthDate.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH)))) {
			age -= 1;
		}
		return age;
	}
	
	public ArrayList<Veiculo> getVeiculosPorFrota(String code) {
		ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
		for (Frota f: listaFrotas) {
			if(f.getCode().equals(code)) {
				for(Veiculo v: f.listaVeiculos) {
					listaVeiculos.add(v);
				}
			}
		}
		return listaVeiculos;
	}
	
	public String toString() {
		return "Nome: "+getNome()+
		"\n Endereco: "+getEndereco()+
		"\n CNPJ: "+getCnpj();
	}
}