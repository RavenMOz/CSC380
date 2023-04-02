package application.oldui;
	
import application.oldui.components.GlobalNavigation;
import application.oldui.components.MemberEditor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static Stage stage;
//	private Scene scene;
	private BorderPane root;
	
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		stage.setTitle("Primary Stage");

		this.root = new BorderPane();
		root.setTop(new GlobalNavigation());
		root.setRight(new MemberEditor());
		
		root.setBackground(Background.fill(Color.DODGERBLUE));

		Scene scene = new Scene(root, 800, 600, Color.DODGERBLUE);
		
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
