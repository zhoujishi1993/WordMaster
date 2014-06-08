package view;

import model.Result;
import view.Frame.Status;
import controller.Controller;


public class TotalResultPanel extends ResultPanel {
	private Controller controller;

	
	public TotalResultPanel(Controller controller) {
		super(controller);
		this.controller = controller;
	}
	
	@Override
	protected void loadResult() {
		//setResult(controller.getResult(Status.END_TOTAL));
		setResult(new Result("ads",1,1,1,1,1));
	}
	
	@Override
	protected void loadLabel() {
		setLabel("±¾´Ê¿â±³ËÐÇé¿ö");
	}

}
