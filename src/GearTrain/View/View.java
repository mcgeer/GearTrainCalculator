package GearTrain.View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class View {
	// ================View Variables needed by Controller=================//
	// Main Display
	private Shell parentShell;
	private Display display;

	// Menu Bar
	private MenuItem menuFileItemNew;
	private MenuItem menuFileItemOpen;
	private MenuItem menuFileItemSave;
	private MenuItem menuFileItemSaveAs;

	private MenuItem menuProjectItemProperties;
	private MenuItem menuProjectItemCalculate;

	private MenuItem menuHelpItemProgramHelp;
	private MenuItem menuHelpItemGeneralHelp;
	
	//Buttons
	private Button buttonCalculate;
	private Button buttonCalculateOptimalTrain;
	
	//Text boxes
	private Text inputSpeedText;
	private Text boxDimensionXText;
	private Text outputSpeedText;
	private Text boxDimensionYText;
	private Text boxDimensionZText;
	private Text minGearSizeText;
	private Text maxGearSizeText;
	
	//FileBrowser
	private FileDialog fileBrowser;
	
	//Colors
	private final Color CUSTOM_BLACK = new Color(display, 0x2C, 0x3E, 0x50);
	private final Color CUSTOM_WHITE = new Color(display, 0xFA, 0xFA, 0xFF);
	private final Color CUSTOM_PURPLE = new Color(display, 0x9B, 0x59, 0xB6);
	private final Color CUSTOM_GREEN = new Color(display, 0x2E, 0xCC, 0x71);
	private final Color CUSTOM_BLUE = new Color(display, 0x34, 0x98, 0xDB);
	private final Color CUSTOM_ORANGE = new Color(display, 0xE6, 0x7E, 0x22);
	private final Color CUSTOM_YELLOW = new Color(display, 0xF1, 0xC4, 0x0F);
	private final Color CUSTOM_RED= new Color(display, 0xE7, 0x4C, 0x3C);
	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		display = Display.getDefault();
		createMainContents();
		parentShell.open();
		parentShell.layout();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createMainContents() {
		parentShell = new Shell(SWT.CLOSE | SWT.TITLE | SWT.MIN);
		parentShell.setToolTipText("");
		parentShell.setSize(1080, 720);
		parentShell.setText("GearTrain");
		parentShell.setBackground(CUSTOM_BLACK);
		parentShell.setLayout(null);
		Menu menu = new Menu(parentShell, SWT.BAR);
		parentShell.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_file = new Menu(mntmFile);
		mntmFile.setMenu(menu_file);

		menuFileItemNew = new MenuItem(menu_file, SWT.NONE);
		menuFileItemNew.setText("New");

		menuFileItemOpen = new MenuItem(menu_file, SWT.NONE);
		menuFileItemOpen.setText("Open File...");

		MenuItem menuFileLineSpeorator1 = new MenuItem(menu_file, SWT.SEPARATOR);
		menuFileLineSpeorator1.setText("menu.file.separator");

		menuFileItemSave = new MenuItem(menu_file, SWT.NONE);
		menuFileItemSave.setText("Save");

		menuFileItemSaveAs = new MenuItem(menu_file, SWT.NONE);
		menuFileItemSaveAs.setText("Save As...");

		MenuItem mntmProject = new MenuItem(menu, SWT.CASCADE);
		mntmProject.setText("Project");

		Menu menu_project = new Menu(mntmProject);
		menu_project.setLocation(new Point(0, 0));
		mntmProject.setMenu(menu_project);

		menuProjectItemProperties = new MenuItem(menu_project, SWT.NONE);
		menuProjectItemProperties.setText("Properties");

		menuProjectItemCalculate = new MenuItem(menu_project, SWT.NONE);
		menuProjectItemCalculate.setText("Calculate...");

		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");
		
		Menu menu_help = new Menu(mntmHelp);
		menu_help.setLocation(new Point(0, 0));
		mntmHelp.setMenu(menu_help);

		menuHelpItemProgramHelp = new MenuItem(menu_help, SWT.NONE);
		menuHelpItemProgramHelp.setText("Program Help");

		menuHelpItemGeneralHelp = new MenuItem(menu_help, SWT.NONE);
		menuHelpItemGeneralHelp.setText("General Help");
		
		ScrolledComposite consoleScrolledComposite = new ScrolledComposite(parentShell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		consoleScrolledComposite.setBounds(25, 474, 1024, 150);
		consoleScrolledComposite.setExpandHorizontal(true);
		consoleScrolledComposite.setExpandVertical(true);
		consoleScrolledComposite.setBackground(CUSTOM_WHITE);
		
		ScrolledComposite gearTrainElementScrolledComposite = new ScrolledComposite(parentShell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		gearTrainElementScrolledComposite.setBounds(391, 53, 658, 415);
		gearTrainElementScrolledComposite.setExpandHorizontal(true);
		gearTrainElementScrolledComposite.setExpandVertical(true);
		gearTrainElementScrolledComposite.setBackground(CUSTOM_WHITE);
		
		Label lblInputSpeed = new Label(parentShell, SWT.NONE);
		lblInputSpeed.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblInputSpeed.setBounds(25, 53, 163, 35);
		lblInputSpeed.setText("Input Speed:");
		lblInputSpeed.setBackground(CUSTOM_BLACK);
		lblInputSpeed.setForeground(CUSTOM_BLUE);
		
		Label lblBoxDimensionX = new Label(parentShell, SWT.NONE);
		lblBoxDimensionX.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblBoxDimensionX.setBounds(25, 133, 158, 35);
		lblBoxDimensionX.setText("Box Dimension X:");
		lblBoxDimensionX.setBackground(CUSTOM_BLACK);
		lblBoxDimensionX.setForeground(CUSTOM_BLUE);
		
		Label lblBoxDimensionY = new Label(parentShell, SWT.NONE);
		lblBoxDimensionY.setSize(158, 35);
		lblBoxDimensionY.setLocation(25, 174);
		lblBoxDimensionY.setText("Box Dimension Y:");
		lblBoxDimensionY.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblBoxDimensionY.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblBoxDimensionY.setBackground(CUSTOM_BLACK);
		lblBoxDimensionY.setForeground(CUSTOM_BLUE);
		
		Label lblBoxDimensionZ = new Label(parentShell, SWT.NONE);
		lblBoxDimensionZ.setSize(158, 35);
		lblBoxDimensionZ.setLocation(25, 215);
		lblBoxDimensionZ.setText("Box Dimension Z:");
		lblBoxDimensionZ.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblBoxDimensionZ.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblBoxDimensionZ.setBackground(CUSTOM_BLACK);
		lblBoxDimensionZ.setForeground(CUSTOM_BLUE);
		
		Label lblOutputSpeed = new Label(parentShell, SWT.NONE);
		lblOutputSpeed.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblOutputSpeed.setBounds(25, 392, 134, 35);
		lblOutputSpeed.setText("Output Speed:");
		lblOutputSpeed.setBackground(CUSTOM_BLACK);
		lblOutputSpeed.setForeground(CUSTOM_GREEN);
		
		Label lblAllowWormDrives = new Label(parentShell, SWT.NONE);
		lblAllowWormDrives.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblAllowWormDrives.setBounds(25, 338, 188, 35);
		lblAllowWormDrives.setText("Allow Worm Drives:");
		lblAllowWormDrives.setBackground(CUSTOM_BLACK);
		lblAllowWormDrives.setForeground(CUSTOM_ORANGE);
		
		Label lblMinGearSize = new Label(parentShell, SWT.NONE);
		lblMinGearSize.setSize(158, 35);
		lblMinGearSize.setLocation(25, 256);
		lblMinGearSize.setText("Min Gear Size:");
		lblMinGearSize.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblMinGearSize.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblMinGearSize.setBackground(CUSTOM_BLACK);
		lblMinGearSize.setForeground(CUSTOM_BLUE);
		
		Label lblMaxGearSize = new Label(parentShell, SWT.NONE);
		lblMaxGearSize.setSize(158, 35);
		lblMaxGearSize.setLocation(25, 297);
		lblMaxGearSize.setText("Max Gear Size:");
		lblMaxGearSize.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblMaxGearSize.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblMaxGearSize.setBackground(CUSTOM_BLACK);
		lblMaxGearSize.setForeground(CUSTOM_BLUE);
		
		Label lblUnits = new Label(parentShell, SWT.NONE);
		lblUnits.setSize(163, 33);
		lblUnits.setLocation(25, 94);
		lblUnits.setText("Units:");
		lblUnits.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblUnits.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblUnits.setBackground(CUSTOM_BLACK);
		lblUnits.setForeground(CUSTOM_BLUE);
		
		Label lblGearTrain = new Label(parentShell, SWT.NONE);
		lblGearTrain.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblGearTrain.setBounds(10, 10, 375, 37);
		lblGearTrain.setText("Gear Train:");
		lblGearTrain.setBackground(CUSTOM_BLACK);
		lblGearTrain.setForeground(CUSTOM_PURPLE);
		
		Label lblElements = new Label(parentShell, SWT.NONE);
		lblElements.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblElements.setBounds(609, 10, 228, 37);
		lblElements.setText("Gear Train Elements");
		lblElements.setBackground(CUSTOM_BLACK);
		lblElements.setForeground(CUSTOM_YELLOW);
		
		inputSpeedText = new Text(parentShell, SWT.BORDER);
		inputSpeedText.setBounds(189, 53, 196, 35);
		
		boxDimensionXText = new Text(parentShell, SWT.BORDER);
		boxDimensionXText.setBounds(189, 133, 196, 35);
		
		outputSpeedText = new Text(parentShell, SWT.BORDER);
		outputSpeedText.setBounds(189, 392, 196, 35);
		
		boxDimensionYText = new Text(parentShell, SWT.BORDER);
		boxDimensionYText.setBounds(189, 174, 196, 35);
		
		boxDimensionZText = new Text(parentShell, SWT.BORDER);
		boxDimensionZText.setBounds(189, 215, 196, 35);
		
		minGearSizeText = new Text(parentShell, SWT.BORDER);
		minGearSizeText.setBounds(189, 256, 196, 35);
		
		maxGearSizeText = new Text(parentShell, SWT.BORDER);
		maxGearSizeText.setBounds(189, 297, 196, 35);
		
		buttonCalculate = new Button(parentShell, SWT.NONE);
		buttonCalculate.setBounds(219, 433, 166, 35);
		buttonCalculate.setText("Calculate");
		
		buttonCalculateOptimalTrain = new Button(parentShell, SWT.NONE);
		buttonCalculateOptimalTrain.setBounds(25, 433, 188, 35);
		buttonCalculateOptimalTrain.setText("Calculate Optimal Train");
		
		Button btnAllowWormDrives = new Button(parentShell, SWT.CHECK);
		btnAllowWormDrives.setSelection(true);
		btnAllowWormDrives.setBounds(215, 337, 29, 32);
		btnAllowWormDrives.setText("");
		btnAllowWormDrives.setBackground(CUSTOM_BLACK);
		btnAllowWormDrives.setForeground(CUSTOM_RED);
		
		Combo comboSpeedUnits = new Combo(parentShell, SWT.NONE);
		comboSpeedUnits.setItems(new String[] {"RPS", "RPM"});
		comboSpeedUnits.setBounds(189, 94, 94, 33);
		comboSpeedUnits.select(0);
		
		Combo comboDimensionUnits = new Combo(parentShell, SWT.NONE);
		comboDimensionUnits.setItems(new String[] {"mm", "cm", "m", "in", "ft"});
		comboDimensionUnits.setBounds(289, 94, 94, 33);
		comboDimensionUnits.select(0);
	}

// ======================File Browser======================== //
	public String openFileBrowserForOpen() {
		fileBrowser = new FileDialog(parentShell, SWT.OPEN);
		fileBrowser.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
		fileBrowser.setFilterExtensions(new String[] { "*.xml", "*.*" }); // Windows
		
		fileBrowser.setFilterPath("c:\\"); // Windows path
		return fileBrowser.open();
	}

	public String openFileBrowserForSave() {
		fileBrowser = new FileDialog(parentShell, SWT.SAVE);
		fileBrowser.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
		fileBrowser.setFilterExtensions(new String[] { "*.xml", "*.*" }); // Windows
		fileBrowser.setFilterPath("c:\\"); // Windows path
		return fileBrowser.open();
	}
	
//========================GETTERS===========================//
	
// =====Getters for objects required outside the scope of this class===== //
	public Display getDisplay() {
		return display;
	}
	

	public Shell getParentShell() {
		return parentShell;
	}

	public MenuItem getMenuFileItemNew() {
		return menuFileItemNew;
	}

	public MenuItem getMenuFileItemOpen() {
		return menuFileItemOpen;
	}

	public MenuItem getMenuFileItemSave() {
		return menuFileItemSave;
	}

	public MenuItem getMenuFileItemSaveAs() {
		return menuFileItemSaveAs;
	}

	public MenuItem getMenuProjectItemProperties() {
		return menuProjectItemProperties;
	}

	public MenuItem getMenuProjectItemCalculate() {
		return menuProjectItemCalculate;
	}

	public MenuItem getMenuHelpItemProgramHelp() {
		return menuHelpItemProgramHelp;
	}

	public MenuItem getMenuHelpItemGeneralHelp() {
		return menuHelpItemGeneralHelp;
	}
	
	public Button getButtonCalculate(){
		return this.buttonCalculate;
	}
	
	public Button getButtonCalculateOptimalTrain(){
		return this.buttonCalculateOptimalTrain;
	}
}
