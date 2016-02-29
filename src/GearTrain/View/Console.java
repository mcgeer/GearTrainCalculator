package GearTrain.View;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Console extends ScrolledComposite{
	//Tables
	private Table consoleMessageTabel;
	public Console(Composite arg0, int arg1, Color bg, Color fg) {
		super(arg0, arg1);
		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(bg);
		setForeground(fg);
		consoleMessageTabel = new Table(this, SWT.FULL_SELECTION);
		setMinSize(consoleMessageTabel.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		consoleMessageTabel.setBackground(bg);
		consoleMessageTabel.setForeground(fg);
		//Opening message
		TableItem openingMessage = new TableItem(consoleMessageTabel, SWT.NONE);
		openingMessage.setText(new String[]{"Hello, and Welcome to the Gear Train Calculator."});
		
		setContent(consoleMessageTabel);
	}
	
	/**
	 * Add a new message to the UI console
	 * @param message, is a string to be appended to the console
	 */
	public void addToConsol(String message){
		TableItem t = new TableItem(consoleMessageTabel, SWT.NONE);
		t.setText(new String[]{message});
		layout();	
	}
	
	/**
	 * Clear the on screen UI console
	 */
	public void clearConsol(){
		//consoleMessageTabel.clearAll();
		consoleMessageTabel.removeAll();
	}

}
