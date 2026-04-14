package jogoDaVelhaPOO;

import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class JogoDaVelha {

	private List<Jogador> jogadores;
	private int tamanho;
	private String[][] tabuleiro;

	public JogoDaVelha(int tamanho, List<Jogador> jogadores) {
		this.tamanho = tamanho;
		this.jogadores = jogadores;
		this.tabuleiro = new String[tamanho][tamanho];
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public void criarTabuleiro() {// Metodo que preenche a matriz com números de 1 ao length convertidos para
									// string
		int cont = 0;
		for (int l = 0; l < tamanho; l++) {
			for (int k = 0; k < tamanho; k++) {
				cont++;
				tabuleiro[l][k] = cont + "";
			}
		}
	}

	public String exibirTabuleiro(boolean exibirN) {// Metodo que cria visualmente o tabuleiro e retorna uma string
		boolean achou = false;
		String tabuleiroFormatado = "";
		LinhaCreator linhaCreator = new LinhaCreator();
		String linha = linhaCreator.CriarLinha("-", "+", tamanho);
		int contVetor = 0;
		for (int l = 0; l < tamanho; l++) {
			int cont = 0;
			for (int k = 0; k < tamanho; k++) {
				cont++;
				contVetor++;
				if (cont < tamanho) {
					if (exibirN) {
						if (contVetor < 10) {
							tabuleiroFormatado += "| " + tabuleiro[l][k];
						} else {
							tabuleiroFormatado += "|" + tabuleiro[l][k];
						}
					} else {
						for (Jogador j : jogadores) {
							if ((tabuleiro[l][k].equals(j.getSimbolo()))) {
								achou = true;
								break;
							} else {
								achou = false;
							}
						}
						if (achou) {

							tabuleiroFormatado += "| " + tabuleiro[l][k];

						} else {
							if (contVetor < 10) {
								tabuleiroFormatado += "| " + " ";
							} else {
								tabuleiroFormatado += "|" + "  ";
							}
						}

					}

				} else {
					if (exibirN) {
						if (contVetor < 10) {
							tabuleiroFormatado += "| " + tabuleiro[l][k] + "|";
						} else {
							tabuleiroFormatado += "|" + tabuleiro[l][k] + "|";
						}
					} else {
						for (Jogador j : jogadores) {
							if ((tabuleiro[l][k].equals(j.getSimbolo()))) {
								achou = true;
								break;
							} else {
								achou = false;
							}
						}
						if (achou) {
							if (contVetor < 10) {
								tabuleiroFormatado += "| " + tabuleiro[l][k] + "|";
							} else {
								tabuleiroFormatado += "| " + tabuleiro[l][k] + "|";
							}
						} else {
							if (contVetor < 10) {
								tabuleiroFormatado += "| " + " " + "|";
							} else {
								tabuleiroFormatado += "|" + "  " + "|";
							}
						}

					}
				}
			}
			if (contVetor < tamanho * tamanho) {
				tabuleiroFormatado += "\n";
				tabuleiroFormatado += linha;
				tabuleiroFormatado += "\n";
			}
		}
		return tabuleiroFormatado;
	}

	public boolean validarJogada(String jogada) {// Metodo que valida a jogada feita pelo jogador, retornando true caso
													// seja uma jogada valida e falso caso o contrario
		int cont = 0;
		Jogador jogador;
		for (int k = 0; k < jogadores.size(); k++) {
			jogador = jogadores.get(k);
			for (int m = 0; m < tabuleiro.length; m++) {
				for (int n = 0; n < tabuleiro.length; n++) {
					if (tabuleiro[m][n].equals(jogada) && jogada.equals(jogador.getJogada())) {
						tabuleiro[m][n] = jogador.getSimbolo();
						cont++;
					}
				}
			}
		}
		if (cont > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int validarEstado() {// verifica vitoria e empate, retorna um valor inteiro dependendo do resultado
		int temp = 0; // (0, sem resultado, 1 vitoria e 2 empate)
		int contV;
		int contT = 0;
		for (int o = 0; o < jogadores.size(); o++) {// For principal, gera o loop para cada jogador
			for (int k = 0; k < tabuleiro.length; k++) {// loop para verificar vitoria nas linhas
				contV = 0;
				for (int l = 0; l < tabuleiro.length; l++) {
					if (tabuleiro[k][l].equals(jogadores.get(o).getSimbolo())) {
						contV++;
					} else {
						contV = 0;
					}
					if (contV == tabuleiro.length) {
						return 1;
					}
				}
			}
			for (int k = 0; k < tabuleiro.length; k++) {// loop para verificar vitoria nas colunas
				contV = 0;
				for (int l = 0; l < tabuleiro.length; l++) {
					if (tabuleiro[l][k].equals(jogadores.get(o).getSimbolo())) {
						contV++;
					} else {
						contV = 0;
					}
					if (contV == tabuleiro.length) {
						return 1;
					}
				}
			}
			contV = 0;
			for (int k = 0; k < tabuleiro.length; k++) {// loop para verificar vitoria na diagonal
				if (tabuleiro[k][k].equals(jogadores.get(o).getSimbolo())) {
					contV++;
				} else {
					contV = 0;
				}
				if (contV == tabuleiro.length) {
					return 1;
				}
			}
			temp = 0;
			contV = 0;
			for (int k = tabuleiro.length - 1; k >= 0; k--) {// loop para verificar vitoria na transversal
				if (tabuleiro[k][temp].equals(jogadores.get(o).getSimbolo())) {
					contV++;
				} else {
					contV = 0;
				}
				if (contV == tabuleiro.length) {
					return 1;
				}
				temp++;
			}
		}
		for (int k = 0; k < tabuleiro.length; k++) {// loop para verificar empate
			for (int j = 0; j < tabuleiro.length; j++) {
				if (tabuleiro[k][j].equals(jogadores.getFirst().getSimbolo())
						|| tabuleiro[k][j].equals(jogadores.getLast().getSimbolo())) {
					contT++;
				} else {
					contT = 0;
				}
				if (contT == tabuleiro.length * tabuleiro.length) {
					return 2;
				}
			}
		}
		return 0;
	}

	public void rodarJogo() { // Metodo que gerencia o fluxo do jogo, chamando os outros métodos para
								// controle, validação e criação
		Scanner scan = new Scanner(System.in);
		Random rando = new Random();
		int jogada;
		boolean partida = true;
		boolean jogadaValida;
		Jogador jogador;
		String opcao;
		boolean exibirN = true;
		while (partida) {
			for (int k = 0; k < jogadores.size(); k++) {

				jogador = jogadores.get(k);
				if (exibirN) {
					System.out.println(exibirTabuleiro(true));
					exibirN = false;
				} else {
					System.out.println(exibirTabuleiro(false));
				}

				do {
					if (jogador.getNome().equals("Máquina")) {
						jogada = rando.nextInt(1, tamanho * tamanho + 1);
						jogador.setJogada(jogada + "");
					} else {
						System.out.println("Faça sua jogada, " + jogador.getNome() + ": ");
						jogador.setJogada(scan.nextLine());
					}
					jogadaValida = validarJogada(jogador.getJogada());
				} while (!jogadaValida);

				if (validarEstado() == 1) {
					exibirN = true;
					System.out.println("O jogador " + jogador.getNome() + " venceu!");
					jogadores.get(k).setVitorias(1);
					System.out.println(exibirTabuleiro(false));

					while (true) {
						System.out.println("Deseja jogar novamente?");
						System.out.println("1- Sim");
						System.out.println("2- Não");
						opcao = scan.nextLine();
						if (!(opcao.equals("1") || opcao.equals("2"))) {
							System.out.println("Opção inválida!");

						} else {
							if (opcao.equals("1")) {
								criarTabuleiro();
								break;
							} else {
								partida = false;
								for (Jogador j : jogadores) {
									System.out.println("Vitórias de " + j.getNome() + ": " + j.getVitorias());
									System.out.println("Empates de " + j.getNome() + ": " + j.getEmpates());

								}
								jogadores.clear();
								break;
							}
						}
					}
					break;
				} else if (validarEstado() == 2) {
					exibirN = true;
					System.out.println("Empate!");
					jogadores.forEach(j -> j.setEmpates(1));
					System.out.println(exibirTabuleiro(true));

					while (true) {
						System.out.println("Deseja jogar novamente?");
						System.out.println("1- Sim");
						System.out.println("2- Não");
						opcao = scan.nextLine();
						if (!(opcao.equals("1") || opcao.equals("2"))) {
							System.out.println("Opção inválida!");

						} else {
							if (opcao.equals("1")) {
								criarTabuleiro();
								break;
							} else {
								partida = false;
								for (Jogador j : jogadores) {
									System.out.println("Vitórias de " + j.getNome() + ": " + j.getVitorias());
									System.out.println("Empates de " + j.getNome() + ": " + j.getEmpates());
								}
								jogadores.clear();
								break;
							}
						}
					}
					break;
				}
				System.out.println("Jogada de " + jogador.getNome());
			}
		}
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
}