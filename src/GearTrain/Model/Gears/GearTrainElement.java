package GearTrain.Model.Gears;

import org.w3c.dom.NamedNodeMap;

import GearTrain.Exceptions.GearTrainNonMeshableElements;
import GearTrain.Model.GearAlignment;
import GearTrain.Model.NewInstance;

public abstract class GearTrainElement extends NewInstance{
	
	private double length, width, height;
	private String unit_size, unit_speed;
	private GearAlignment alignment;
	
	/**
	 * General Constructor for GearTrainElement, used to set up base values
	 */
	public GearTrainElement(){
		//Arbitrary, Gears override these
		this.setDimensions(10, 1, 10);
		this.setSpeedUnits("RPM");
		this.setDimensionUnits("mm");
		this.setAllignment(GearAlignment.AXIAL);
	}

	/**
	 * Constructs a GearTrainElement from its sub class, using parsed XML file data
	 * @param map is attribute data for a given gear train element
	 */
	@SuppressWarnings("unchecked")
	public GearTrainElement(NamedNodeMap map) {
		this();
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

	public GearAlignment getAllignment() {
		return alignment;
	}

	public void setAllignment(GearAlignment allignment) {
		this.alignment = allignment;
	}
	
	/**
	 * Allows for the integration of new gear elements to be implemented into output calculations
	 * @param previous element that meshes to this
	 * @return ratio multiplier between this and previous
	 * @throws GearTrainNonMeshableElements when gear elements are non compatible in planar formation
	 * 				or modulus/other meshing factors are inconsistent 
	 */
	public abstract double getRatio(GearTrainElement previous) throws GearTrainNonMeshableElements;
}
