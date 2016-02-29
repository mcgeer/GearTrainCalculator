package GearTrain.Model.Gears;

import org.w3c.dom.NamedNodeMap;

import GearTrain.Exceptions.GearTrainNonMeshableElements;
import GearTrain.Model.GearAlignment;

public class Worm extends GearTrainElement{
	private int threads;
	private double pitch;
	public Worm(){
		this.setThreads(1);
	}
	
	/**
	 * Construct spur gear based on XML parse attribute map
	 * @param map XML parsed attribute map of a SpurGear 
	 */
	public Worm(NamedNodeMap map) {
		super(map);
		this.newInstance(this.getClass(), map);
		this.setDimensions(pitch, this.getWidth(), pitch);
	}
	
	@Override
	public double getRatio(GearTrainElement previous) throws GearTrainNonMeshableElements {
		//Input Gear
		if(previous == null){
			return 1.0;
		}
		//Axial doesn't change ratio
		else if(this.getAllignment() == GearAlignment.AXIAL){
			return 1.0;
		}
		//Planar changes ratio can only be planar to spur gears
		else{			
		//If statements are all false the remaining elements are planar, a worm always drives and must be axial
		//		Throw NonMeshable error
		throw new GearTrainNonMeshableElements("Elements do not mesh in gear train. Element dissagreement");
	
		}
	}

	public double getPitch(){
		return this.pitch;
	}
	
	public void setPitch(double pitch){
		this.pitch = pitch;
	}
	
	public int getThreads() {
		return this.threads;
	}

	
	public void setThreads(int threads) {
		this.threads = threads;
	}

}
