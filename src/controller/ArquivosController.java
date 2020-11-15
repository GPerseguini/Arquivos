package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController{

	@Override
	public void verificaDirTemp(String path) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			System.out.println("O diretório já existe");
		}else{
			dir.mkdir();
		}
		
	}

	@Override
	public boolean verificaRegistro(String path, String nome, int  cod) throws IOException {
		File dir = new File(path);
		File arq = new File(path,nome);
		boolean arqExiste = false;
		boolean codExiste = false;
		if (dir.exists() && dir.isDirectory()) {
			if (arq.exists() && arq.isFile()){
				arqExiste = true;
			    System.out.println("O arquivo existe");
			    FileInputStream fluxo = new FileInputStream(arq);
			    InputStreamReader leitor = new InputStreamReader(fluxo);
			    BufferedReader buffer = new BufferedReader(leitor);
			    String linha = buffer.readLine();
			   
			    
			    while (linha != null){
					String[] x = new String[3];	
			    	int j = 0;
			    	   for (String aux: linha.split(";")){
					    	x[j] = aux;	
					    	/*switch(j){
					    	case 0:
					    		System.out.println("\nCódigo: "+x[j]);
					    		break;
					    	case 1:
					    		System.out.println("Nome: "+x[j]);
					    		break;
					    	case 2:
					    		System.out.println("Email: "+x[j]);
					    		break;
					    	}*/
					    	j++;
					    			}
			    if (Integer.parseInt(x[0]) == cod){
			    	codExiste = true;
			    	System.out.println("Código encontrado");
			    }
					linha = buffer.readLine();
				}
			    
				buffer.close();
				leitor.close();
				fluxo.close();
			}else{
				throw new IOException("Arquivo Inválido");
			}
			
		}
		return codExiste;
		
	}

	@Override
	public void imprimeCadastro(String path, String nome, int codigo) throws IOException {
		
		if (verificaRegistro(path,nome,codigo) == false){
			System.out.println("o código não existe");
		}else{	
			File arq = new File(path,nome);
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null){
				String[] x = new String[3];	
				int j = 0;
				for (String aux: linha.split(";")){
					x[j] = aux;	
					if (Integer.parseInt(x[0]) == codigo){
					switch(j){
				    	case 0:
				    		System.out.println("\nCódigo: "+x[j]);
				    		break;
				    	case 1:
				    		System.out.println("Nome: "+x[j]);
				    		break;
				    	case 2:
				    		System.out.println("Email: "+x[j]);
				    		break;
				    		}
				    	}
					j++;
				 }
				linha = buffer.readLine();
			}
		}
	}
	@Override
	public void insereCadastro(String path,String ar, int codigo, String nome, String email) throws IOException {
	/*	StringBuffer buffer = new StringBuffer();
		String linha = " ";
	
		while (!linha.equalsIgnoreCase(null)){
			linha = codigo + ";" + nome + ";" + email;
			if (!linha.equalsIgnoreCase(null)) {
				buffer.append(linha+"\n");
			}
		}	
		*/
		if (verificaRegistro(path,ar,codigo) == true){
			System.out.println("Não foi possível adicionar, o código já existe :(");
		}else{
			File dir = new File(path);
			File arq = new File(path, ar);
			if (dir.exists() && dir.isDirectory()){
				boolean existe = false;
				if (arq.exists()){
					existe = true;
				}
				FileWriter fileWriter = new FileWriter (arq, existe);
				PrintWriter print = new PrintWriter (fileWriter);
				String conteudo =  nome ;
				print.write("\n"+codigo+";"+conteudo+";"+email);
				print.flush();
				print.close();
				fileWriter.close();
			}
		}
	}
}
