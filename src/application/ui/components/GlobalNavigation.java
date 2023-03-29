package application.ui.components;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GlobalNavigation extends HBox {

	private Button save;
	private Button close;
	private Button clear;
	private Button export;
	private Button share;
	
	public GlobalNavigation() {
		
		super(35);
		
		setBackground(Background.fill(new Color(.6, .8, 1, 1)));
		
		save = new Button("Save");
		close = new Button("Close");
		clear = new Button("Clear");
		export = new Button("Export");
		share = new Button("Share");
		
		Rectangle shape = new Rectangle(400,200);
		shape.setArcHeight(50);
		shape.setArcWidth(50);
		
		save.setShape(shape);
		close.setShape(shape);
		clear.setShape(shape);
		export.setShape(shape);
		share.setShape(shape);
		
		save.setPrefSize(75, 55);
		close.setPrefSize(75, 55);
		clear.setPrefSize(75, 55);
		export.setPrefSize(75, 55);
		share.setPrefSize(75, 55);
				
		getChildren().addAll(save, close, clear, export, share);
		setPrefWidth(1920);
		setMinHeight(75);
		
	}
	
}
