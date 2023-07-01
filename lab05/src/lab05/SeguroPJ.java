package lab05;

import java.util.ArrayList;
import java.util.Date;

public class SeguroPJ extends Seguro {

	private Frota frota;
	private ClientePJ cliente;
	
	public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora,
					Frota frota, ClientePJ cliente) {
		super(dataInicio, dataFim, seguradora);
		this.frota = frota;
		this.cliente = cliente;
		calcularValor();
	}
	
	public Frota getFrota() {
		return frota;
	}
	
	public void setFrota(Frota frota) {
		this.frota = frota;
	}
	
	public ClientePJ getCliente() {
		return cliente;
	}
	
	public void setCliente(ClientePJ cliente) {
		this.cliente = cliente;
	}

	@Override
	public boolean autorizarCondutor(Condutor condutor) {
		if (listaCondutores.contains(condutor)) {
			return false;
		}
		else {
			listaCondutores.add(condutor);
			return true;
		}
	}

	@Override
	public boolean desautorizarCondutor(Condutor condutor) {
		return listaCondutores.remove(condutor);
	}

	@Override
	public void calcularValor() {
		CalcSeguro calcseguro[] = CalcSeguro.values();
		double valor = 0;
		double qntFuncionarios = listaCondutores.size();
		double anosPosFundacao = cliente.getIdade();
		double qntVeiculos = frota.listaVeiculos.size();
		double qntSinistrosCliente = listaSinistros.size();
		double qntSinistrosCondutor = listaCondutores.size();
		valor = calcseguro[0].fator() * 10 * (qntFuncionarios/10) * 
				(1 + 1/(qntVeiculos + 2)) * (1 + 1/(anosPosFundacao + 2)) *
				(2 + qntSinistrosCliente/10) * (5 + qntSinistrosCondutor/10);
		this.setValorMensal((int)Math.round(valor));
		//cliente.setValorSeguro(valor);
	}

	@Override
	public void gerarSinistro(Sinistro sinistro) {
		this.listaSinistros.add(sinistro);
	}
	
	@Override
	public ArrayList<Veiculo> getListaVeiculos() {
		ArrayList<Veiculo> lista = new ArrayList<>();
		for(Frota f: this.cliente.listaFrotas) {
			for(Veiculo v: f.listaVeiculos) {
				lista.add(v);
			}
		}
		return lista;
	}
	
	public String toString() {
		return "ID: "+this.getId()+
		"\n Data do inicio: "+this.getDataInicio()+
		"\n Data do fim: "+this.getDataFim()+
		"\n Seguradora: "+this.getSeguradora().getNome()+
		"\n Valor mensal: "+this.getValorMensal()+
		"\n Frota: "+this.getFrota().getCode()+
		"\n Cliente: "+this.getCliente().toString();
	}
}