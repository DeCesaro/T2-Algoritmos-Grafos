/*
 * CLASSE RESPONSÁVEL PELA MANIPULAÇÃO E GERAÇÃO DOS CASTELOS, E SUAS ESTRUTURAS DE DADOS
 * Basear-se em Graph
 */

import java.util.*;

public class ConqueredCastles {

	private HashMap<Integer, Castle> castles;
	private int vertex;
	private int edges;
	private int count = 0;
	private List<Castle> routeCastle[];

	public int V() {
		return vertex;
	}

	public int E() {
		return edges;
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= vertex)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + vertex);
	}

	public void addEdge(int start, int finish) {
		routeCastle[start].add(castles.get(finish));
		routeCastle[finish].add(castles.get(start));
	}

	public List<Castle> adj(int target) {
		return routeCastle[target];
	}

	public ConqueredCastles(In in) {
		try {
			castles = new HashMap<>();
			int siberioArmy = in.readInt();
			this.vertex = in.readInt();
			vertex++;
			if (vertex < 0) throw new IllegalArgumentException("Números de vértices em um Graph não pode ser negativo");
			this.edges = in.readInt();
			if (edges < 0) throw new IllegalArgumentException("Número de arestas em um Graph não pode ser negativo");
			int wololo;
			this.routeCastle = new LinkedList[vertex];

			//ADIÇÃO DOS CASTELOS NO HASHMAP
			for (int i = 0; i < this.V(); i++) {
				routeCastle[i] = new LinkedList<>();
				if (i == 0) {
					castles.put(i, new Castle(0, siberioArmy));
				} else {
					wololo = in.readInt();
					castles.put(i, new Castle(wololo, in.readInt()));
				}
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

	private boolean hasArmy(int dest, int initt) {

		boolean AttackConfirmed = false;

		Castle attack = castles.get(dest);
		Castle defense = castles.get(initt);

		int destinyConquered = attack.getRemainingKnights();
		int townCenter = defense.getInitialKnights();

		int survived = destinyConquered - ((townCenter * 2) + 50);

		if (survived >= 0) {

			defense.setRemainingKnights(survived);
			AttackConfirmed = true;
		}

		return AttackConfirmed;

	}

	public int conquered() {

		int dominating = 0;
		boolean[] visited = new boolean[vertex + 1];
		int wololo = castles.get(0).getNumCastle();
		conquered(wololo, visited, dominating);

		return count;
	}

	private void conquered(int enemyCastle, boolean[] visited, int dominating) {

		visited[enemyCastle] = true;

		for (Castle c : adj(enemyCastle)) {
			int castleTarget = c.getNumCastle();
			if (!visited[castleTarget] && hasArmy(enemyCastle, castleTarget)) {
				conquered(castleTarget, visited, dominating + 1);
			}
		}

		visited[enemyCastle] = false;
		updateReign(dominating);
	}

	private void updateReign(int temp) {
		if (temp >= this.count) {
			this.count = temp;
		}
	}
}
