package GearTrain.Model;

public enum Gear_Direction {
	CC(1), CCW(-1);
	private int value;
	 
	private Gear_Direction(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}