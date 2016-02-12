package GearTrain.Model.Gears;

import org.w3c.dom.NamedNodeMap;

import GearTrain.Exceptions.GearTrainNonMeshableElements;
import GearTrain.Model.GearAlignment;

public class WormGear extends GearTrainElement{
	private int teeth;
	private double pitch;
	
	public WormGear(){
		this.teeth = 10;
		this.pitch = 10;
	}
	
	/**
	 * Construct spur gear based on XML parse attribute map
	 * @param map XML parsed attribute map of a SpurGear 
	 */
	public WormGear(NamedNodeMap map) {
		super(map);
		this.newInstance(this.getClass(), map);
		this.setDimensions(pitch, this.getWidth(), pitch);
	}
	
	@Override
	public double getRatio(GearTrainElement previous) throws GearTrainNonMeshableElements {
		//Input Gear
		if(previous == null){
			return 1;
		}
		//Axial doesn't change ratio, this cannot drive an element however
		else if(this.getAllignment() == GearAlignment.AXIAL){
			return 1;
		}
		//Planar changes ratio can only be planar to worms
		else{
			if(previous instanceof Worm){
				return (double)((Worm) previous).getThreads() / (double) this.getTeeth();
			}
		//If statements are all false the remaining elements are planar, a worm always drives and must be axial
		//		Throw NonMeshable error
		throw new GearTrainNonMeshableElements("Elements do not mesh in gear train. Element dissagreement");
	
		}
	}



	public int getTeeth() {
		return teeth;
	}



	public void setTeeth(int teeth) {
		this.teeth = teeth;
	}



	public double getPitch() {
		return pitch;
	}



	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

}
