/*
 * CLASSE RESPONSÁVEL PELA MANIPULAÇÃO E GERAÇÃO DOS CASTELOS, E SUAS ESTRUTURAS DE DADOS
 * Basear-se em Graph
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class Reign {

	private HashMap<Integer, Castle> castles;
	private int vertex;
	private int edges;
	//private int siberioKn;

	public Reign(In in){
		try {
			castles = new HashMap<>();
			int siberioKn = in.readInt();
			this.vertex = in.readInt();
			if (vertex < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
			this.edges = in.readInt();
			if (edges < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
			System.out.println("Edges: "+edges);
			System.out.println("Vertex: "+vertex);
			//ADIÇÃO DOS CASTELOS NO HASHMAP
			for (int i = 0; i < this.V()+1; i++){
				if (i == 0){
					castles.put(i,new Castle(0,siberioKn));
				} else {
					int desk = in.readInt();
					castles.put(i, new Castle(desk, in.readInt()));
				}
			}
			//AQUI IMPRIMO TODAS AS ESTRADAS
			for (int i = 0; i < this.E(); i++) {
				int v = in.readInt();
				int w = in.readInt();
				System.out.println("Roads: "+v+" "+w);
//				validateVertex(v);
//				validateVertex(w);
//				//addEdge(v, w);
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

	public int V() {
		return vertex;
	}

	public int E() {
		return edges;
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= vertex)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertex-1));
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Reign G = new Reign(in);
		System.out.println();
		System.out.println("IMPRIMINDO CASTELOS NO HASH COM VALORES");
		G.printMap(G.castles); //IMPRIMIR OS CASTELOS
		//StdOut.println(G);
		//StdOut.println();
	}
}
