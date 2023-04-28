package treelinecalculationsalgorithm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RecursiveTreeTest extends Application
{
    static Stage window;
    static Scene scene;

    static Pane pane = new Pane();

    static Label currentMemberLabel;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Family myFamily = new Family();

        /****************************************************************************************************************************/

        window = primaryStage;
        window.setTitle("Tree Line Algorithm Test");

        Line dividingLine1 = new Line(1200, 0, 1200, 1000);
        Line dividingLine2 = new Line(1200, 210, 1500, 210);
        Line dividingLine3 = new Line(1200, 550, 1500, 550);

        Label nameLabel = new Label("Enter Member Name:");
        nameLabel.setLayoutX(1225);
        nameLabel.setLayoutY(10);

        TextField nameInput = new TextField();
        nameInput.setLayoutX(1350);
        nameInput.setLayoutY(10);

        Label currentLabel = new Label("CURRENT MEMBER:");
        currentLabel.setLayoutX(1215);
        currentLabel.setLayoutY(110);
        currentLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        currentLabel.setUnderline(true);
        currentLabel.setTextFill(Color.RED);

        currentMemberLabel = new Label();
        currentMemberLabel.setLayoutX(1225);
        currentMemberLabel.setLayoutY(160);
        currentMemberLabel.setFont(Font.font("Verdana", 15));

        TextField spouseInput = new TextField();
        spouseInput.setLayoutX(1350);
        spouseInput.setLayoutY(310);

        Label spouseLabel = new Label("Enter New Spouse:");
        spouseLabel.setLayoutX(1225);
        spouseLabel.setLayoutY(310);

        TextField parentInput = new TextField();
        parentInput.setLayoutX(1350);
        parentInput.setLayoutY(410);

        Label parentLabel = new Label("Enter New Parent:");
        parentLabel.setLayoutX(1225);
        parentLabel.setLayoutY(410);

        TextField childInput = new TextField();
        childInput.setLayoutX(1350);
        childInput.setLayoutY(510);

        Label childLabel = new Label("Enter New Child:");
        childLabel.setLayoutX(1225);
        childLabel.setLayoutY(510);

        Button spouseButton = new Button("Add Spouse");
        spouseButton.setLayoutX(1300);
        spouseButton.setLayoutY(260);
        spouseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                Member curMember         = null;
                Member curMemberRelative = null;

                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(currentMemberLabel.getText()))
                    {
                        curMember = myFamily.getMember(count);

                        myFamily.setRootMember(curMember);

                        break;
                    }
                }

                if(curMember == null)
                {
                    curMember = new Member(currentMemberLabel.getText());
                }

                if(!(spouseInput.getText().equals("")) && !(curMember.hasSpouse()))
                {
                    curMemberRelative = new Member(spouseInput.getText());

                    curMember.addSpouse(curMemberRelative);
                    curMemberRelative.addSpouse(curMember);

                    if(curMember.getSpouse().hasChildren())
                    {
                        for(int i = 0; i < curMember.getSpouse().getChildCount(); i++)
                        {
                            curMember.addChild(curMember.getSpouse().getChild(i));
                        }
                    }

                    if(curMemberRelative.getSpouse().hasChildren())
                    {
                        for(int i = 0; i < curMemberRelative.getSpouse().getChildCount(); i++)
                        {
                            curMemberRelative.addChild(curMemberRelative.getSpouse().getChild(i));
                        }
                    }

                    myFamily.addMember(curMemberRelative);
                }

                myFamily.addMember(curMember);

                spouseInput.setText("");

                drawMembers(myFamily, myFamily.getRootMember());
            }
        });

        Button parentButton = new Button("Add Parent");
        parentButton.setLayoutX(1300);
        parentButton.setLayoutY(360);
        parentButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                Member curMember         = null;
                Member curMemberRelative = null;

                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(currentMemberLabel.getText()))
                    {
                        curMember = myFamily.getMember(count);

                        myFamily.setRootMember(curMember);

                        break;
                    }
                }

                if(curMember == null)
                {
                    curMember = new Member(currentMemberLabel.getText());
                }

                if(!(parentInput.getText().equals("")) && !(curMember.hasTwoParents()))
                {
                    curMemberRelative = new Member(parentInput.getText());
                    
                    curMember.addParent(curMemberRelative);
                    curMemberRelative.addChild(curMember);

                    if(curMember.hasTwoParents())
                    {
                        if(curMember.getParent1() == curMemberRelative)
                        {
                            curMember.getParent2().addSpouse(curMemberRelative);
                            curMemberRelative.addSpouse(curMember.getParent2());
                        }

                        else if(curMember.getParent2() == curMemberRelative)
                        {
                            curMember.getParent1().addSpouse(curMemberRelative);
                            curMemberRelative.addSpouse(curMember.getParent1());
                        }
                    }

                    myFamily.addMember(curMemberRelative);
                }

                myFamily.addMember(curMember);

                parentInput.setText("");

                drawMembers(myFamily, myFamily.getRootMember());
            }
        });

        Button childButton = new Button("Add Child");
        childButton.setLayoutX(1300);
        childButton.setLayoutY(460);
        childButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                Member curMember         = null;
                Member curMemberRelative = null;

                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(currentMemberLabel.getText()))
                    {
                        curMember = myFamily.getMember(count);

                        myFamily.setRootMember(curMember);

                        break;
                    }
                }

                if(curMember == null)
                {
                    curMember = new Member(currentMemberLabel.getText());
                }

                if(!(childInput.getText().equals("")))
                {
                    curMemberRelative = new Member(childInput.getText());
                    
                    curMember.addChild(curMemberRelative);

                    if(curMember.hasSpouse())
                    {
                        curMember.getSpouse().addChild(curMemberRelative);
                    }
                    
                    curMemberRelative.addParent(curMember);
                    curMemberRelative.addParent(curMember.getSpouse());
                    
                    myFamily.addMember(curMemberRelative);
                }

                myFamily.addMember(curMember);

                childInput.setText("");

                drawMembers(myFamily, myFamily.getRootMember());
            }
        });

        Button addButton = new Button("Add Member");
        addButton.setLayoutX(1300);
        addButton.setLayoutY(60);
        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                Member curMember = null;

                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(nameInput.getText()))
                    {
                        curMember = myFamily.getMember(count);

                        myFamily.setRootMember(curMember);

                        break;
                    }
                }

                if(curMember == null)
                {
                    curMember = new Member(nameInput.getText());
                }

                myFamily.addMember(curMember);

                currentMemberLabel.setText(curMember.getName());
                nameInput.setText("");

                drawMembers(myFamily, myFamily.getRootMember());
            }
        });

        Button removeSpouseButton = new Button("Remove Spouse");
        removeSpouseButton.setLayoutX(1240);
        removeSpouseButton.setLayoutY(625);
        removeSpouseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(myFamily.getRootMember().getSpouse().getName()))
                    {
                        myFamily.getRootMember().removeSpouse();

                        removeMembers(myFamily, myFamily.getMember(count), myFamily.getRootMember());

                        if(!(myFamily.getRootMember().hasChildren()))
                        {
                            pane.getChildren().remove(myFamily.getRootMember().getLine(myFamily.getRootMember().getBottomLine().getEndX(), myFamily.getRootMember().getBottomLine().getEndY()));
                            pane.getChildren().remove(myFamily.getRootMember().getBottomLine());
                        }

                        break;
                    }
                }
            }
        });

        Button removeParent1Button = new Button("Remove Parent 1");
        removeParent1Button.setLayoutX(1240);
        removeParent1Button.setLayoutY(665);
        removeParent1Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(myFamily.getRootMember().getParent1().getName()))
                    {
                        pane.getChildren().remove(myFamily.getRootMember().getLine(myFamily.getRootMember().getParent1().getBottomLine().getEndX(), myFamily.getRootMember().getParent1().getBottomLine().getEndY()));

                        myFamily.getRootMember().removeParent(myFamily.getRootMember().getParent1());

                        removeMembers(myFamily, myFamily.getMember(count), myFamily.getRootMember());

                        if(myFamily.getRootMember().hasNoParents())
                        {
                            pane.getChildren().remove(myFamily.getRootMember().getLine(myFamily.getRootMember().getTopLine().getEndX(), myFamily.getRootMember().getTopLine().getEndY()));
                            pane.getChildren().remove(myFamily.getRootMember().getTopLine());
                        }

                        break;
                    }
                }
            }
        });

        Button removeParent2Button = new Button("Remove Parent 2");
        removeParent2Button.setLayoutX(1360);
        removeParent2Button.setLayoutY(665);
        removeParent2Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(myFamily.getRootMember().getParent2().getName()))
                    {
                        myFamily.getRootMember().removeParent(myFamily.getRootMember().getParent2());

                        removeMembers(myFamily, myFamily.getMember(count), myFamily.getRootMember());

                        pane.getChildren().remove(myFamily.getRootMember().getLine(myFamily.getRootMember().getButton().getLayoutX() + (myFamily.getRootMember().getButton().getWidth() / 2), myFamily.getRootMember().getButton().getLayoutY() - 200));

                        if(myFamily.getRootMember().hasNoParents())
                        {
                            pane.getChildren().remove(myFamily.getRootMember().getLine(myFamily.getRootMember().getTopLine().getEndX(), myFamily.getRootMember().getTopLine().getEndY()));
                            pane.getChildren().remove(myFamily.getRootMember().getTopLine());
                        }

                        break;
                    }
                }
            }
        });

        TextField removeChildInput = new TextField();
        removeChildInput.setLayoutX(1275);
        removeChildInput.setLayoutY(580);

        Button removeChildButton = new Button("Remove Child");
        removeChildButton.setLayoutX(1360);
        removeChildButton.setLayoutY(625);
        removeChildButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    for(int i = 0; i < myFamily.getRootMember().getChildCount(); i++)
                    {
                        if(myFamily.getMember(count).getName().equals(myFamily.getRootMember().getChild(i).getName()))
                        {
                            pane.getChildren().remove(myFamily.getRootMember().getLine(myFamily.getRootMember().getChild(i).getTopLine().getEndX(), myFamily.getRootMember().getChild(i).getTopLine().getEndY() - 216));
   
                            if(!(myFamily.getRootMember().hasSpouse()))
                            {
                                pane.getChildren().remove(myFamily.getRootMember().getLine(myFamily.getRootMember().getBottomLine().getEndX(), myFamily.getRootMember().getBottomLine().getEndY()));
                                pane.getChildren().remove(myFamily.getRootMember().getBottomLine());
                            }

                            else
                            {
                                myFamily.getRootMember().getSpouse().removeChild(myFamily.getRootMember().getChild(i));
                            }

                            myFamily.getRootMember().removeChild(myFamily.getRootMember().getChild(i));

                            removeMembers(myFamily, myFamily.getMember(count), myFamily.getRootMember());

                            break;
                        }
                    }
                }
            }
        });

        pane.getChildren().add(dividingLine1);
        pane.getChildren().add(dividingLine2);
        pane.getChildren().add(dividingLine3);
        pane.getChildren().add(addButton);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(nameInput);
        pane.getChildren().add(currentLabel);
        pane.getChildren().add(currentMemberLabel);
        pane.getChildren().add(spouseLabel);
        pane.getChildren().add(spouseInput);
        pane.getChildren().add(spouseButton);
        pane.getChildren().add(parentLabel);
        pane.getChildren().add(parentInput);
        pane.getChildren().add(parentButton);
        pane.getChildren().add(childLabel);
        pane.getChildren().add(childInput);
        pane.getChildren().add(childButton);
        pane.getChildren().add(removeSpouseButton);
        pane.getChildren().add(removeParent1Button);
        pane.getChildren().add(removeParent2Button);
        pane.getChildren().add(removeChildInput);
        pane.getChildren().add(removeChildButton);

        scene = new Scene(pane, 1500, 700);
        window.setScene(scene);
        window.show();
    }

    public static void drawMembers(Family familyTree, Member root)
    {
        if(!(root.getDrawnFlag()))
        {
            Button memButton = new Button(root.getName());
            memButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) 
                {
                    familyTree.setRootMember(root);
                    currentMemberLabel.setText(root.getName());
                }
            });

            memButton.setLayoutX(500);
            memButton.setLayoutY(275);

            pane.getChildren().add(memButton);
            scene.snapshot(null);

            root.setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.setButton(memButton);
            pane.getChildren().add(root.getButton());

            root.hasBeenDrawn(true);
        }

        if(root.hasParent1() && !(root.getParent1().getDrawnFlag()))
        {
            Button memButton = new Button(root.getParent1().getName());
            memButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) 
                {
                    familyTree.setRootMember(root.getParent1());
                    currentMemberLabel.setText(root.getParent1().getName());
                }
            });

            pane.getChildren().add(memButton);
            scene.snapshot(null);

            memButton.setLayoutX(root.getButton().getLayoutX() + (root.getButton().getWidth() / 2) - 100 - memButton.getWidth());
            memButton.setLayoutY(root.getButton().getLayoutY() - root.getButton().getHeight() - 216);

            root.getParent1().setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.getParent1().setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.getParent1().setButton(memButton);
            pane.getChildren().add(root.getParent1().getButton());

            scene.snapshot(null);
            Line memLine1 = new Line(root.getTopLine().getEndX(), root.getTopLine().getEndY(), root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY() - 200);
            Line memLine2 = new Line(root.getParent1().getBottomLine().getEndX(), root.getParent1().getBottomLine().getEndY(), memLine1.getEndX(), memLine1.getEndY());
            
            if(root.getLine(memLine1.getStartX(), memLine1.getStartY()) == null)
            {
                root.addLine(memLine1);
                root.getParent1().addLine(memLine1);
                pane.getChildren().add(root.getLine(memLine1.getStartX(), memLine1.getStartY()));
            }

            else if(root.getLine(memLine1.getStartX(), memLine1.getStartY()).getScene() == null)
            {
                pane.getChildren().add(root.getLine(memLine1.getStartX(), memLine1.getStartY()));
            }

            root.addLine(memLine2);
            root.getParent1().addLine(memLine2);
            pane.getChildren().add(root.getLine(memLine2));

            if(root.getTopLine().getScene() == null)
            {
                pane.getChildren().add(root.getTopLine());
            }

            pane.getChildren().add(root.getParent1().getBottomLine());

            root.getParent1().hasBeenDrawn(true);

            drawMembers(familyTree, root.getParent1());
        }

        if(root.hasParent2() && !(root.getParent2().getDrawnFlag()))
        {
            Button memButton = new Button(root.getParent2().getName());
            memButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) 
                {
                    familyTree.setRootMember(root.getParent2());
                    currentMemberLabel.setText(root.getParent2().getName());
                }
            });

            pane.getChildren().add(memButton);
            scene.snapshot(null);

            memButton.setLayoutX(root.getButton().getLayoutX() + (root.getButton().getWidth() / 2) + 100);
            memButton.setLayoutY(root.getButton().getLayoutY() - root.getButton().getHeight() - 216);

            root.getParent2().setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.getParent2().setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.getParent2().setButton(memButton);
            pane.getChildren().add(root.getParent2().getButton());

            scene.snapshot(null);
            Line memLine1 = new Line(root.getTopLine().getEndX(), root.getTopLine().getEndY(), root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY() - 200);
            Line memLine2 = new Line(memLine1.getEndX(), memLine1.getEndY(), root.getParent2().getButton().getLayoutX() + (root.getParent2().getButton().getWidth() / 2), memLine1.getEndY());
            
            if(root.getLine(memLine1.getStartX(), memLine1.getStartY()) == null)
            {
                root.addLine(memLine1);
                root.getParent2().addLine(memLine1);
                pane.getChildren().add(root.getLine(memLine1.getStartX(), memLine1.getStartY()));
            }

            else if(root.getLine(memLine1.getStartX(), memLine1.getStartY()).getScene() == null)
            {
                pane.getChildren().add(root.getLine(memLine1));
            }

            root.addLine(memLine2);
            root.getParent2().addLine(memLine2);
            pane.getChildren().add(root.getLine(memLine2));
            
            if(root.getTopLine().getScene() == null)
            {
                pane.getChildren().add(root.getTopLine());
            }

            pane.getChildren().add(root.getParent2().getBottomLine());

            root.getParent2().hasBeenDrawn(true);

            drawMembers(familyTree, root.getParent2());
        }

        if(root.hasSpouse() && !(root.getSpouse().getDrawnFlag()))
        {
            Button memButton = new Button(root.getSpouse().getName());
            memButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) 
                {
                    familyTree.setRootMember(root.getSpouse());
                    currentMemberLabel.setText(root.getSpouse().getName());
                }
            });

            pane.getChildren().add(memButton);
            scene.snapshot(null);

            memButton.setLayoutX(root.getButton().getLayoutX() + root.getButton().getWidth() + 300);
            memButton.setLayoutY(root.getButton().getLayoutY());

            root.getSpouse().setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.getSpouse().setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.getSpouse().setButton(memButton);
            pane.getChildren().add(root.getSpouse().getButton());

            scene.snapshot(null);
            Line memLine1 = new Line(root.getBottomLine().getEndX(), root.getBottomLine().getEndY(), (root.getBottomLine().getEndX() + root.getSpouse().getBottomLine().getEndX()) / 2, root.getSpouse().getBottomLine().getEndY());
            Line memLine2 = new Line(root.getSpouse().getBottomLine().getEndX(), root.getSpouse().getBottomLine().getEndY(), memLine1.getEndX(), memLine1.getEndY());
            root.addLine(memLine1);
            root.getSpouse().addLine(memLine2);
            pane.getChildren().add(root.getLine(memLine1));
            pane.getChildren().add(root.getSpouse().getLine(memLine2));

            if(root.getBottomLine().getScene() == null)
            {
                pane.getChildren().add(root.getBottomLine());
            }
    
            pane.getChildren().add(root.getSpouse().getBottomLine());

            root.getSpouse().hasBeenDrawn(true);

            drawMembers(familyTree, root.getSpouse());
        }

        if(root.hasChildren())
        {
            for(int count = 0; count < root.getChildCount(); count++)
            {
                if(!root.getChild(count).getDrawnFlag())
                {
                    if(root.getChildCount() == 1)
                    {
                        Button memButton = new Button(root.getChild(count).getName());
                        memButton.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) 
                            {
                                familyTree.setRootMember(root.getChild(memButton.getText()));
                                currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                            }
                        });

                        pane.getChildren().add(memButton);
                    
                        scene.snapshot(null);
                        Line memLine = new Line(root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndX(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndY(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndX(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndY() + 216); 
                        root.addLine(memLine);

                        scene.snapshot(null);
                        memButton.setLayoutX(memLine.getEndX() - (memButton.getWidth() / 2));
                        memButton.setLayoutY(memLine.getEndY() + 16);

                        root.getChild(count).setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
                        root.getChild(count).setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
                        pane.getChildren().remove(memButton);

                        root.getChild(count).setButton(memButton);
                        pane.getChildren().add(root.getChild(count).getButton());

                        pane.getChildren().add(root.getLine(memLine));
                        pane.getChildren().add(root.getChild(count).getTopLine());

                        root.getChild(count).hasBeenDrawn(true);
                    }

                    else if(root.getChildCount() % 2 == 0)
                    {
                        addEvenChildren(familyTree, root, root.getChildCount());
                    }

                    else if(root.getChildCount() % 2 == 1)
                    {
                        addEvenChildren(familyTree, root, root.getChildCount() - 1);

                        Button memButton = new Button(root.getChild(root.getChildCount() - 1).getName());
                        memButton.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) 
                            {
                                familyTree.setRootMember(root.getChild(memButton.getText()));
                                currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                            }
                        });

                        pane.getChildren().add(memButton);
                        scene.snapshot(null);
                        
                        memButton.setLayoutX(root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndX() - (memButton.getWidth() / 2));
                        memButton.setLayoutY(root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndY() + 232);
                        
                        root.getChild(root.getChildCount() - 1).setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
                        root.getChild(root.getChildCount() - 1).setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
                        pane.getChildren().remove(memButton);

                        root.getChild(root.getChildCount() - 1).setButton(memButton);
                        pane.getChildren().add(root.getChild(root.getChildCount() - 1).getButton());
                        pane.getChildren().add(root.getChild(root.getChildCount() - 1).getTopLine());

                        root.getChild(count).hasBeenDrawn(true);
                    }
                }

                drawMembers(familyTree, root.getChild(count));
            }
        }
    }

    public static void addEvenChildren(Family familyTree, Member root, int childAmt)
    {
        Line memLine = new Line(root.getBottomLine().getEndX(), root.getBottomLine().getEndY(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndX(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndY() + 216);

        for(int i = 0; i < childAmt; i++)
        {
            pane.getChildren().remove(root.getChild(i).getTopLine());
            pane.getChildren().remove(root.getChild(i).getButton());

            Button memButton = new Button(root.getChild(i).getName());
            memButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) 
                {
                    familyTree.setRootMember(root.getChild(memButton.getText()));
                    currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                }
            });

            pane.getChildren().add(memButton);
            scene.snapshot(null);

            if(i % 2 == 0)
            {
                memButton.setLayoutX(memLine.getEndX() - ((i + 1) * 150) - memButton.getWidth());
                memButton.setLayoutY(memLine.getEndY() + 16);
            }

            else
            {
                memButton.setLayoutX(memLine.getEndX() + (i * 150));
                memButton.setLayoutY(memLine.getEndY() + 16);
            }

            root.getChild(i).setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.getChild(i).setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.getChild(i).setButton(memButton);
            pane.getChildren().add(root.getChild(i).getButton());
            pane.getChildren().add(root.getChild(i).getTopLine());

            root.getChild(i).hasBeenDrawn(true);
        }

        scene.snapshot(null);
        Line memLine2 = new Line(root.getChild(childAmt - 2).getButton().getLayoutX() + (root.getChild(childAmt - 2).getButton().getWidth() / 2), root.getChild(childAmt - 2).getButton().getLayoutY() - 16, root.getChild(childAmt - 1).getButton().getLayoutX() + (root.getChild(childAmt - 1).getButton().getWidth() / 2), root.getChild(childAmt - 1).getButton().getLayoutY() - 16); 
        root.addLine(memLine2);
        pane.getChildren().add(root.getLine(memLine2));
    }

    public void removeMembers(Family familyTree, Member root, Member originalMember)
    {
        for(int i = 0; i < root.getLineCount(); i++)
        {
            pane.getChildren().remove(root.getLine(i));
        }

        root.clearLines();

        if(root.getTopLine().getScene() != null)
        {
            pane.getChildren().remove(root.getTopLine());
        }

        if(root.getBottomLine().getScene() != null)
        {
            pane.getChildren().remove(root.getBottomLine());
        }

        pane.getChildren().remove(root.getButton());
        root.hasBeenDrawn(false);
       
        if(root.hasParent1() && root.getParent1() != originalMember && originalMember.getSpouse() != root.getParent1())
        {
            Member curMember = root.getParent1();

            root.removeParent(root.getParent1());

            removeMembers(familyTree, curMember, originalMember);
        }

        if(root.hasParent2() && root.getParent2() != originalMember && originalMember.getSpouse() != root.getParent2())
        {
            Member curMember = root.getParent2();

            root.removeParent(root.getParent2());

            removeMembers(familyTree, curMember, originalMember);
        }
        
        if(root.hasChildren())
        {
            for(int i = 0; i < root.getChildCount(); i++)
            {
                if(root.getChild(i) != originalMember)
                {
                    if(originalMember.hasChildren())
                    {
                        if(originalMember.getChild(i) != root.getChild(i))
                        {
                            Member curMember = root.getChild(i);

                            root.removeChild(root.getChild(i));

                            removeMembers(familyTree, curMember, originalMember);
                        }
                    }
                }
            }

            root.clearChildren();
        }

        familyTree.removeMember(root);
    }
}