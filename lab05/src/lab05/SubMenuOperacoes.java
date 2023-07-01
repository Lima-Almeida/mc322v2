package lab05;

public enum SubMenuOperacoes {
	VOLTAR(0, "voltar"),
	CADASTRAR_CLIENTE(1, "Cadastrar cliente"),
	CADASTRAR_VEICULO(2, "Cadastrar veiculo"),
	CADASTRAR_SEGURADORA(3, "Cadastrar seguradora"),
	CADASTRAR_CONDUTOR(4, "Cadastrar condutor"),
	CADASTRAR_SEGURO(5, "Cadastrar seguro"),
	LISTAR_CLIENTES(6, "Listar clientes"),
	LISTAR_SINISTROS_POR_SEGURADORA(7, "Listar sinistros por seguradora"),
	LISTAR_SINISTROS_POR_CLIENTE(8, "Listar sinistros por cliente"),
	LISTAR_VEICULO_POR_CLIENTE(9, "Listar veiculos por cliente"),
	LISTAR_VEICULO_POR_SEGURADORA(10, "Listar veiculos por seguradora"),
	EXCLUIR_CLIENTE(11, "Excluir cliente"),
	EXCLUIR_VEICULO(12, "Excluir veiculo"),
	EXCLUIR_SINISTRO(13, "Excluir sinistro");
	
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