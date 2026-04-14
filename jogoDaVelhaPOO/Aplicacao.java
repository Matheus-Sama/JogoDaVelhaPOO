package jogoDaVelhaPOO;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Aplicacao {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		List<Jogador> jogadores = new ArrayList<Jogador>();

		LinhaCreator linhaVisual = new LinhaCreator();
		Jogador jogador;

		String nome;
		String simbolo;
		String txtTemp = "";
		String linha = "";
		String opcao;

		boolean controle = true;
		boolean maquina;

		int tamanho = 3;
		JogoDaVelha game = new JogoDaVelha(tamanho, jogadores);
		while (controle) {
			game.criarTabuleiro();
			txtTemp = "JOGO DA VELHA";
			linha = linhaVisual.CriarLinhaAjustavel("=", txtTemp);
			System.out.println(linha);
			System.out.println("1- PARTIDA PADRÃO");
			System.out.println("2- PARTIDA PERSONALIZADA");
			System.out.println("3- ENCERRAR");
			opcao = scan.nextLine();
			if (!(opcao.equals("1") || opcao.equals("2") || opcao.equals("3"))) {
				txtTemp = "OPÇÃO INVÁLIDA";
				linha = linhaVisual.CriarLinhaAjustavel("x", txtTemp);
				System.out.println(linha);
			} else {
				switch (opcao) {
				case "1":
					while (true) {
						System.out.println("Deseja jogar contra a máquina?");
						System.out.println("1- Sim");
						System.out.println("2- Não");
						opcao = scan.nextLine();
						if (!(opcao.equals("1") || opcao.equals("2"))) {
							txtTemp = "OPÇÃO INVÁLIDA";
							linha = linhaVisual.CriarLinhaAjustavel("x", txtTemp);
							System.out.println(linha);

						} else {
							if (opcao.equals("1")) {
								maquina = true;
								break;
							} else {
								maquina = false;
								break;
							}
						}
					}
					nome = verificarEspacos(scan, "Digite o seu nome:");
					while (true) {
						System.out.println("Escolha o simbolo:");
						System.out.println("1- X");
						System.out.println("2- O");
						opcao = scan.nextLine();
						if (!(opcao.equals("1") || opcao.equals("2"))) {
							txtTemp = "OPÇÃO INVÁLIDA";
							linha = linhaVisual.CriarLinhaAjustavel("x", txtTemp);
							System.out.println(linha);

						} else {
							if (maquina) {
								if (opcao.equals("1")) {
									jogador = new Human(nome, "X");
									jogadores.add(jogador);
									jogador = new Maquina("Máquina", "O");
									jogadores.add(jogador);
								} else {
									jogador = new Human(nome, "O");
									jogadores.add(jogador);
									jogador = new Maquina("Máquina", "X");
									jogadores.add(jogador);
								}
							} else {
								if (opcao.equals("1")) {
									jogador = new Human(nome, "X");
									jogadores.add(jogador);
									nome = verificarEspacos(scan, "Digite o nome:");
									jogador = new Human(nome, "O");
									jogadores.add(jogador);
								} else {
									jogador = new Human(nome, "O");
									jogadores.add(jogador);
									nome = verificarEspacos(scan, "Digite o nome:");
									jogador = new Human(nome, "X");
									jogadores.add(jogador);
								}
							}
							break;
						}
					}
					game.rodarJogo();
					break;
				case "2":
					while (true) {
						System.out.println("Deseja jogar contra a máquina?");
						System.out.println("1- Sim");
						System.out.println("2- Não");
						opcao = scan.nextLine();
						if (!(opcao.equals("1") || opcao.equals("2"))) {
							txtTemp = "OPÇÃO INVÁLIDA";
							linha = linhaVisual.CriarLinhaAjustavel("x", txtTemp);
							System.out.println(linha);

						}
						if (opcao.equals("1")) {
							maquina = true;
							break;
						} else {
							maquina = false;
							break;
						}
					}
					nome = verificarEspacos(scan, "Digite o nome:");
					simbolo = verificarEspacos(scan, "Digite o simbolo: ");
					jogador = new Human(nome, simbolo);
					jogadores.add(jogador);
					if (maquina) {
						while (true) {
							simbolo = verificarEspacos(scan, "Digite o simbolo da máquina:");
							if (simbolo.equals(jogador.getSimbolo())) {
								System.out.println("Simbolos iguais! Tente novamente");
							} else {
								break;
							}
						}
						jogador = new Maquina("Máquina", simbolo);
						jogadores.add(jogador);
					} else {
						nome = verificarEspacos(scan, "Digite o nome:");
						simbolo = verificarEspacos(scan, "Digite o simbolo: ");
						jogador = new Human(nome, simbolo);
						jogadores.add(jogador);
					}
					game.rodarJogo();
					break;
				case "3":
					controle = false;
					break;
				}
			}

		}
	}

	private static String verificarEspacos(Scanner scan, String texto) {
		while (true) {
			System.out.println(texto);
			String txt = scan.nextLine();
			if (!txt.isBlank()) {
				return txt;
			}
			System.out.println("Valor inválido!");
		}
	}

}
