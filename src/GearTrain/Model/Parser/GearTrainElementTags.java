package GearTrain.Model.Parser;
import java.security.KeyException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import org.w3c.dom.NamedNodeMap;

import GearTrain.Model.NewInstance;
import GearTrain.Model.Gears.SpurGear;

public enum GearTrainElementTags {
	SPUR_GEAR("SpurGear", SpurGear::new);
	
	private String tag;
	private Function<NamedNodeMap, ? extends NewInstance> createNewInstanceFunction; 
	
	private GearTrainElementTags (String tag, Function<NamedNodeMap, ? extends NewInstance> createNewInstanceFunction){
		this.tag = tag;
		this.createNewInstanceFunction = createNewInstanceFunction;
	}
	
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
