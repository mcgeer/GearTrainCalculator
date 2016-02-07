package GearTrain.Model.Parser;
import java.io.File;
import java.io.IOException;
import java.security.KeyException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import GearTrain.Model.GearTrain;

public class XMLParser {
	
	public static GearTrain loadGearTrain(String input_file){
		GearTrain gt = new GearTrain();
		try {
			File file = new File(input_file);
			DocumentBuilderFactory doc_builder_factory = DocumentBuilderFactory.newInstance(); //Throws ParserConfigurationException
			DocumentBuilder doc_builder = doc_builder_factory.newDocumentBuilder();
			Document doc = doc_builder.parse(file);		//Throws IOException and SAXException
			
			Node gear_train_node = doc.getFirstChild();
			gt.newInstance(gt.getClass(), gear_train_node.getAttributes());
		
			NodeList gear_train_elements = gear_train_node.getChildNodes();
			
			for(int i = 0; i < gear_train_elements.getLength(); i++){
				Node current_node = gear_train_elements.item(i);
				NamedNodeMap attributes= current_node.getAttributes();
				if(!(current_node instanceof Text)){
					try{
						gt.addGearTrainElement(GearTrainElementTags.createNewInstance(current_node.getNodeName(), current_node.getAttributes()));
					} catch(KeyException e){
						e.printStackTrace();
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return gt;
	}	
}
