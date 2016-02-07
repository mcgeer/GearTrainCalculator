package GearTrain.Model.Parser;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import GearTrain.Model.GearAlignment;
import GearTrain.Model.GearTrain;
import GearTrain.Model.NewInstance;
import GearTrain.Model.Gears.GearTrainElement;
import GearTrain.Model.Gears.SpurGear;

public enum FieldInformationValues {
	//Tag name, Field Name, Cast to function, Unbounded list of where this field exists 
	WIDTH("width", "width", Double::valueOf, GearTrainElement.class, GearTrain.class),
	LENGTH("length", "length", Double::valueOf, GearTrainElement.class, GearTrain.class),
	HEIGHT("height", "height", Double::valueOf, GearTrainElement.class, GearTrain.class),
	MAX_GEAR_SIZE("max_gear_size", "max_gear_size", Double::valueOf, GearTrain.class),
	MIN_GEAR_SIZE("min_gear_size", "min_gear_size", Double::valueOf, GearTrain.class),
	INPUT_SPEED("input_speed", "input_speed", Double::valueOf, GearTrain.class),
	MODULUS("modulus", "modulus", Double::valueOf, SpurGear.class),
	PITCH("pitch", "pitch", Double::valueOf, SpurGear.class),
	TEETH("teeth", "teeth", Integer::valueOf, SpurGear.class),
	ALIGNMENT("alignment", "alignment", GearAlignment::getAlignment, GearTrainElement.class),
	UNIT_SIZE("unit_size", "unit_size", String::valueOf, GearTrainElement.class, GearTrain.class),
	UNIT_SPEED("unit_speed", "unit_speed", String::valueOf, GearTrain.class, GearTrainElement.class);
	
	
	private String key;
	private String field;
	private List<Class<? extends NewInstance>> objs;
	private Function<String, Object> castFunction; 
	
	/**
	 * Constructor for FieldInformationValues, matches Tag name keys from xml to field name keys in project
	 * 			where fields are found in classes @param objs and cast from string to type of field 
	 * @param key is the string name of the XML attribute tag
	 * @param value	is the string name of the field paired to @param key
	 * @param castFunction is the function called to cast the attribute to the type of its
	 * 				paired field
	 * @param objs is an unbounded list of locations of the field @param value
	 */
	@SafeVarargs()
	private FieldInformationValues (String key, String value, Function<String, Object> castFunction, Class<? extends NewInstance>... objs){
		this.key = key;
		this.field = value;
		this.objs = Arrays.asList(objs);
		this.castFunction = castFunction;
	}
	
	public String getValue(){
		return this.field;
	}
	
	public String getKey(){
		return this.key;
	}
	
	/**
	 * 
	 * @param obj all objects related to the key
	 * @return Array of fields matched to key
	 */
	public static FieldInformationValues[] getValuesOfClass(Class<? extends NewInstance> obj){
		return Arrays.asList(values()).stream().filter(b -> b.objs.contains(obj)).toArray(size -> new FieldInformationValues[size]);
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
