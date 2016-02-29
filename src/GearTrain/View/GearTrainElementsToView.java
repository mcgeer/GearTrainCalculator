package GearTrain.View;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

public class GearTrainElementsToView extends ScrolledComposite{

	public GearTrainElementsToView(Composite arg0, int arg1, Color bg,Color fg) {
		super(arg0, arg1);
		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(bg);
		setForeground(fg);
	}

}
