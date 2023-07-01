package lab05;

public enum MenuOperacoes {
	SAIR(0, "Sair"),
	CADASTRAR(1, "Cadastrar"),
	LISTAR(2, "Listar"),
	EXCLUIR(3, "Excluir"),
	GERAR_SINISTRO(4, "Gerar Sinistro"),
	TRANSFERIR_SEGURO(5, "Transferir Seguro"),
	CALCULAR_RECEITA(6, "Calcular receita");
	
	public final int operacao;
	public final String descricao;
	
	MenuOperacoes(int operacao, String descricao) {
		this.operacao = operacao;
		this.descricao = descricao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}