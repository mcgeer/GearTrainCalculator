
public class GearTrainController {
	private View view;
	private GearTrain gear_train_model;
	
	public GearTrainController(View view){
		this.setView(view);
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public void setGear_train_model(GearTrain gear_train_model) {
		this.gear_train_model = gear_train_model;
	}
}
