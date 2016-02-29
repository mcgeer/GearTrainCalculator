package GearTrain.Model;
import java.lang.reflect.Field;

import org.w3c.dom.NamedNodeMap;

import GearTrain.Model.Parser.FieldInformationValues;

public abstract class NewInstance{
	/**
	 * Generate new instance of subclass through recursively gathering all field that can be set,
	 *  matching them in GearTrain.Model.Parser.FieldInformation, and assigning the values in
	 *  the attribute map, map.
	 * @param c the current class who's fields are being set
	 * @param map is an NamedNodeMap of attribute nodes parsed from an XML file describing the element
	 */
	public void newInstance(Class<? extends NewInstance> c, NamedNodeMap map){
		for (FieldInformationValues pair : FieldInformationValues.getValuesOfClass(c)) {
			try {
				//See if attribute is specified in the attribute map, if so set elements
				if(map.getNamedItem(pair.getKey()) != null){
					Field f = c.getDeclaredField((String) pair.getKey());
					f.setAccessible(true);
					//Match Types
					f.set(this, pair.cast(map.getNamedItem(pair.getKey()).getNodeValue()));
				}
			}catch( NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public abstract String elementToString();
}
