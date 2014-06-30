package view;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Frame extends Application{

	private Stage stage;
	private Controller controller;
    public enum Status {
        INIT (0), SELECT (1), START (2), RECITE (3), END_TOTAL (4), END_PART (5);
  
        private int nCode ;
  
        private Status(int _nCode) {
            this.nCode = _nCode;
        }
  
        @Override
        public String toString() {
            return String.valueOf ( this.nCode );
        }
     }
    
    public Frame() {
            try {
                this.controller = new Controller(this);
            } catch (Exception ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.exit(0);
            }
    }
    
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("WordMaster");
        stage = primaryStage;
        changeView(Status.INIT);
        primaryStage.show();
    }
    
    public void changeView(Status status) {
    	Parent root = null;
    	Scene scene;
    	switch (status) {
    	case INIT:
    		root = new WelcomePanel(controller);
    		break;
    	case SELECT:
    		root = new CLPanel(controller);
    		break;
    	case START:
    		root = new CSPanel(controller);
    		break;
    	case RECITE:
    		root = new RecitePanel(controller);
    		break;
    	case END_TOTAL:
    		root = new TotalResultPanel(controller);
    		break;
    	case END_PART:
    		root = new PartResultPanel(controller);
    		break;
    	default:
    	}
        scene = new Scene(root, 500, 450);
		stage.setScene(scene);
		stage.setResizable(false);
    }
}
