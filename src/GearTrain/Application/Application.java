package GearTrain.Application;
import GearTrain.Controller.GearTrainController;
import GearTrain.View.View;


public class Application {
	public static void main(String args[]){
		View v = new View();
		GearTrainController gtc = new GearTrainController(v);
		gtc.Run();
	}
}
