import javax.swing.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {


		//vetor com os casos de testes possíveis
		Object[] options = { "caso30.txt", "caso32.txt", "caso34.txt","caso36.txt",
			"caso38.txt", "caso40.txt","caso42.txt","caso44.txt",
				"caso46.txt","caso48.txt", "caso50.txt", "caso60.txt"};
		String response = "./casosTestes/";

		//uma interface bem simples para o usuário escolher qual caso de teste deseja selecionar
		//tanto as opções no vetor como a interface, não permitem o usuário "burlar" a escolha.
		do {
			response += (String)  JOptionPane.showInputDialog(null,
					"Qual caso de teste deseja utilizar ?",
					"Pergunta",
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,
					"não");
		} while (response.equals("") || response.equals("não"));

	//	System.out.println(response);
		Reign reign = new Reign( new In(response));


		System.out.println();
		long initTime = System.currentTimeMillis();
		System.out.println("Sibério pode conquistar "+reign.conquered()+" castelos");
		long endTime = System.currentTimeMillis();
		long finalTime = endTime-initTime;
		long segundos = ( finalTime / 1000 ) % 60;      // se não precisar de segundos, basta remover esta linha.
		long minutos  = ( finalTime / 60000 ) % 60;     // 60000   = 60 * 1000
		//int horas    = ms / 3600000;            // 3600000 = 60 * 60 * 1000
		System.out.println();
		System.out.println( String.format( "%02d minutos, %02d segundos e %2d milissegundos", minutos, segundos, finalTime ) );
		//System.out.println("\nTempo total de execução: "+()+" milissegundos");
		
	}
}