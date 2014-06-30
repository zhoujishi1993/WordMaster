package view;

import view.Frame.Status;
import controller.Controller;


public class PartResultPanel extends ResultPanel {
	
	public PartResultPanel(Controller controller) {
		super(controller);
	}
	
	@Override
	protected void loadResult() {
		//System.out.println("loadResult: "+getController().getResult(Status.END_PART));
		setResult(getController().getResult(Status.END_PART));
	}
	
	@Override
	protected void loadLabel() {
		setLabel("本次背诵情况");
	}

}
