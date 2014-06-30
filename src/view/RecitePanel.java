package view;

import view.Frame.Status;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javax.swing.JOptionPane;

public class RecitePanel extends VBox {
	private Controller controller;
	private ToolBar toolBar;
	private TextField answer;
	private Text label;
	private Text result;
	public RecitePanel(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		createToolBar();
		label = new Text();
		answer = new TextField();
		result = new Text();
		Button btn = new Button("Go on!");
		this.getChildren().add(toolBar);
		
		String nextWord = controller.showNextWord();
		
		if (nextWord != null)
			label.setText(nextWord);
		label.setFont(Font.font("微软雅黑", 30));
		this.getChildren().add(label);		
		this.getChildren().add(answer);
		this.getChildren().add(btn);
		this.getChildren().add(result);
		
		btn.setPrefSize(80, 50);
		
		VBox.setMargin(label, new Insets(100,100,20,100));
		VBox.setMargin(answer, new Insets(20,100,20,100));
		VBox.setMargin(btn, new Insets(20,200,20,200));
		VBox.setMargin(result, new Insets(20,200,50,200));
		
		label.setTextAlignment(TextAlignment.CENTER);
		answer.setAlignment(Pos.CENTER);
		btn.setAlignment(Pos.CENTER);
		result.setTextAlignment(TextAlignment.CENTER);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
                        if(answer.getText()==null || answer.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "输入不能为空");
                            return;
                        }
		    	boolean correct;
		        correct = controller.newInputWord(answer.getText());
		        if (!correct) {
		        	result.setText("错误！");
		        	result.setFill(Color.RED);
		        } else {
		        	result.setText("正确！");
		        	result.setFill(Color.GREEN);
		        }
		        nextWord();
		    }
		});
		
		
	}
	
	private void nextWord() {
		String nextWord = controller.showNextWord();
		answer.setText("");
		if (nextWord != null)
			label.setText(nextWord);
	}
	
	private void createToolBar() {
		Button back = new Button("Back");
		Label label = new Label("开始背诵");

		label.setPrefWidth(380);
		label.setAlignment(Pos.CENTER);
		back.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        controller.changeView(Status.START);
		    }
		});

		
		ToolBar toolBar = new ToolBar(back, label);
		toolBar.setPrefSize(400, 35);
		
		this.toolBar = toolBar;
	}
	

}
