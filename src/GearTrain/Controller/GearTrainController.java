package GearTrain.Controller;

import org.eclipse.swt.widgets.Shell;
import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import GearTrain.Exceptions.GearTrainNonMeshableElements;
import GearTrain.Model.GearTrain;
import GearTrain.Model.Parser.XMLParser;
import GearTrain.View.View;

public class GearTrainController {
	private static View view;
	private static GearTrain gear_train_model;
	private Shell parentShell;
	private Display display;
	Field f;

	/**
	 * Create a new GearTrainController with View view and a GearTrain generated
	 * by an input xml file
	 * 
	 * @param view
	 * @param input_file
	 */
	public GearTrainController(View view) {
		this.setView(view);
		view.open();
		initViewEventHandeling();

		try {
			display = view.getDisplay();
			parentShell = view.getParentShell();
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * This function keeps the view open until the display is disposed.
	 */
	public void Run() {
		while (!parentShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	// ===============================VIEW=============================== //
	public void initViewEventHandeling() {
		//Buttons
		view.getButtonCalculateOptimalTrain().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				view.refreshView();
				System.out.println("Event: Button-> Calculate Optimal Gear Train");
			}
		});
		
		view.getButtonCalculate().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					Double outSpeed = gear_train_model.calculateOutputSpeed();
					view.getConsole().addToConsol("Output Speed of Gear Train is: " + outSpeed + gear_train_model.getUnitSpeed());
					ModelTextFieldLink.OUTPUT_SPEED.getStateAndSetText();
				} catch (GearTrainNonMeshableElements e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		// Menu -> File
		view.getMenuFileItemNew().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Event: File-> New Selected");
			}
		});

		view.getMenuFileItemOpen().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				String file = view.openFileBrowserForOpen();
				setGearTrainModel(XMLParser.loadGearTrain(file));
				if(gear_train_model != null){
					drawModelToView();
				}
				view.getConsole().addToConsol("Opened Gear Train: " +   gear_train_model.getName());
			}
		});

		view.getMenuFileItemSave().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Event: File-> Save Selected");
			}
		});

		view.getMenuFileItemSaveAs().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Event: File-> SaveAs Selected");
			}
		});
		// Menu -> Project
		view.getMenuProjectItemProperties().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Event: Project -> Properties Selected");
			}
		});

		view.getMenuProjectItemCalculate().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Event: Project -> Calculate Selected");
			}
		});
		// Menu -> Help
		view.getMenuHelpItemGeneralHelp().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Event: Help -> General Help Selected");
			}
		});

		view.getMenuHelpItemProgramHelp().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Event: Help -> Program Help Selected");
			}
		});
	}

	// ====================================Setters====================================//
	
	/**
	 * Draws All elements and attributes in the gear train to the View
	 */
	private void drawModelToView(){
		for(ModelTextFieldLink m : ModelTextFieldLink.values()){
			m.getStateAndSetText();
		}
		//TODO
		//Add something For Worm Drives
		//Add gear Train Elements here
	}
	
	public void setView(View view) {
		this.view = view;
	}

	public void setGearTrainModel(GearTrain gear_train_model) {
		this.gear_train_model = gear_train_model;
	}
	
	/**
	 * Links all Text field setters to corresponding GearTrain getters
	 */
	private enum ModelTextFieldLink {
		INPUT_SPEED			(gear_train_model::getInputSpeed, view::setInputSpeedText, String::valueOf),
		OUTPUT_SPEED		(gear_train_model::getOutputSpeed, view::setOutputSpeedText, String::valueOf),
		GEAR_BOX_DIMENSION_X(gear_train_model::getGearBoxLength, view::setBoxDimensionXText, String::valueOf),
		GEAR_BOX_DIMENSION_Y(gear_train_model::getGearBoxWidth, view::setBoxDimensionYText, String::valueOf),
		GEAR_BOX_DIMENSION_Z(gear_train_model::getGearBoxHeight, view::setBoxDimensionZText, String::valueOf),
		MAX_GEAR_SIZE		(gear_train_model::getMaxGearSize, view::setMaxGearSizeText, String::valueOf),
		MIN_GEAR_SIZE		(gear_train_model::getMin_gear_size, view::setMinGearSizeText, String::valueOf),
		GEAR_TRAIN_NAME		(gear_train_model::getName, view::setGearTrainName, String::valueOf);
		
		private Supplier<?> getter;
		private Consumer<String> setter;
		private Function<Object, String> castFunction; 
		
		private ModelTextFieldLink(Supplier<?> getter, Consumer<String> setter, Function<Object, String> castFunction){
			this.getter = getter;
			this.setter = setter;
			this.castFunction = castFunction;
		}
		
		public void getStateAndSetText(){
			setter.accept(castFunction.apply(getter.get()));
		}
	}
	
}
