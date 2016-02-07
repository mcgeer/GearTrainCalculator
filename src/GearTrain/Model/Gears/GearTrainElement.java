package GearTrain.Model.Gears;

import org.w3c.dom.NamedNodeMap;

import GearTrain.Exceptions.GearTrainNonMeshableElements;
import GearTrain.Model.GearAlignment;
import GearTrain.Model.NewInstance;

public abstract class GearTrainElement extends NewInstance{
	
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
	private GearAlignment alignment;

	
	public GearTrainElement(){
		
	}
	
	@SuppressWarnings("unchecked")
	public void InitGearTrainElement(NamedNodeMap map, Class<? extends NewInstance> c) {
		this.newInstance((Class<? extends NewInstance>)c.getSuperclass(), map);
	}

	@SuppressWarnings("unchecked")
	public GearTrainElement(NamedNodeMap map) {
		this.newInstance((Class<? extends NewInstance>)this.getClass().getSuperclass(), map);
	}

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

	public GearAlignment getAllignment() {
		return alignment;
	}

	public void setAllignment(GearAlignment allignment) {
		this.alignment = allignment;
	}

	public abstract double getRatio(GearTrainElement previous) throws GearTrainNonMeshableElements;
}
