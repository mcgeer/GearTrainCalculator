package GearTrain.Model;
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

import GearTrain.Exceptions.GearTrainNonMeshableElements;
import GearTrain.Model.Gears.GearTrainElement;


//Model for a given GearTrain
public class GearTrain extends NewInstance {
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
	 * @param g a object that extends from GearTrainElement to be added to the end of the train
	 * @return True if element can be the next element in the train, else False
	 */
	public boolean addGearTrainElement(GearTrainElement g){
		//TODO generate conditions for false for now just append the gear
		gear_train.add(g);
		return true;
	}
	//-----------------------------Calculation Methods-----------------------------//
	/**
	 * Get the ratio of the gear train and multiply it to the input speed
	 * @return output speed of gear train
	 * @throws GearTrainNonMeshableElements, 2 elements were found not feasible to mesh
	 */
	public double getOutputSpeed() throws GearTrainNonMeshableElements{
		double ratio = 1;
		GearTrainElement p = null;
		for(GearTrainElement g : gear_train){
			ratio *= g.getRatio(p);
			p = g;
		}
		System.out.println(ratio);
		return ratio * getInputSpeed();
	}
	
	public boolean checkIsInBounds(){
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
