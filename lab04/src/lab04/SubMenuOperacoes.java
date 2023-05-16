package lab04;

public enum SubMenuOperacoes {
	VOLTAR(0, "voltar"),
	CADASTRAR_CLIENTE(1, "Cadastrar cliente"),
	CADASTRAR_VEICULO(2, "Cadastrar veiculo"),
	CADASTRAR_SEGURADORA(3, "Cadastrar seguradora"),
	LISTAR_CLIENTES(4, "Listar clientes"),
	LISTAR_SINISTROS_POR_SEGURADORA(5, "Listar sinistros por seguradora"),
	LISTAR_SINISTROS_POR_CLIENTE(6, "Listar sinistros por cliente"),
	LISTAR_VEICULO_POR_CLIENTE(7, "Listar veiculos por cliente"),
	LISTAR_VEICULO_POR_SEGURADORA(8, "Listar veiculos por seguradora"),
	EXCLUIR_CLIENTE(9, "Excluir cliente"),
	EXCLUIR_VEICULO(10, "Excluir veiculo"),
	EXCLUIR_SINISTRO(11, "Excluir sinistro");
	
	public final int operacao;
	public final String descricao;
	
	SubMenuOperacoes(int operacao, String descricao) {
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