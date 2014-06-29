package view;

import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import view.Frame.Status;
import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
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
	private int type;
	public CSPanel(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		type = 1;
		createToolBar();
		createListView();
		createGridPane();
		this.getChildren().add(toolBar);
		this.getChildren().add(gridPane);
	}
	
	private void createToolBar() {
		Button back = new Button("Back");
		Label label = new Label("背诵选项");
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
		    		System.out.println(type);
		    		if (!controller.setStartPosition(type, word.getText())) {
		    			JOptionPane.showMessageDialog(null,"输入单词有误，默认从第一个开始");
		    		}
		    		if (!controller.setReciteNum(Integer.parseInt(count.getText()))) {
		    			JOptionPane.showMessageDialog(null,"输入数量超过剩余单词数量，默认背到最后一个为止");
		    		}
		    		
		    		controller.changeView(Status.RECITE);
		    		
		    	} else {
		    		JOptionPane.showMessageDialog(null,"输入单词数量不合法，请重新输入");
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
		gridPane.add(listView, 0, 0);
		word = new TextField();
		word.setEditable(false);
		count = new TextField();
		Label countLabel = new Label("输入要背的单词数");
		Label modeLabel = new Label("选择背诵模式");
		VBox vbox = new VBox();
		vbox.getChildren().add(countLabel);
		vbox.getChildren().add(count);
		vbox.getChildren().add(modeLabel);
		
		
		
		//gridPane.add(count, 1, 1);
		//gridPane.add(word, 1, 2);
		
		final ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("从头开始");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		rb1.setUserData("rb1");

		RadioButton rb2 = new RadioButton("从上一次背到的单词开始");
		rb2.setToggleGroup(group);
		rb2.setUserData("rb2");
		 
		RadioButton rb3 = new RadioButton("自选");
		rb3.setToggleGroup(group);
		rb3.setUserData("rb3");
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle old_toggle, Toggle new_toggle) {
		            if (group.getSelectedToggle() != null) {
		                if (new_toggle.getUserData().toString().equals("rb1")) {
		                	word.setEditable(false);
		                	type = 1;
		                } else if (new_toggle.getUserData().toString().equals("rb2")) {
		                	word.setEditable(false);
		                	type = 2;
		                } else if (new_toggle.getUserData().toString().equals("rb3")) {
		                	word.setEditable(true);
		                	type = 3;
		                }
		            }                
		        }
		});
		
		word.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override public void handle(KeyEvent e) {
		    	System.out.println("prefix:"+word.getText());
		    	System.out.println(e.getText());
		    	String s = word.getText();
		    	if (e.getCode().isLetterKey()) {
		    		s = s + e.getText();
		    	} else if (e.getCode().isWhitespaceKey()) {
		    		s = s + " ";
		    	} else if (e.getCode().equals(KeyCode.BACK_SPACE)) {
		    		if (s!=null && !s.equals(""))
		    			s = s.substring(0, s.length()-1);
		    	}
		    	listView.setItems(FXCollections.observableArrayList(controller.getStartList(s)));
		    }
		});
		vbox.getChildren().add(rb1);
		vbox.getChildren().add(rb2);
		vbox.getChildren().add(rb3);
		vbox.getChildren().add(word);
	//	gridPane.add(rb1, 1, 3);
	//	gridPane.add(rb2, 1, 3);
	//	gridPane.add(rb3, 1, 3);
		Button resultButton = new Button("查看背诵统计信息");
		
		resultButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	controller.changeView(Status.END_TOTAL);
		    }
		});
		vbox.getChildren().add(resultButton);
		
		Insets insets = new Insets(10,10,10,10);
		for (Node c : vbox.getChildren()) {
			VBox.setMargin(c, insets);
		}
		gridPane.add(vbox, 1, 0);
		

	}
	
	private static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]+"); 
	    return pattern.matcher(str).matches();    
	} 
}
