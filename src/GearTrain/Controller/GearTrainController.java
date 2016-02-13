package GearTrain.Controller;

import org.eclipse.swt.widgets.Shell;
import java.lang.reflect.Field;

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
	private View view;
	private GearTrain gear_train_model;
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

	public void Run() {
		while (!parentShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		try {
			if (this.gear_train_model != null)
				System.out.println(gear_train_model.getOutputSpeed());
		} catch (GearTrainNonMeshableElements e) {
			e.printStackTrace();
		}
	}

	// ===============================VIEW=============================== //
	public void initViewEventHandeling() {
		//Buttons
		view.getButtonCalculateOptimalTrain().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("Event: Button-> Calculate Optimal Gear Train");
			}
		});
		
		view.getButtonCalculate().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("Event: Button-> Calculate");
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
				System.out.println("Event: File-> Open Selected");
				String file = view.openFileBrowserForOpen();
				// TODO test if valid file!
				setGearTrainModel(XMLParser.loadGearTrain(file));
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
	public void setView(View view) {
		this.view = view;
	}

	public void setGearTrainModel(GearTrain gear_train_model) {
		this.gear_train_model = gear_train_model;
	}
}
