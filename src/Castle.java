public class Castle {

	private int idCastle;
	private int initialKnights;
	private int remainingKnights;

	public Castle(int idCastle, int initialKnights) {
		this.idCastle = idCastle;
		this.initialKnights = initialKnights;
		this.remainingKnights = initialKnights;
	}

	public int getIdCastle() {
		return idCastle;
	}


	public int getInitialKnights() {
		return initialKnights;
	}

	public void setInitialKnights(int initialKnights) {
		this.initialKnights = initialKnights;
	}

	public int getRemainingKnights() {
		return remainingKnights;
	}

	public void setRemainingKnights(int remainingKnights) {
		this.remainingKnights = remainingKnights;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Castle: ");
		sb.append(getIdCastle()).append(", ");
		sb.append("Knights: ");
		sb.append(getInitialKnights());
		return  sb.toString();
	}
}
