package GearTrain.Model.Gears;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;

import GearTrain.Exceptions.GearTrainNonMeshableElements;
import GearTrain.Model.GearAlignment;

public class SpurGear extends GearTrainElement{
	private double modulus, pitch/*TODO, addendum*/;
	private int teeth;
	
	public SpurGear(double modulus, double pitch_diameter,
			//double length, double width, double height,
			String speed_units, String dimension_units,
			GearAlignment alignment){
		
		//setDimensions(length, width, height);
		setSpeedUnits(speed_units);
		setDimensionUnits(dimension_units);
		
		setAllignment(alignment);
		
		setPitchDiameter(pitch_diameter);
		setModulus(modulus);
		
	}

	public SpurGear(NamedNodeMap map) {
		super(map);
		this.newInstance(this.getClass(), map);
	}

	
	
	public double getModulus() {
		return modulus;
	}

	public void setModulus(double modulus) {
		this.modulus = modulus;
	}

	public double getPitchDiameter() {
		return pitch;
	}

	public void setPitchDiameter(double pitch_diameter) {
		this.pitch = pitch_diameter;
	}

	public int getGearTeeth() {
		return teeth;
	}

	public void setGearTeeth(int gear_teeth) {
		this.teeth = gear_teeth;
	}


	public double getRatio(GearTrainElement previous) throws GearTrainNonMeshableElements {
		//Input Gear
		if(previous == null){
			return 1;
		}
		//Axial doesn't change ratio
		else if(this.getAllignment() == GearAlignment.AXIAL){
			return 1;
		}
		//Planar changes ratio can only be planar to spur gears
		else{
			if(previous instanceof SpurGear){
				//ratio = TeethA/TeethB if b is smaller speed increases
				if(((SpurGear) previous).getModulus() == this.getModulus())
					return ((SpurGear) previous).getGearTeeth() / this.getGearTeeth();
				else
					throw new GearTrainNonMeshableElements("Elements do not mesh in gear train. Modulus dissagreement.");
			}
		}
		//If statements are all false the remaining elements are planar to the spur gear
		//		Only spur gears mesh with spur gears thus an error is thrown
		throw new GearTrainNonMeshableElements("Elements do not mesh in gear train. Element dissagreement");
	}
}
