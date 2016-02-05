
public class SpurGear extends GearTrainElement{
	private double modulus, pitch_diameter/*, addendum*/;
	private int gear_teeth;
	
	public SpurGear(double modulus, double pitch_diameter,
			//double length, double width, double height,
			String speed_units, String dimension_units,
			GEAR_ALLIGNMENT allignment){
		
		//setDimensions(length, width, height);
		setSpeedUnits(speed_units);
		setDimensionUnits(dimension_units);
		
		setAllignment(allignment);
		
		setPitchDiameter(pitch_diameter);
		setModulus(modulus);
	}

	public double getModulus() {
		return modulus;
	}

	public void setModulus(double modulus) {
		this.modulus = modulus;
	}

	public double getPitchDiameter() {
		return pitch_diameter;
	}

	public void setPitchDiameter(double pitch_diameter) {
		this.pitch_diameter = pitch_diameter;
	}

	public int getGearTeeth() {
		return gear_teeth;
	}

	public void setGearTeeth(int gear_teeth) {
		this.gear_teeth = gear_teeth;
	}

	
}
