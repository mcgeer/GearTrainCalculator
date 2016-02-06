
public abstract class GearTrainElement {
	public enum GEAR_ALLIGNMENT {
		AXIAL, PLANAR
	};
	
	public enum GEAR_DIRECTION {
		CC(1), CCW(-1);
		private int value;
		 
		private GEAR_DIRECTION(int value) {
			this.setValue(value);
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}
	
	private double length, width, height;
	private String unit_size, unit_speed;
	private GEAR_ALLIGNMENT allignment;

	public void setDimensions(double length, double width, double height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}

	public void setDimensionUnits(String units) {
		this.unit_size = units;
	}

	public void setSpeedUnits(String units) {
		this.unit_speed = units;
	}

	public double getLength() {
		return this.length;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	public String getDimensionUnits() {
		return this.unit_size;
	}

	public String getSpeedUnits() {
		return this.unit_speed;
	}


	//public abstract <T extends GearTrainElement> double getOutputSpeed(T previous);

	public GEAR_ALLIGNMENT getAllignment() {
		return allignment;
	}

	public void setAllignment(GEAR_ALLIGNMENT allignment) {
		this.allignment = allignment;
	}
}
