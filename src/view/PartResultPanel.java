package view;

import view.Frame.Status;
import controller.Controller;


public class PartResultPanel extends ResultPanel {
	private Controller controller;

	
	public PartResultPanel(Controller controller) {
		super(controller);
		this.controller = controller;
	}
	
	@Override
	protected void loadResult() {
		setResult(controller.getResult(Status.END_PART));
	}
	
	@Override
	protected void loadLabel() {
		setLabel("本次背诵情况");
	}

}
