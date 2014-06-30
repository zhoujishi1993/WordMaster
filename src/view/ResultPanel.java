package view;

import model.Result;
import view.Frame.Status;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public abstract class ResultPanel extends VBox {
	private Controller controller;
	private ToolBar toolBar;

	private Label label;
	private Result result;
	private TableView<Result> table;
	
	private PieChart chart1;
	private PieChart chart2;
	
	public ResultPanel(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		label = new Label();
		loadResult();
		loadLabel();
		createToolBar();
		createTable();
		createChart();
		this.getChildren().add(toolBar);
		
		
		
		this.setSpacing(5);
        this.getChildren().add(table);
        
        HBox hbox = new HBox();
        hbox.getChildren().add(chart1);
        hbox.getChildren().add(chart2);
        hbox.setSpacing(10);
        this.getChildren().add(hbox);
       
		
		
	}

	
	protected void createToolBar() {
		Button back = new Button("Back");

		label.setPrefWidth(350);
		label.setAlignment(Pos.CENTER);
		back.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        controller.changeView(Status.SELECT);
		    }
		});

		
		ToolBar toolBar = new ToolBar(back, label);
		toolBar.setPrefSize(400, 35);
		
		this.toolBar = toolBar;
	}
	
	private void createTable() {
		table = new TableView<Result>();
		TableColumn<Result, String> nameColumn = new TableColumn<Result, String>("词库名");
	    TableColumn<Result, Integer> totalColumn = new TableColumn<Result, Integer>("词库单词总数");
	    TableColumn<Result, Integer> recitedColumn = new TableColumn<Result, Integer>("已背单词数");
	    TableColumn<Result, Integer> correctColumn = new TableColumn<Result, Integer>("正确单词数");
	    TableColumn<Result, Integer> wrongColumn = new TableColumn<Result, Integer>("错误单词数");
	    TableColumn<Result, Double> accuracyColumn = new TableColumn<Result, Double>("正确率");
	    
	    
	       
	    final ObservableList<Result> data = FXCollections.observableArrayList(result);
	    nameColumn.setCellValueFactory(
	    	    new PropertyValueFactory<Result,String>("name")
	    	);
	    totalColumn.setCellValueFactory(
	    	    new PropertyValueFactory<Result,Integer>("totalNum")
	    	);
	    recitedColumn.setCellValueFactory(
	    	    new PropertyValueFactory<Result,Integer>("recited")
	    	);
	    correctColumn.setCellValueFactory(
	    	    new PropertyValueFactory<Result,Integer>("correct")
	    	);
	    wrongColumn.setCellValueFactory(
	    	    new PropertyValueFactory<Result,Integer>("wrong")
	    	);
	    accuracyColumn.setCellValueFactory(
	    	    new PropertyValueFactory<Result,Double>("accuracy")
	    	);
	    table.setItems(data);
	    table.getColumns().addAll(nameColumn, totalColumn, recitedColumn, correctColumn, wrongColumn, accuracyColumn);
	    for (TableColumn<Result, ?> tc:table.getColumns()) {
	    	tc.setMinWidth(83);
	    }
	}
	
	protected abstract void loadResult();
	protected abstract void loadLabel();
	
	public void setResult(Result r) {
		this.result = r;
	}
	
	public void setLabel(String s) {
		this.label.setText(s);
	}
	
	private void createChart() {
		double per1 = 0;
		double per2 = 0;
		if (result.getRecited()!=0) {
			per1 = (double)result.getCorrect()/(double)result.getRecited()*100;
			per2 = (double)result.getWrong()/(double)result.getRecited()*100;
		}
		System.out.println("recited: "+result.getRecited());
		ObservableList<PieChart.Data> pieChartData1 =
				
                FXCollections.observableArrayList(
                new PieChart.Data("正确", per1),
                new PieChart.Data("错误", per2));

        chart1 = new PieChart(pieChartData1);
        chart1.setTitle("Accuracy");
        
        ObservableList<PieChart.Data> pieChartData2 =
                FXCollections.observableArrayList(
                new PieChart.Data("背诵", (double)result.getRecited()/(double)result.getTotalNum()*100),
                new PieChart.Data("未背诵", (1-(double)result.getRecited()/(double)result.getTotalNum())*100));

        chart2 = new PieChart(pieChartData2);
        chart2.setTitle("Has Recited");
	}
	
	protected Controller getController() {
		return controller;
	}
	
}
