package view;

import view.Frame.Status;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CLPanel extends VBox {
	private Controller controller;
	private ToolBar toolBar;
	private ListView<String> listView;
	public CLPanel(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		createToolBar();
		createListView();
		this.getChildren().add(toolBar);
		this.getChildren().add(listView);
	}
	
	private void createToolBar() {
		Button back = new Button("Back");
		Label label = new Label("Ñ¡Ôñ´Ê¿â");
		Button ok = new Button("OK");
		label.setPrefWidth(380);
		label.setAlignment(Pos.CENTER);
		//back.getStyleClass().add("backButton");
		//ok.getStyleClass().add("nextButton");
		back.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        controller.changeView(Status.INIT);
		    }
		});
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	controller.setLexicon(listView.getSelectionModel().getSelectedItem());
		        controller.changeView(Status.START);
		    }
		});
		
		ToolBar toolBar = new ToolBar(back, label, ok);
		toolBar.setPrefSize(400, 35);
		
		this.toolBar = toolBar;
	}
	
	private void createListView() {
		ObservableList<String> data = FXCollections.observableArrayList(controller.getLexiconList());
		final ListView<String> listView = new ListView<String>(data);
		//listView.setPrefSize(200, 250);
		this.listView = listView;
	}
}
