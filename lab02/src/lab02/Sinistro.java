package lab02;
import java.util.Random;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	
	public Sinistro(String data, String endereco) {
		this.id = idGenerator();
		this.data = data;
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public int idGenerator() {
		Random number = new Random();
		int threshold = 100000;
		int id = number.nextInt(threshold);
		return id;
	}
	
	public String toString() {
		return "ID: "+id+
				"\n Data:  "+data+
				"\n Endereco: "+endereco;
	}
}
