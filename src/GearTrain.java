import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


//Model for a given GearTrain
public class GearTrain {
	private List<GearTrainElement> gear_train = new ArrayList<>();
	private double input_speed = 0;
	private double height, length, width;
	private String unit_size, unit_speed, name;
	
	/**
	 * The default GearTrain Constructor creates an empty GearTrain, it is recommended that
	 * 		The NewInstance Method is called immediately
	 */
	public GearTrain() {
		setGearBoxHeight(Double.POSITIVE_INFINITY);
		setGearBoxLength(Double.POSITIVE_INFINITY);
		setGearBoxWidth(Double.POSITIVE_INFINITY);
		setInputSpeed(1.0);
		setName("GearTrain");
		setUnitSize("mm");
		setUnitSpeed("RPM");
	}
	
	/**
	 * The default GearTrain Constructor is called then a new GearTrain is created based on the
	 * 			input xml file
	 */
	public GearTrain(String input_file){
		this();
		this.loadGearTrain(input_file);
	}
	
	public void loadGearTrain(String input_file){
		
		try {
			File file = new File(input_file);
			DocumentBuilderFactory doc_builder_factory = DocumentBuilderFactory.newInstance(); //Throws ParserConfigurationException
			DocumentBuilder doc_builder = doc_builder_factory.newDocumentBuilder();
			Document doc = doc_builder.parse(file);		//Throws IOException and SAXException
			
			Node gear_train_node = doc.getFirstChild();			//<GearTrain>
			NamedNodeMap gear_train_attr = gear_train_node.getAttributes();
			HashMap<String, String> gear_train_mapped_attr = new HashMap<>();
			
			//Get all key value pairs for attributes of the gear train
			for(int i = 0; i < gear_train_attr.getLength(); i++){
				gear_train_mapped_attr.put(gear_train_attr.item(i).getNodeName(), gear_train_attr.item(i).getNodeValue());
			}
			//Calibrate the GearTrain to fit the attributes
			this.NewInstance(gear_train_mapped_attr);
			
			//Add all GearTrainElements to the gear_train
			//NodeList gear_train_element_nodes = gear_train_node.getChildNodes();
			NodeList gear_train_element_nodes = doc.getElementsByTagName("*");
			for(int i = 1; i < gear_train_element_nodes.getLength(); i++){
				Node current_node = gear_train_element_nodes.item(i);
				
				NamedNodeMap gear_element_attr = current_node.getAttributes();
				HashMap<String, String> gear_element_mapped_attr = new HashMap<>();
				if(current_node.getNodeType() != Node.ELEMENT_NODE)
					continue;
				//For all elements check they are truly attributes then add attributes to Key value pairing
				for(int j = 0; j < gear_element_attr.getLength(); j++){
					if(gear_element_attr.item(j).getNodeType() != Node.ATTRIBUTE_NODE)
						continue;
					gear_element_mapped_attr.put(gear_element_attr.item(j).getNodeName(), gear_element_attr.item(j).getNodeValue());
				}
				
				switch(current_node.getNodeName()){
					case "SpurGear":
						SpurGear g = new SpurGear();
						g.NewInstance(gear_element_mapped_attr);
						gear_train.add(g);
						break;
					case "WormGear":
						//TODO add Worm Gear Code
						break;
					case "Worm":
						//TODO add WORM code
						break;
					default:
						//TODO Throw Exception InvalidGearTrain
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		System.out.println(gear_train.size());
	}
	
	/**
	 * 
	 * @param mapped_gear_train A HashMap<K,V> of Strings where K is an xml attribute and V is its value
	 * This file takes mapped_gear_train and matches the attributes to fields in the GearTrain Class, setting them
	 * 		to the attribute values.  This is achieved through reflection.
	 */
	public void NewInstance(HashMap<String, String> mapped_gear_train){
		Class gt_class = this.getClass();
		Iterator it = mapped_gear_train.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			try {
				Field f = gt_class.getDeclaredField((String) pair.getKey());
				//Match Types
				if(f.getType().equals(Double.class) || f.getType().equals(double.class))
					f.setDouble(this, Double.parseDouble((String) pair.getValue()));
				else
					f.set(this, (String) pair.getValue());
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @param g a object that extends from GearTrainElement to be added to the end of the train
	 * @return True if element can be the next element in the train, else False
	 */
	public boolean addGearTrainElement(GearTrainElement g){
		//TODO generate conditions for false for now just append the gear
		gear_train.add(g);
		return true;
	}
	//-----------------------------Calculation Methods-----------------------------//
	public double getOutputSpeed(){
		//TODO generate code to get output speed based on looping through gearTrain elements
		return 0.0;
	}
	
	public boolean checkIsInBounds(){
		//TODO code to check GearBox bounds
		return true;
	}
	
	//-----------------------------Getters and Setters-----------------------------//
	
	public double getInputSpeed() {
		return input_speed;
	}

	public void setInputSpeed(double input_speed) {
		this.input_speed = input_speed;
	}

	public double getGearBoxHeight() {
		return height;
	}

	public void setGearBoxHeight(double gear_box_height) {
		this.height = gear_box_height;
	}

	public double getGearBoxLength() {
		return length;
	}

	public void setGearBoxLength(double gear_box_length) {
		this.length = gear_box_length;
	}

	public double getGearBoxWidth() {
		return width;
	}

	public void setGearBoxWidth(double gear_box_width) {
		this.width = gear_box_width;
	}

	public String getUnitSize() {
		return unit_size;
	}

	public void setUnitSize(String unit_size) {
		this.unit_size = unit_size;
	}

	public String getUnitSpeed() {
		return unit_speed;
	}

	public void setUnitSpeed(String unit_speed) {
		this.unit_speed = unit_speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
