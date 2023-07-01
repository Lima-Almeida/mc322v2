package lab05;

import java.util.ArrayList;

public class Frota {
	
	private String code;
	protected ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
	
	public Frota(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean addVeiculo(Veiculo veiculo) {
		if(this.listaVeiculos.contains(veiculo)) {
			return false;
		}
		else {
			this.listaVeiculos.add(veiculo);
			return true;
		}
	}
	
	public boolean removeVeiculo(Veiculo veiculo) {
		return this.listaVeiculos.remove(veiculo);
	}
}