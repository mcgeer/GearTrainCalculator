import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

	public SpurGear() {
		// TODO Auto-generated constructor stub
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

	public void NewInstance(HashMap<String, String> mapped_gear_element) {
		Class g_class = this.getClass();
		Iterator it = mapped_gear_element.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println(pair.getKey() + "=" + pair.getValue());
			try {
				Field f = g_class.getDeclaredField((String) pair.getKey());
				//Match Types
				if(pair.getKey().equals("allignment"))
					this.setAllignment((pair.getValue().equals("axial")) ? GEAR_ALLIGNMENT.AXIAL : GEAR_ALLIGNMENT.PLANAR);
				else if(f.getType().equals(Double.class) || f.getType().equals(double.class))
					f.setDouble(this, Double.parseDouble((String) pair.getValue()));
				else
					f.set(this, (String) pair.getValue());
			}catch (NoSuchFieldException e){
				e.printStackTrace();
			}catch( SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	
}
