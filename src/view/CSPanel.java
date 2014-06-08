package view;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import view.Frame.Status;
import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class CSPanel extends VBox {
	private Controller controller;
	private ToolBar toolBar;
	private ListView<String> listView;
	private GridPane gridPane;
	private TextField word;
	private TextField count;
	public CSPanel(Controller controller) {
		this.controller = controller;
		init();
	}
	
	public void init() {
		createToolBar();
		createListView();
		createGridPane();
		this.getChildren().add(toolBar);
		this.getChildren().add(gridPane);
	}
	
	private void createToolBar() {
		Button back = new Button("Back");
		Label label = new Label("����ѡ��");
		Button ok = new Button("OK");
		label.setPrefWidth(380);
		label.setAlignment(Pos.CENTER);
		back.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        controller.changeView(Status.SELECT);
		    }
		});
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if (isNumeric(count.getText())) {
		    		if (!controller.setStartWord(word.getText())) {
		    			JOptionPane.showMessageDialog(null,"���뵥������Ĭ�ϴӵ�һ����ʼ");
		    		}
		    		if (!controller.setCount(Integer.parseInt(count.getText()))) {
		    			JOptionPane.showMessageDialog(null,"������������ʣ�൥��������Ĭ�ϱ������һ��Ϊֹ");
		    		}
		    		
		    		controller.changeView(Status.RECITE);
		    		
		    	} else {
		    		JOptionPane.showMessageDialog(null,"���뵥���������Ϸ�������������");
		    	}
		    }
		});
		
		ToolBar toolBar = new ToolBar(back, label, ok);
		toolBar.setPrefSize(400, 35);
		
		this.toolBar = toolBar;
	}
	
	private void createListView() {
		final ListView<String> listView = new ListView<String>();
		//listView.setPrefSize(200, 250);
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override public void handle(MouseEvent e) {
		    	word.setText(listView.getSelectionModel().getSelectedItem());
		    }
		});
		this.listView = listView;
	}
	
	private void createGridPane() {
		gridPane = new GridPane();
		GridPane.setColumnSpan(listView, 5);
		gridPane.add(listView, 0, 0);
		word = new TextField(controller.getFirstWord());
		word.setEditable(false);
		count = new TextField();
		Label countLabel = new Label("����Ҫ���ĵ�����");
		gridPane.add(countLabel, 1, 0);
		gridPane.add(count, 1, 1);
		gridPane.add(word, 1, 2);
		
		final ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("��ͷ��ʼ");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		rb1.setUserData("rb1");

		RadioButton rb2 = new RadioButton("����һ�α����ĵ��ʿ�ʼ");
		rb2.setToggleGroup(group);
		rb1.setUserData("rb2");
		 
		RadioButton rb3 = new RadioButton("��ѡ");
		rb3.setToggleGroup(group);
		rb1.setUserData("rb3");
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle old_toggle, Toggle new_toggle) {
		            if (group.getSelectedToggle() != null) {
		                if (group.getSelectedToggle().getUserData().equals("rb1")) {
		                	word.setText(controller.getFirstWord());
		                	word.setEditable(false);
		                } else if (group.getSelectedToggle().getUserData().equals("rb2")) {
		                	word.setText(controller.getLastWord());
		                	word.setEditable(false);
		                } else if (group.getSelectedToggle().getUserData().equals("rb2")) {
		                	word.setText("");
		                	word.setEditable(true);
		                }
		            }                
		        }
		});
		
		word.setOnKeyTyped(new EventHandler<KeyEvent>() {
		    @Override public void handle(KeyEvent e) {
		    	listView.setItems(FXCollections.observableArrayList(controller.getStartList(word.getText())));
		    }
		});
		
		gridPane.add(rb1, 1, 3);
		gridPane.add(rb2, 1, 3);
		gridPane.add(rb3, 1, 3);
		Button resultButton = new Button("�鿴����ͳ����Ϣ");
		
		resultButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	controller.changeView(Status.END_TOTAL);
		    }
		});
		gridPane.add(resultButton, 1, 4);
		

	}
	
	private static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	} 
}
