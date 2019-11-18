package com.locadoraApi.modelo;

public class Veiculo {
	
	private int id;
	
	private String renavan;
	
	private String chassi;
	
	private String descricao;
	
	private int anoFabricacao;
	
	private int anoModelo;
	
	private Cor cor;
	
	private Modelo modelo;	
	
	public Veiculo() {
		super();
	}

	public Veiculo(int id, String renavan, String chassi, String descricao, int anoFabricacao, int anoModelo, Cor cor, Modelo modelo) {
		super();
		this.id = id;
		this.renavan = renavan;
		this.chassi = chassi;
		this.descricao = descricao;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.cor = cor;
		this.modelo = modelo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public int getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	
	

}
