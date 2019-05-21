public class Castle {

	private int idCastle;
	private int knights;
	private boolean visited;

	public Castle(int idCastle) {
		this.idCastle = idCastle;
		this.knights = 0;
	}

	public Castle(int idCastle, int knights) {
		this.idCastle = idCastle;
		this.knights = knights;
	}

	public int getIdCastle() {
		return idCastle;
	}

	public void setIdCastle(int idCastle) {
		this.idCastle = idCastle;
	}

	public int getKnights() {
		return knights;
	}

	public void setKnights(int knights) {
		this.knights = knights;
	}

	public boolean isVisited() {
		return visited;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Castle: ");
		sb.append(getIdCastle()).append(", ");
		sb.append("Knights: ");
		sb.append(getKnights());
		return  sb.toString();
	}
}
