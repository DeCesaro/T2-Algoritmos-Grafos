/*
 * CLASSE RESPONSÁVEL PELA MANIPULAÇÃO E GERAÇÃO DOS CASTELOS, E SUAS ESTRUTURAS DE DADOS
 * Basear-se em Graph
 */

import java.util.*;

public class Reign {

	private HashMap<Integer, Castle> castles;
	private int vertex;
	private int edges;
	private int count = 0;
	private List<Castle> roadsOfCastle[];
	//private int siberioKn;

	public Reign(In in){
		try {
			castles = new HashMap<>();
			int siberioKn = in.readInt();
			this.vertex = in.readInt();
			vertex++;
			if (vertex < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
			this.edges = in.readInt();
			if (edges < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
			int desk;
			this.roadsOfCastle = new LinkedList[vertex];
			//System.out.println("Edges: "+edges);
			//System.out.println("Vertex: "+vertex);
			//ADIÇÃO DOS CASTELOS NO HASHMAP
			for (int i = 0; i < this.V(); i++){
				roadsOfCastle[i] = new LinkedList<>();
				if (i == 0){
					castles.put(i,new Castle(0,siberioKn));
				} else {
					desk = in.readInt();
					castles.put(i, new Castle(desk, in.readInt()));
				}
			}

			//AQUI IMPRIMO TODAS AS ESTRADAS
			for (int i = 0; i < this.E(); i++) {
				int v = in.readInt();
				int w = in.readInt();
				addEdge(v, w);
			}
		}
		catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor", e);
		}
	}

	public void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println("Chave no Hash é "+pairs.getKey() + ", Objeto: " + pairs.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	public void addEdge(int origin, int end) {
		roadsOfCastle[origin].add(castles.get(end));
		roadsOfCastle[end].add(castles.get(origin));
	}

	public List<Castle> adj(int target){
		return roadsOfCastle[target];
	}

	public int conquered(){

		int temp = 0;
		boolean []visited = new boolean[vertex+1];
		int desk = castles.get(0).getIdCastle();
		conquered(desk, visited, temp);

		//printMap(castles);

		return count;
	}

	private void conquered(int target, boolean[] visited,int temp){


		visited[target] = true;

		for (Castle c : adj(target)) {
			int cn = c.getIdCastle();
			if (!visited[cn] && hasEnoughKnights(target, cn)) {
				//System.out.println("asjkdsajd: "+temp);
				conquered(cn, visited, temp+1);
			}
			//System.out.println("nao conquistou temp chamado "+temp);
		}

		visited[target] = false;
		updateCastlesConquered(temp);
		//System.out.println("Ultima tentativa aaaaa");
	}

	private void updateCastlesConquered(int temp) {
		if (temp >= this.count){
			this.count = temp;
		}
	}


	private boolean hasEnoughKnights(int to, int from) {

		boolean sucessulAtack = false;

		Castle atk = castles.get(to);
		Castle def = castles.get(from);

		int toFrom = atk.getRemainingKnights();
		int toGo = def.getInitialKnights();

		int survived = toFrom - ((toGo * 2) + 50);

		//int survived = toFrom - ((toGo * 2) + 50);


		if (survived >= 0) {

			def.setRemainingKnights(survived);
			sucessulAtack = true;
		}

		return sucessulAtack;

	}

	public int V() {
		return vertex;
	}

	public int E() {
		return edges;
	}

//	private void validateVertex(int v) {
//		if (v < 0 || v >= vertex)
//			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertex-1));
//	}

	public static void main(String[] args) {
		Reign G = new Reign(new In("caso46.txt"));
		System.out.println();
		long initTime = System.currentTimeMillis();
		System.out.println("Sibério pode conquistar "+G.conquered()+" castelos");
		long endTime = System.currentTimeMillis();
		long totalTime = endTime-initTime;

		System.out.println("\nTempo total de execução: "+totalTime+" milissegundos");
		//System.out.println();
		//System.out.println("IMPRIMINDO CASTELOS NO HASH COM VALORES");
		//G.printMap(G.castles); //IMPRIMIR OS CASTELOS
		//StdOut.println(G);
		//StdOut.println();
	}
}
