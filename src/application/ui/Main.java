package application.ui;
	
import application.ui.components.GlobalNavigation;
import application.ui.components.MemberEditor;
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
		
//		Image img1 = new Image(getClass().getResourceAsStream("save.png"));
//		Image img2 = new Image(getClass().getResourceAsStream("ok.png"));
//		ImageView iview1 = new ImageView(img1);
//		ImageView iview2 = new ImageView(img2);
//		Button button = new Button("_Save", iview1);
//		
//		Label label = new Label("This is a Label", iview2);
//		
//		button.setOnAction(new EventHandler<ActionEvent>() {
//
//			public void handle(ActionEvent event) {
//
//				System.out.println("Button Pressed");
//				
//			}
//			
//		});
//		
//		final TextField textField = new TextField();
//		textField.setPromptText("Enter your cock...");
//		textField.setFocusTraversable(false);
//		textField.setOnAction(new EventHandler<ActionEvent>() {
//
//			public void handle(ActionEvent event) {
//
//				System.out.println("You entered text");
//				System.out.println(textField.getText());
//				textField.setText("Get fukt mate");
//				
//			}
//			
//		});
//		
//		button.setFont(new Font("Lexend", 28));
//		button.setGraphicTextGap(10);
//		button.setRotate(20);
//		
//		Rectangle shape = new Rectangle(400,200);
//		shape.setArcHeight(50);
//		shape.setArcWidth(50);
//		button.setShape(shape);
//		HBox hbox = new HBox(textField);
//		root.setBottom(hbox);
//		root.setCenter(button);
//		root.setTop(label);
		root.setBackground(Background.fill(Color.DODGERBLUE));

		Scene scene = new Scene(root, 800, 600, Color.DODGERBLUE);
		
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
