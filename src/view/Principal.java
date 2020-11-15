package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {
		IArquivosController arqCont = new ArquivosController();
		String path = "/Users/gabrielperseguini/Desktop/Temp";
		String arq = "teste.csv";
		String nome = "Leandro";
		int cod = 4;
		String email="blablabla@blabla.bla";
		
		try {
			arqCont.verificaDirTemp(path);
			//arqCont.verificaRegistro(path,nome,cod);
			arqCont.imprimeCadastro(path,nome,cod);
			arqCont.insereCadastro(path,arq,cod, nome, email);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
