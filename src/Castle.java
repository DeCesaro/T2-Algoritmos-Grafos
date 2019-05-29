public class Castle {

	private int idCastle; //identificador de um castelo
	private int armyOfCastle; //exercito no começo
	private int robustOfAt; //exercito que sobra a cada ataque

	public Castle(int idCastle, int armyOfCastle) {
		this.idCastle = idCastle;
		this.armyOfCastle = armyOfCastle;
		this.robustOfAt = armyOfCastle;
	}

	public int getIdCastle() {
		return idCastle;
	}

	public int getArmyOfCastle() {
		return armyOfCastle;
	}

	public int getRobustOfAt() {
		return robustOfAt;
	}

	public void setRobustOfAt(int robustOfAt) {
		this.robustOfAt = robustOfAt;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Castle: ");
		sb.append(getIdCastle()).append(", ");
		sb.append("Knights: ");
		sb.append(getArmyOfCastle());
		return  sb.toString();
	}
}
