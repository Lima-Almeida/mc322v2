package lab05;

import java.util.ArrayList;
import java.util.Date;

abstract public class Seguro {
	
	final private int id;
	private Date dataInicio;
	private Date dataFim;
	private Seguradora seguradora;
	protected ArrayList<Sinistro> listaSinistros = new ArrayList<>();
	protected ArrayList<Condutor> listaCondutores = new ArrayList<>();
	private int valorMensal;

	public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		this.id = Validacao.idGenerator();
	}
	
	public int getId() {
		return id;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public Seguradora getSeguradora() {
		return seguradora;
	}
	
	public void setDataFim(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	
	public int getValorMensal() {
		return valorMensal;
	}
	
	public void setValorMensal(int valorMensal) {
		this.valorMensal = valorMensal;
	}
	
	abstract public ArrayList<Veiculo> getListaVeiculos();
	
	abstract public boolean autorizarCondutor(Condutor condutor);
	
	abstract public boolean desautorizarCondutor(Condutor condutor);
	
	abstract public void calcularValor();
	
	abstract public void gerarSinistro(Sinistro sinistro);
}