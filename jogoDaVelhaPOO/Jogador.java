package jogoDaVelhaPOO;

public abstract class Jogador {
	private String nome;
	private String simbolo;
	private String jogada;
	private int vitorias;
	private int empates;
	public Jogador(String nome, String simbolo) {
		this.nome = nome;
		this.simbolo = simbolo;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias += vitorias;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates += empates;
	}

	public void setJogada(String jogada) {
		this.jogada = jogada;
	}
	public String getJogada() {
		return this.jogada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public String getSimbolo() {

		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;

	}
}
