package GearTrain.Model;
import java.lang.reflect.Field;
import org.w3c.dom.NamedNodeMap;

import GearTrain.Model.Parser.FieldInformationValues;

public abstract class NewInstance{
	public void newInstance(Class<? extends NewInstance> c, NamedNodeMap map){
		for (FieldInformationValues pair : FieldInformationValues.getValuesOfClass(c)) {
			System.out.println(pair.getKey());
			try {
				Field f = c.getDeclaredField((String) pair.getKey());
				f.setAccessible(true);
				//Match Types
				f.set(this, pair.cast(map.getNamedItem(pair.getKey()).getNodeValue()));
				
			}catch (NoSuchFieldException e){
				
			}catch( SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
