package application.treeutil;

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

        Button removeButton = new Button("Remove Member");
        removeButton.setLayoutX(1300);
        removeButton.setLayoutY(575);
        removeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(currentMemberLabel.getText()))
                    {
                        for(int j = 0; j < myFamily.getMember(count).getLineCount(); j++)
                        {
                            pane.getChildren().remove(myFamily.getMember(count).getLine(j));
                        }

                        myFamily.getMember(count).clearLines();

                        if(myFamily.getMember(count).getTopLine().getScene() != null)
                        {
                            pane.getChildren().remove(myFamily.getMember(count).getTopLine());
                        }

                        if(myFamily.getMember(count).getBottomLine().getScene() != null)
                        {
                            pane.getChildren().remove(myFamily.getMember(count).getBottomLine());
                        }

                        pane.getChildren().remove(myFamily.getMember(count).getButton());
                        myFamily.getMember(count).hasBeenDrawn(false);

                        if(myFamily.getMember(count).hasSpouse())
                        {
                            myFamily.getMember(count).getSpouse().removeSpouse();
                            myFamily.getMember(count).removeSpouse();
                        }
                        
                        if(myFamily.getMember(count).hasParent1())
                        {
                            myFamily.getMember(count).getParent1().removeChild(myFamily.getMember(count));
                            myFamily.getMember(count).removeParent(myFamily.getMember(count).getParent1());
                        }

                        if(myFamily.getMember(count).hasParent2())
                        {
                            myFamily.getMember(count).getParent2().removeChild(myFamily.getMember(count));
                            myFamily.getMember(count).removeParent(myFamily.getMember(count).getParent2());
                        }
                       
                        if(myFamily.getMember(count).hasChildren())
                        {
                            for(int i = 0; i < myFamily.getMember(count).getChildCount(); i++)
                            {
                                myFamily.getMember(count).getChild(i).removeParent(myFamily.getMember(count));
                            }

                            myFamily.getMember(count).clearChildren();
                        }

                        myFamily.removeMember(myFamily.getMember(count));

                        break;
                    }
                }
            }
        });

        Button removeSpouseButton = new Button("Remove Spouse");
        removeSpouseButton.setLayoutX(1240);
        removeSpouseButton.setLayoutY(625);
        removeSpouseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                // Stuff...
            }
        });

        Button removeParent1Button = new Button("Remove Parent 1");
        removeParent1Button.setLayoutX(1240);
        removeParent1Button.setLayoutY(665);
        removeParent1Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                // Stuff...
            }
        });

        Button removeParent2Button = new Button("Remove Parent 2");
        removeParent2Button.setLayoutX(1360);
        removeParent2Button.setLayoutY(665);
        removeParent2Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                // Stuff...
            }
        });

        Button removeChildButton = new Button("Remove Child");
        removeChildButton.setLayoutX(1360);
        removeChildButton.setLayoutY(625);
        removeChildButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                // Stuff...
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
        pane.getChildren().add(removeButton);
        pane.getChildren().add(removeSpouseButton);
        pane.getChildren().add(removeParent1Button);
        pane.getChildren().add(removeParent2Button);
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
            Line memLine1 = new Line(root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY() - 16, root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY() - 200);
            Line memLine2 = new Line(memLine1.getEndX(), memLine1.getEndY(), root.getParent1().getButton().getLayoutX() + (root.getParent1().getButton().getWidth() / 2), memLine1.getEndY());
            root.addLine(memLine1);
            root.addLine(memLine2);
            pane.getChildren().add(root.getLine(memLine1));
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
            Line memLine1 = new Line(root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY() - 16, root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY() - 200);
            Line memLine2 = new Line(memLine1.getEndX(), memLine1.getEndY(), root.getParent2().getButton().getLayoutX() + (root.getParent2().getButton().getWidth() / 2), memLine1.getEndY());
            root.addLine(memLine1);
            root.addLine(memLine2);
            pane.getChildren().add(root.getLine(memLine1));
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
            pane.getChildren().add(root.getBottomLine());
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
                                currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                            }
                        });

                        pane.getChildren().add(memButton);
                        scene.snapshot(null);

                        memButton.setLayoutX(((root.getButton().getLayoutX() + root.getButton().getWidth() + root.getSpouse().getButton().getLayoutX()) / 2) - (memButton.getWidth() / 2));
                        memButton.setLayoutY(root.getButton().getLayoutY() + root.getButton().getHeight() + 232);

                        root.getChild(count).setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
                        root.getChild(count).setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
                        pane.getChildren().remove(memButton);

                        root.getChild(count).setButton(memButton);
                        pane.getChildren().add(root.getChild(count).getButton());

                        scene.snapshot(null);
                        Line memLine = new Line(((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + root.getButton().getHeight() + 16, ((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + root.getButton().getHeight() + 216); 
                        root.getChild(count).addLine(memLine);
                        pane.getChildren().add(root.getChild(count).getLine(memLine));
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
                                currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                            }
                        });

                        pane.getChildren().add(memButton);
                        scene.snapshot(null);
                        
                        memButton.setLayoutX(((root.getButton().getLayoutX() + root.getButton().getWidth() + root.getSpouse().getButton().getLayoutX()) / 2) - (memButton.getWidth() / 2));
                        memButton.setLayoutY(root.getChild(0).getButton().getLayoutY());
                        
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
        for(int i = 0; i < childAmt; i++)
        {
            pane.getChildren().remove(root.getChild(i).getTopLine());
            pane.getChildren().remove(root.getChild(i).getButton());

            Button memButton = new Button(root.getChild(i).getName());
            memButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) 
                {
                    currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                }
            });

            pane.getChildren().add(memButton);
            scene.snapshot(null);

            if(i % 2 == 0)
            {
                memButton.setLayoutX(root.getButton().getLayoutX() - (i * 50));
                memButton.setLayoutY(root.getButton().getLayoutY() + root.getButton().getHeight() + 232);
            }

            else
            {
                memButton.setLayoutX(root.getSpouse().getButton().getLayoutX() + (i * 50));
                memButton.setLayoutY(root.getSpouse().getButton().getLayoutY() + root.getSpouse().getButton().getHeight() + 232);
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
        Line memLine1 = new Line(((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + root.getButton().getHeight() + 16, ((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + root.getButton().getHeight() + 216); 
        Line memLine2 = new Line(root.getChild(childAmt - 2).getButton().getLayoutX() + (root.getChild(childAmt - 2).getButton().getWidth() / 2), root.getChild(childAmt - 2).getButton().getLayoutY() - 16, root.getChild(childAmt - 1).getButton().getLayoutX() + (root.getChild(childAmt - 1).getButton().getWidth() / 2), root.getChild(childAmt - 1).getButton().getLayoutY() - 16); 
        root.addLine(memLine1);
        root.addLine(memLine2);
        pane.getChildren().add(root.getLine(memLine1));
        pane.getChildren().add(root.getLine(memLine2));
    }
}
