package view;

import view.Frame.Status;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WelcomePanel extends BorderPane {
	private Controller controller;
	public WelcomePanel(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		Text t = new Text("Word Master");
		Text t2 = new Text("ʷ���������õĵ��ʱ������");
		t.setFont(Font.font("΢���ź�",50));
		t2.setFont(Font.font("΢���ź�",30));
		Button btn = new Button("����");
		btn.setAlignment(Pos.CENTER);
		btn.setPrefSize(70, 35);
		btn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        controller.changeView(Status.SELECT);
		    }
		});
		VBox vbox = new VBox();
		vbox.getChildren().add(t);
		vbox.getChildren().add(t2);
		vbox.getChildren().add(btn);
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		this.setCenter(vbox);
		//this.setPadding(new Insets(50,50,50,50));
	}
}
