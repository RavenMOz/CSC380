package application.oldui.components;

import application.oldui.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MemberEditor extends VBox implements Runnable {
	
	Thread t;
	
	public MemberEditor() {
		
		super(35);
		
		TextField f = new TextField();
		Label l = new Label("Name:");
		
		l.setFont(new Font("Lexend", 24));
		
		getChildren().addAll(l, f);
		
		setBackground(Background.fill(new Color(.8, .9, .99, 1)));
		setMaxWidth(500);
		
		start();
	}
	
	void start() {
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			setPrefWidth(Main.stage.getWidth()/3);
		}
	}
	
}
