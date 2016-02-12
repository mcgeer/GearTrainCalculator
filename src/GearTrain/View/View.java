package GearTrain.View;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.graphics.Point;

public class View{

	protected Shell parentShell;
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		parentShell.open();
		parentShell.layout();
		while (!parentShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		parentShell = new Shell();
		parentShell.setToolTipText("");
		parentShell.setSize(1080, 720);
		parentShell.setText("GearTrain");
		parentShell.setLayout(null);
		
		Menu menu = new Menu(parentShell, SWT.BAR);
		parentShell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_file = new Menu(mntmFile);
		mntmFile.setMenu(menu_file);
		
		MenuItem mntmNew = new MenuItem(menu_file, SWT.NONE);
		mntmNew.setText("New");
		
		MenuItem mntmOpen = new MenuItem(menu_file, SWT.NONE);
		mntmOpen.setText("Open File...");
		
		MenuItem menuItem = new MenuItem(menu_file, SWT.SEPARATOR);
		menuItem.setText("menu.file.separator");
		
		MenuItem mntmSave = new MenuItem(menu_file, SWT.NONE);
		mntmSave.setText("Save");
		
		MenuItem mntmSaveAs = new MenuItem(menu_file, SWT.NONE);
		mntmSaveAs.setText("Save As...");
		
		MenuItem mntmProject = new MenuItem(menu, SWT.CASCADE);
		mntmProject.setText("Project");
		
		Menu menu_project = new Menu(mntmProject);
		menu_project.setLocation(new Point(0, 0));
		mntmProject.setMenu(menu_project);
		
		MenuItem mntmProperties = new MenuItem(menu_project, SWT.NONE);
		mntmProperties.setText("Properties");
		
		MenuItem mntmCalcul = new MenuItem(menu_project, SWT.NONE);
		mntmCalcul.setText("Calculate...");
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");
		
		Menu menu_help = new Menu(mntmHelp);
		menu_help.setLocation(new Point(0, 0));
		mntmHelp.setMenu(menu_help);
		
		MenuItem mntmProgramHelp = new MenuItem(menu_help, SWT.NONE);
		mntmProgramHelp.setText("Program Help");
		
		MenuItem mntmGeneralHelp = new MenuItem(menu_help, SWT.NONE);
		mntmGeneralHelp.setText("General Help");

	}
}

