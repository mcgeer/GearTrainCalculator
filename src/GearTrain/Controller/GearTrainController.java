package GearTrain.Controller;

import GearTrain.Exceptions.GearTrainNonMeshableElements;
import GearTrain.Model.GearTrain;
import GearTrain.Model.Parser.XMLParser;
import GearTrain.View.View;

public class GearTrainController {
	private View view;
	private GearTrain gear_train_model;
	
	public GearTrainController(View view, String input_file){
		this.setView(view);
		this.setGear_train_model(XMLParser.loadGearTrain(input_file));
	}

	public void testController(){
		try {
			System.out.println(gear_train_model.getOutputSpeed());
		} catch (GearTrainNonMeshableElements e) {
			e.printStackTrace();
		}
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public void setGear_train_model(GearTrain gear_train_model) {
		this.gear_train_model = gear_train_model;
	}
}
