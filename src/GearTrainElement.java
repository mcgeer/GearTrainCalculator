
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
	private String dimension_units, speed_units;
	private GEAR_ALLIGNMENT allignment;

	public void setDimensions(double length, double width, double height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}

	public void setDimensionUnits(String units) {
		this.dimension_units = units;
	}

	public void setSpeedUnits(String units) {
		this.speed_units = units;
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
		return this.dimension_units;
	}

	public String getSpeedUnits() {
		return this.speed_units;
	}


	//public abstract <T extends GearTrainElement> double getOutputSpeed(T previous);

	public GEAR_ALLIGNMENT getAllignment() {
		return allignment;
	}

	public void setAllignment(GEAR_ALLIGNMENT allignment) {
		this.allignment = allignment;
	}
}
