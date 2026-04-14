package jogoDaVelhaPOO;

public class LinhaCreator {

	public String CriarLinha(String linha, String borda, int comprimento) {
		String linhaCriada = "";
		int cont = 0;
		for (int l = 0; l <= comprimento; l++) {
			cont++;
			if (cont <= comprimento) {
				linhaCriada += borda;
				linhaCriada += linha;
				linhaCriada += linha;
			} else {
				linhaCriada += borda;
			}
		}
		return linhaCriada;
	}

	public String CriarLinhaAjustavel(String txtLinha, String txtVisi) {
		String linhaCriada = "";
		for (int k = 0; k < txtVisi.length(); k++) {
			linhaCriada += txtLinha;
		}
		linhaCriada += "\n" + txtVisi + "\n" + linhaCriada;
		return linhaCriada;
	}
}
