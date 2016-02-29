package GearTrain.Model.Gears;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import GearTrain.Model.GearAlignment;
import GearTrain.Model.GearTrain;
import GearTrain.Model.NewInstance;
//This Enum is unused and undefined, planned use is for getters and setters to UI
public enum GearDisplayInformation {
	//Field name (key), Message to display, getter, setter. Class location
	WIDTH("width", "Width", Double::valueOf, GearTrainElement.class, GearTrain.class);
	/*LENGTH("length", "Length", Double::valueOf, GearTrainElement.class, GearTrain.class),
	HEIGHT("height", "Height", Double::valueOf, GearTrainElement.class, GearTrain.class),
	MODULUS("modulus", "Modulus", Double::valueOf, SpurGear.class),
	PITCH("pitch", "Pitch", Double::valueOf, SpurGear.class, WormGear.class, Worm.class),
	TEETH("teeth", "Teeth", Integer::valueOf, SpurGear.class, WormGear.class),
	THREAD("threads", "Threads", Integer::valueOf, Worm.class),
	ALIGNMENT("alignment", "Alignment", GearAlignment::getAlignment, GearTrainElement.class),
	//GearTrain Constants
	MAX_GEAR_SIZE("max_gear_size", "Max Gear Size", Double::valueOf, GearTrain.class),
	MIN_GEAR_SIZE("min_gear_size", "Min Gear Size", Double::valueOf, GearTrain.class),
	INPUT_SPEED("input_speed", "Input Speed", Double::valueOf, GearTrain.class),
	UNIT_SIZE("unit_size", "Unit Size", String::valueOf, GearTrain.class),
	UNIT_SPEED("unit_speed", "Unit Speed", String::valueOf, GearTrain.class);*/
	
	
	private String fieldName;
	private String message;
	private List<Class<? extends NewInstance>> objs;
	private Function<String, Object> castFunction; 
	
	@SafeVarargs()
	private GearDisplayInformation (String fieldName, String message, Function<String, Object> castFunction, Class<? extends NewInstance>... objs){
		this.fieldName = fieldName;
		this.message = message;
		this.objs = Arrays.asList(objs);
		this.castFunction = castFunction;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public String getFieldName(){
		return this.fieldName;
	}
	
	/**
	 * 
	 * @param obj all objects related to the key
	 * @return Array of fields matched to key
	 */
	public static GearDisplayInformation[] getValuesOfClass(Class<? extends NewInstance> obj){
		return Arrays.asList(values()).stream().filter(b -> b.objs.contains(obj)).toArray(size -> new GearDisplayInformation[size]);
	}
	
	/**
	 * 
	 * @param value is the value to be cast and stored in the associated field
	 * @return the cast value to be stored in the represented field
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> T cast(String value){
		return (T) castFunction.apply(value);
	}
	
	
}
