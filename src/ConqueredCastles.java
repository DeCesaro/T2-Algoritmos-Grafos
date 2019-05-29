/*
 * CLASSE RESPONSÁVEL PELA MANIPULAÇÃO E GERAÇÃO DOS CASTELOS, E SUAS ESTRUTURAS DE DADOS
 * Basear-se em Graph
 */

import java.util.*;

public class ConqueredCastles {

	private HashMap<Integer, Castle> castlesOfKingdom;
	private int vertex;
	private int edges;
	private int count;
	private List<Castle> waysOfKingdom[];

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

	public void addEdge(int start, int finish) {
		waysOfKingdom[start].add(castlesOfKingdom.get(finish));
		waysOfKingdom[finish].add(castlesOfKingdom.get(start));
	}

	public List<Castle> adj(int target) {
		return waysOfKingdom[target];
	}

	public ConqueredCastles(In in) {
		try {
			castlesOfKingdom = new HashMap<>();
			int siberioArmy = in.readInt();
			this.vertex = in.readInt();
			vertex++;
			if (vertex < 0) throw new IllegalArgumentException("Números de vértices em um Graph não pode ser negativo");
			this.edges = in.readInt();
			if (edges < 0) throw new IllegalArgumentException("Número de arestas em um Graph não pode ser negativo");
			this.waysOfKingdom = new LinkedList[vertex];
			//ADIÇÃO DOS CASTELOS NO HASHMAP
			castlesOfKingdom.put(0, new Castle(0, siberioArmy));
			waysOfKingdom[0] = new LinkedList<>();
			for (int i = 1; i < this.V(); i++) {
				waysOfKingdom[i] = new LinkedList<>();
				castlesOfKingdom.put(i, new Castle(in.readInt(), in.readInt()));
			}
			//Adição de todas as estradas
			for (int i = 0; i < this.E(); i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("Formato de entrada inválido no construtor do Graph", e);
		}
	}

	private boolean hasArmy(int one, int other) {
		Castle attack = castlesOfKingdom.get(one);
		Castle defense = castlesOfKingdom.get(other);
		int destinyConquered = attack.getRemainingKnights();
		int townCenter = defense.getInitialKnights();
		int survived = destinyConquered - ((townCenter * 2) + 50);
		if (survived > 0) {
			defense.setRemainingKnights(survived);
			return true;
		}
		return false;
	}

	public int rush() {
		boolean[] steps = new boolean[vertex+1];
		rush(steps, 0, count);
		return count;
	}

	private void rush(boolean[] steps, int present,  int invadedLands) {
		steps[present] = true;
		for (Castle c : adj(present)) {
			int castleTarget = c.getNumCastle();
			if (!steps[castleTarget] && hasArmy(present, castleTarget)) {
				rush(steps, castleTarget, invadedLands + 1);
			}
		}
		updateKingdom(invadedLands);
		steps[present] = false;
	}

	private void updateKingdom(int temp) {
		if (temp > count) {
			count = temp;
		}
	}
}
