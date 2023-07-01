package lab05;

import java.util.ArrayList;
import java.util.Date;

public class SeguroPF extends Seguro {

	private Veiculo veiculo;
	private ClientePF cliente;
	
	public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora,
					Veiculo veiculo, ClientePF cliente) {
		super(dataInicio, dataFim, seguradora);
		this.veiculo = veiculo;
		this.cliente = cliente;
		calcularValor();
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public ClientePF getCliente() {
		return cliente;
	}
	
	public void setCliente(ClientePF cliente) {
		this.cliente = cliente;
	}

	@Override
	public boolean autorizarCondutor(Condutor condutor) {
		boolean existe = false;
		for(Condutor c: this.listaCondutores) {
			if(c.getCpf().equals(condutor.getCpf())) {
				existe = true;
			}
		}
		if(existe) {
			return false;
		}
		else {
			this.listaCondutores.add(condutor);
			return true;
		}
	}

	@Override
	public boolean desautorizarCondutor(Condutor condutor) {
		boolean existe = false;
		for(Condutor c: this.listaCondutores) {
			if(c.getCpf().equals(condutor.getCpf())) {
				existe = true;
			}
		}
		if(existe) {
			this.listaCondutores.remove(condutor);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void calcularValor() {
		CalcSeguro calcseguro[] = CalcSeguro.values();
		double valor = 0;
		int age = cliente.getIdade();
		if (age <= 30) {
			valor = calcseguro[0].fator() * calcseguro[1].fator() * cliente.listaVeiculos.size();
		}
		else if ((age > 30) && (age <= 60)) {
			valor = calcseguro[0].fator() * calcseguro[2].fator() * cliente.listaVeiculos.size();
		}
		else if (age > 60) {
			valor = calcseguro[0].fator() * calcseguro[3].fator() * cliente.listaVeiculos.size();
		}
		this.setValorMensal((int)Math.round(valor));
	}

	@Override
	public void gerarSinistro(Sinistro sinistro) {
		this.listaSinistros.add(sinistro);
	}
	
	@Override
	public ArrayList<Veiculo> getListaVeiculos() {
		return this.cliente.listaVeiculos;
	}
	
	public String toString() {
		return "ID: "+this.getId()+
		"\n Data do inicio: "+this.getDataInicio()+
		"\n Data do fim: "+this.getDataFim()+
		"\n Seguradora: "+this.getSeguradora().getNome()+
		"\n Valor mensal: "+this.getValorMensal()+
		"\n Veiculo: "+this.getVeiculo().toString()+
		"\n Cliente: "+this.getCliente().toString();
	}
}