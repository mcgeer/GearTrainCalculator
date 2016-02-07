package GearTrain.Model.Parser;
import java.security.KeyException;
import java.util.function.Function;
import org.w3c.dom.NamedNodeMap;

import GearTrain.Model.NewInstance;
import GearTrain.Model.Gears.SpurGear;

public enum GearTrainElementTags {
	SPUR_GEAR("SpurGear", SpurGear::new);
	
	private String tag;
	private Function<NamedNodeMap, ? extends NewInstance> createNewInstanceFunction; 
	
	/**
	 * Key value pairs of tag names and constructor methods
	 * @param tag XML tag as a String nested in <GearTrain>
	 * @param createNewInstanceFunction Constructor for tag @param tag
	 */
	private GearTrainElementTags (String tag, Function<NamedNodeMap, ? extends NewInstance> createNewInstanceFunction){
		this.tag = tag;
		this.createNewInstanceFunction = createNewInstanceFunction;
	}
	
	/**
	 * 
	 * @param tag XML tag as a String nested in <GearTrain> being constructed
	 * @param map of elements being added
	 * @return new GearTrain element instance
	 * @throws KeyException specified key (@param tag) does not exist
	 */
	@SuppressWarnings("unchecked")
	public static <T extends NewInstance> T createNewInstance(String tag, NamedNodeMap map) throws KeyException{
		for(GearTrainElementTags pair : values()){
			if(tag.equals(pair.tag))
				return (T) pair.createNewInstanceFunction.apply(map);
		}
		System.out.println(tag);
		throw new KeyException();
	}
	
	
}
