/*
 * CLASSE RESPONSAVEL PELA MANIPULACAO E GERACAO DOS CASTELOS, E SUAS ESTRUTURAS DE DADOS
 * Basear-se em Graph
 */

import java.util.*;

public class ConqueredCastles {

	private HashMap<Integer, Castle> castlesOfKingdom; //todos os castelos ficam armazenados aqui
	private int vertex; //numero de total de castelos
	private int edges; //numero de totais arestas entre os castelos
	private int count; //contador para armazenar o maior caminho
	private List<Castle> waysOfKingdom[]; //matriz com listas com todas as estradas  

	public int V() {
		return vertex;
	}

	public int E() {
		return edges;
	}

	//valida o vertice 
	private void validateVertex(int v) {
		if (v < 0 || v >= vertex)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertex-1));
	}
	//adiciona a estrada para cada castelo dentro da lista
	public void addEdge(int start, int finish) {
		waysOfKingdom[start].add(castlesOfKingdom.get(finish));
		waysOfKingdom[finish].add(castlesOfKingdom.get(start));
	}
	//retorna a lista de adjacencia do castelo target
	public List<Castle> adj(int target) {
		return waysOfKingdom[target];
	}
	//construtor da classe recebendo um objeto da classe In como parametro
	public ConqueredCastles(In in) {
		try {
			castlesOfKingdom = new HashMap<>(); //instancia o hash onde ficarao os castelos
			int siberioArmy = in.readInt(); //o primeiro inteiro se refere ao numero de guerreiros do siberio
			this.vertex = in.readInt(); //numero de castelos
			vertex++; //soma pra contar com o castelo de siberio
			if (vertex < 0) throw new IllegalArgumentException("Números de vértices em um Graph não pode ser negativo");
			this.edges = in.readInt(); //numero de estradas entre os castelos
			if (edges < 0) throw new IllegalArgumentException("Número de arestas em um Graph não pode ser negativo");
			this.waysOfKingdom = new LinkedList[vertex]; // instancia os caminhos
			//ADIÇÃO DOS CASTELOS NO HASHMAP
			castlesOfKingdom.put(0, new Castle(0, siberioArmy)); //adiciona o castelo de siberio como o primeiro
			waysOfKingdom[0] = new LinkedList<>(); // aloca a primeira posicao de estradas sobre o castelo de siberio
			for (int i = 1; i < this.V(); i++) { 
				waysOfKingdom[i] = new LinkedList<>(); //cada posicao de estrada recebe uma lista
				castlesOfKingdom.put(i, new Castle(in.readInt(), in.readInt())); //adiciona cada posicao com o numero e cavaleiros correspondentes do arquivo
			}
			//Adição de todas as estradas
			for (int i = 0; i < this.E(); i++) {
				int v = in.readInt(); //pega o primeiro inteiro que identifica o castelo
				int w = in.readInt(); //pega o segundo inteiro que identifica o castelo
				validateVertex(v); //valida
				validateVertex(w); //valida
				addEdge(v, w); //adiciona nas listas de adjacencias
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Formato de entrada inválido no construtor do Graph: ", e); //caso nao consiga ler
		}
	}

	//verifica se o "one" consegue conquistar o "other"
	private boolean hasArmy(int one, int other) {
		Castle cob = castlesOfKingdom.get(one); //pega referencia do primeiro castelo
		Castle buckler = castlesOfKingdom.get(other); //pega referencia do segundo castelo

		int destinyConquered = cob.getRobustOfAt(); //armazena o numero de cavaleiros restantes
		int restOfTheArmy = destinyConquered - 50; //quantidade simulada para caso ganhe e ja desconte 50
		int townCenter = buckler.getArmyOfCastle(); //armazena quantidade de guerreiros do castelo que vai atacar

		int possible = (restOfTheArmy - (townCenter * 2)); //verifica se ele tem pelo menos o dobro dos cavaleiros de quem ira receber o ataque
		int armyNextAttack = possible - 50; // retira os 50 caso consiga ficar positivo

		if (armyNextAttack > 0) { //se o saldo de cavaleiros for positivo ele consegue conquistar
			buckler.setRobustOfAt(possible); //define como cavaleiros sobreviventes ja no castelo que sofreu ataque
			return true; 
		}
		return false;
	}

	//metodo que chama recursao que realiza a busca pelo maior caminho
	public int rush() {
		boolean[] steps = new boolean[vertex]; //um vetor de boolean para auxiliar as passadas entre os filhos dos castelos
		rush(steps, 0, count); //passa-se o vetor com os passos, 0 por ser o castelo do siberio, count por ser a variavel onde ficara o resultado
		return count;
	}

	//metodo recursivo 
	private void rush(boolean[] steps, int present,  int invadedLands) {
		steps[present] = true; //todo castelo que entrar ja fica marcado como passado
		for (Castle c : adj(present)) { //for each para todos os adjacentes do castelo atual
			int castleTarget = c.getIdCastle(); //pega identificador do castelo adjacentes de cada iteracao
			if (!steps[castleTarget] && hasArmy(present, castleTarget)) { //verifica se o castelo nao foi visitado e se tem exercito para conquistar
				rush(steps, castleTarget, invadedLands + 1); //caso tenha, executa o metodo recursivo novamente 
			}
		}
		//sempre que sair da iteracao ele verifica se precisa atualizar o contador global
		if(invadedLands > count){
			count = invadedLands;
		}
		steps[present] = false; //define falso o para o castelo que ja foi utilizado e nao vai mais ser neste laco
	}

}
