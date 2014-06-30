package view;

import model.Result;
import view.Frame.Status;
import controller.Controller;


public class TotalResultPanel extends ResultPanel {

	public TotalResultPanel(Controller controller) {
		super(controller);
	}
	
	@Override
	protected void loadResult() {
		setResult(getController().getResult(Status.END_TOTAL));
	}
	
	@Override
	protected void loadLabel() {
		setLabel("本词库背诵情况");
	}

}
