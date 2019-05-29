public class Castle {

	private int numCastle;
	private int initialKnights;
	private int remainingKnights;

	public Castle(int numCastle, int initialKnights) {
		this.numCastle = numCastle;
		this.initialKnights = initialKnights;
		this.remainingKnights = initialKnights;
	}

	public int getNumCastle() {
		return numCastle;
	}

	public int getInitialKnights() {
		return initialKnights;
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
		sb.append(getNumCastle()).append(", ");
		sb.append("Knights: ");
		sb.append(getInitialKnights());
		return  sb.toString();
	}
}
